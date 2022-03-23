package com.practice.code.algorithm.gsp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.github.chen0040.spm.data.ItemSetWithTimeId;
import com.github.chen0040.spm.data.MetaData;
import com.github.chen0040.spm.data.Sequence;
import com.github.chen0040.spm.data.Sequences;
import com.practice.code.algorithm.apriori.AprioriFrequentItemsetGenerator;

import association.AssociationRule;
import association.AssociationRuleGenerator;
import association.FrequentItemsetData;

public class RunGSP {
    
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false; 
		}
		return pattern.matcher(strNum).matches();
	}

	public static void  main(String args[]) throws NumberFormatException, IOException {

		List<Sequence> database = new ArrayList<>();

		// Below is 4 sequences of transactions stored in the database 
		/*
		S1 	(1), (1 2 3), (1 3), (4), (3 6)
		S2 	(1 4), (3), (2 3), (1 5)
		S3 	(5 6), (1 2), (4 6), (3), (2)
		S4 	(5), (7), (1 6), (3), (2), (3)
		*/

		HashMap<Integer,List<String>> tempData=new HashMap();

        BufferedReader br = new BufferedReader(new FileReader("C:/Users/rohan/Downloads/TEST_DATA-1.csv"));  
		String line="";
		Scanner sc=new Scanner(System.in);
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
			String[] tempRow = line.split(";");    // use comma as separator  
			if(isNumeric(tempRow[0])){
				List<String> temp=tempData.get(Integer.parseInt(tempRow[0]));
				if(temp==null){
					temp=new ArrayList<String>();
					tempData.put(Integer.parseInt(tempRow[0]), temp);
				}
				temp.add(tempRow[1]);
			}
		}  
        List<List<String>> itemsetList = new ArrayList<>();

		for (Entry<Integer, List<String>> entry : tempData.entrySet()) {
			List<String> texts =entry.getValue();
			itemsetList.add(entry.getValue());
		     
			List<ItemSetWithTimeId> itemSets = texts.stream().map(text -> new ItemSetWithTimeId(text.split(","))).collect(Collectors.toList());
		      Sequence sequence = new Sequence();
		      for(ItemSetWithTimeId element : itemSets) {
		         sequence.addElement(element);
		      }
			database.add(sequence);

		}
        System.out.println("Please enter the Minimum support value");

		int minimumSupport=(int) (sc.nextDouble()*10);
		GSP method = new GSP();
		method.setMinSupportLevel(minimumSupport);
		List<String> uniqueItems = new MetaData(database).getUniqueItems();
		Sequences result = method.minePatterns(database, uniqueItems, -1);
		

		List<Set<String>> frequentItemsetList = new ArrayList();;
		Map<Set<String>, Integer> supportCountMap=new HashMap();;
		result.getSequences().stream().forEach(sequence -> {
			System.out.println("sequence: " + sequence);
			Set<String> temp=new HashSet();
			
			for(ItemSetWithTimeId s: sequence.getElements()) {
				supportCountMap.put(s.getItems().stream().collect(Collectors.toSet()), s.getSupport());

				temp.addAll(s.getItems().stream().collect(Collectors.toSet()));
			}
			frequentItemsetList.add(temp);
			supportCountMap.put(temp, sequence.getSupport());
			
		});
		for(Sequence sequence : database){
	         for(String item : uniqueItems){
	            if(sequence.containsItem(item)){
	            	supportCountMap.put(new HashSet<>(Arrays.asList(item)), supportCountMap.getOrDefault(item, 0) + 1);
	            }
	         }
	    }
        FrequentItemsetData<String> data = new FrequentItemsetData<>(frequentItemsetList, supportCountMap, minimumSupport, itemsetList.size());

		long startTime = System.nanoTime();
        System.out.println("Please enter the Minimum Confidence value");

        List<AssociationRule<String>> associationRuleList = 
                new AssociationRuleGenerator<String>()
                        .mineAssociationRules(data, sc.nextDouble());
        long endTime = System.nanoTime();

        int i = 1;

        System.out.println();
        System.out.println("--- Association rules ---");

        for (AssociationRule<String> rule : associationRuleList) {
            System.out.printf("%2d: %s\n", i++, rule);
        }

        System.out.printf("Mined association rules in %d milliseconds.\n",
                          (endTime - startTime) / 1_000_000);
        sc.close();
		
	}

}
