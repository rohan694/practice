package com.practice.code.algorithm.apriori;


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
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;


public class Demo {

    public static void main(String[] args) throws NumberFormatException, IOException {
        demo();
    }
    
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false; 
		}
		return pattern.matcher(strNum).matches();
	}

    private static void demo() throws NumberFormatException, IOException {
        AprioriFrequentItemsetGenerator<String> generator =
                new AprioriFrequentItemsetGenerator<>();


		HashMap<Integer,Set<String>> tempData=new HashMap();

        BufferedReader br = new BufferedReader(new FileReader("C:/Users/rohan/Downloads/TEST_DATA.csv"));  
		String line="";
		Scanner sc=new Scanner(System.in);
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
			String[] tempRow = line.split(";");    // use comma as separator  
			if(isNumeric(tempRow[0])){
				Set<String> temp=tempData.get(Integer.parseInt(tempRow[0]));
				if(temp==null){
					temp=new HashSet<String>();
					tempData.put(Integer.parseInt(tempRow[0]), temp);
				}
				temp.add(tempRow[1]);
			}
		}  
        List<Set<String>> itemsetList = new ArrayList<>();

		for (Entry<Integer, Set<String>> entry : tempData.entrySet()) {
			Set<String> transaction =entry.getValue();
			itemsetList.add(entry.getValue());
		}

        long startTime = System.nanoTime();
        System.out.println("Please enter the Minimum support value");
        FrequentItemsetData<String> data = generator.generate(itemsetList, sc.nextDouble());
        long endTime = System.nanoTime();
System.out.println("freq"+data);
        int i = 1;

        System.out.println("--- Frequent itemsets ---");

        for (Set<String> itemset : data.getFrequentItemsetList()) {
            
        	System.out.printf("%2d: %9s, support: %1.1f\n",
                              i++, 
                              itemset,
                              data.getSupport(itemset));
        }

        System.out.printf("Mined frequent itemset in %d milliseconds.\n", 
                          (endTime - startTime) / 1_000_000);

        startTime = System.nanoTime();
        System.out.println("Please enter the Minimum Confidence value");

        List<AssociationRule<String>> associationRuleList = 
                new AssociationRuleGenerator<String>()
                        .mineAssociationRules(data, sc.nextDouble());
        endTime = System.nanoTime();

        i = 1;

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