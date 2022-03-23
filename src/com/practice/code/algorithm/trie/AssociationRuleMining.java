package com.practice.code.algorithm.trie;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AssociationRuleMining {
	static Scanner scanner = new Scanner(System.in);
	   
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false; 
		}
		return pattern.matcher(strNum).matches();
	}

	public static void main(String[] args) throws Exception {
		AssociationRuleMining arm = new AssociationRuleMining();
		Instances data;
		
		//READING INPUT AND DATASET 
		System.out.println("Enter dataset path: ");
		String filePath = scanner.nextLine();
		

		
		
		
		
		if (arm.checkFileExistence(filePath)){
			data = arm.readDataSet(filePath);
		}
		else{
			System.out.println("Incorrect file");
			return;
		}
		
		int minimumSupport = arm.getUserInput(data.size());
		if(minimumSupport > data.size() || minimumSupport < 0){
			System.out.println("Invalid minimum support should be between 0 and " + data.size());
			return;
		}
		
		Long startTime = System.currentTimeMillis();
		
		//BUILDING TRIE
		Trie trieObj = new Trie();
		trieObj.singleItemSet(data, minimumSupport);
		
		//FREQUENT ITEMSET GENERATION
		System.out.println("Candidate itemset generation starts.");
		trieObj.traverseDFS(trieObj.root, "");
		System.out.println("Candidate itemset generation completed.");
		
		//RULE GENERATION
		trieObj.getPermutations();
		
		//SORTING RULES IN DESCENDING ORDER OF CONFIDENCE
		trieObj.sortRules();
		
		//PRINTING RULES
		trieObj.printRules();
		
		Long stopTime = System.currentTimeMillis();
		Long elapsedTime = stopTime - startTime;
		System.out.println("Runtime: " + elapsedTime/1000.0 + " seconds");
	}

	public Instances readDataSet(String filePath) throws Exception{
		//DataSource input = new DataSource("D:/eclipse workspaces/practice/weather.nominal.arff");
	/*

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

		
		
		File myObj = new File("dataset.arff");
		try {
		    if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		        StringBuilder sb=new StringBuilder();
		        sb.append("@relation weather.symbolic\n");
		        sb.append("\n");
		        sb.append("\n");
	        } else {
	          System.out.println("File already exists.");
	        }
	    } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	     }
*/
		DataSource input = new DataSource(filePath);
		Instances data = input.getDataSet();
		return data;
	}
	
	public boolean checkFileExistence(String filePath){
		File file = new File(filePath);
		if(file.exists() && !file.isDirectory()){
			return true;
		}
		return false;
	}
	
	public int getUserInput(int dataSize){
		System.out.println("Total number of instances in dataset is: " + dataSize);
		System.out.println("Enter minimum frequency(support) for generating rules: ");
		return Integer.parseInt(scanner.nextLine());		
	}
}

