package com.practice.code.general;
import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class Apriori {
	private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false; 
		}
		return pattern.matcher(strNum).matches();
	}
	public static void main (String args[]) throws NumberFormatException, IOException {

		// Get input
		HashMap<Integer,ArrayList<String>> tempData=new HashMap();
		
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/rohan/Downloads/TEST_DATA.csv"));  
		String line="";
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
			String[] tempRow = line.split(";");    // use comma as separator  
			if(isNumeric(tempRow[0])){
				ArrayList<String> temp=tempData.get(Integer.parseInt(tempRow[0]));
				if(temp==null){
					temp=new ArrayList<String>();
					tempData.put(Integer.parseInt(tempRow[0]), temp);
				}
				temp.add(tempRow[1]);
			}
		}  

		//Scanner terminal = new Scanner(System.in);
		//System.out.println("Number of transactions: ");
		//int numberOfTransactions = Integer.parseInt(terminal.nextLine());
		//System.out.println("Enter transactions separated by new line and items separated by spaces:");

		ArrayList<ArrayList<String>> transactions = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> _transactions = new ArrayList<ArrayList<String>>();

		ArrayList<ArrayList<String>> prevItemSetsWithMinSupportCount = new ArrayList<ArrayList<String>>();

		for (Map.Entry<Integer,ArrayList<String>> entry : tempData.entrySet()) {
			ArrayList<String> transaction =entry.getValue();
			transactions.add(transaction);
			_transactions.add(transaction);
		}
		System.out.println("final data after preprocessing : ");
		System.out.println(transactions);
		System.out.println("Minumum support count: 2");
		int minSupportCount = 2; //Integer.parseInt(terminal.nextLine());

		// Get all items
		ArrayList<String> items = getUniqueItems(transactions);

		int x = 0; // x is the number of elements in the item-ets to consider
		while (true) {

			// Consider one more item than the last iteration
			x++;

			// List of support count of each itemset
			ArrayList<Integer> supportCountList = new ArrayList<Integer>();

			// Get permuted itemsets with items. There will be x elements in each itemset.
			ArrayList<ArrayList<String>> itemSets = getItemSets(items, x);

			// Calculate each itemset's support count
			for (ArrayList<String> itemSet : itemSets) {

				int count = 0;
				for (ArrayList<String> transaction : transactions) {
					if (existsInTransaction(itemSet, transaction)) count++;
				}
				supportCountList.add(count);
			}

			// Out of all the itemsets, get the itemsets with
			// support count greater than or equal to minSupportCount
			ArrayList<ArrayList<String>> itemSetsWithMinSupportCount = getItemSetsWithMinSupportCount(itemSets, supportCountList, minSupportCount);

			// No itemSetsWithMinSupportCount exist
			if (itemSetsWithMinSupportCount.size() == 0) {
				System.out.print("The itemset(s) that are the most frequent itemset(s): ");
				System.out.println(prevItemSetsWithMinSupportCount);
				System.out.println("association rules are");
				System.out.println(findAssociationRules(prevItemSetsWithMinSupportCount,supportCountList,transactions));
				break;
			}

			items = getUniqueItems(itemSetsWithMinSupportCount);

			prevItemSetsWithMinSupportCount = itemSetsWithMinSupportCount;
		}

	}

	private static char[] findAssociationRules(ArrayList<ArrayList<String>> frequentSetList, ArrayList<Integer> supportCountList, ArrayList<ArrayList<String>> transactions) {
		// TODO Auto-generated method stub
		
		System.out.println("just for testing"+frequentSetList);
		System.out.println(supportCountList);
		/*for(ArrayList<String> frequentSet:frequentSetList) {
			for(int i=0;i<frequentSet.size();i++) {
				for(int j=0;j<frequentSet.size(); && j++) {
					for(int k=0;k<frequentSet.size();k++) {
						ArrayList<String> temp=new ArrayList();
						temp.add(frequentSet.get(i));
						temp.add(frequentSet.get(j));
						
						if(getSupportCount(temp,transactions))
					}
				}
			}
		}*/
		
		
		return null;
	}
	
	private static int getSupportCount(ArrayList<String> itemSet,ArrayList<ArrayList<String>> transactions) {
		int count = 0;
			for (ArrayList<String> transaction : transactions) {
				if (existsInTransaction(itemSet, transaction)) count++;
			}
			
		return count;
	}
	// Returns the list of unqiue items from a list of transactions
	private static ArrayList<String> getUniqueItems (ArrayList<ArrayList<String>> data) {
		ArrayList<String> toReturn = new ArrayList<String>();

		for (ArrayList<String> transaction : data) {
			for (String item : transaction) {
				if (!toReturn.contains(item)) toReturn.add(item);
			}
		}

		Collections.sort(toReturn);
		return toReturn;
	}

	// Returns a list of itemsets, where each itemset has x number of items
	private static ArrayList<ArrayList<String>> getItemSets (ArrayList<String> items, int number) {
		if (number == 1) {

			// Return ArrayList of (ArrayList with one item)
			ArrayList<ArrayList<String>> toReturn = new ArrayList<ArrayList<String>>();
			for (String item : items) {
				ArrayList<String> aList = new ArrayList<String>();
				aList.add(item);
				toReturn.add(aList);
			}
			return toReturn;

		} else {

			int size = items.size();

			ArrayList<ArrayList<String>> toReturn = new ArrayList<ArrayList<String>>();

			for (int i = 0; i < size; i++) {

				// Copy items to _items
				ArrayList<String> _items = new ArrayList<String>();
				for (String item : items) {
					_items.add(item);
				}

				// Get item at i-th position
				String thisItem = items.get(i);

				// Remove items upto i, inclusive
				for (int j = 0; j <= i; j++) {
					_items.remove(0);
				}

				// Get permutations of the remaining items
				ArrayList<ArrayList<String>> permutationsBelow = getItemSets(_items, number - 1);

				// Add thisItem to each permutation and add the permutation to toReturn
				for (ArrayList<String> aList : permutationsBelow) {
					aList.add(thisItem);
					Collections.sort(aList);
					toReturn.add(aList);
				}

			}

			return toReturn;

		}
	}

	// Check if all items exist in a transaction
	private static boolean existsInTransaction (ArrayList<String> items, ArrayList<String> transaction) {
		for (String item : items) {
			if (!transaction.contains(item)) return false;
		}
		return true;
	}

	// Returns itemsets with support count greater than or equal to minimum support count
	private static ArrayList<ArrayList<String>> getItemSetsWithMinSupportCount (
		ArrayList<ArrayList<String>> itemSets, ArrayList<Integer> count, int minSupportCount) {

		ArrayList<ArrayList<String>> toReturn = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < count.size(); i++) {
			int c = count.get(i);
			if (c >= minSupportCount) {
				toReturn.add(itemSets.get(i));
			}
		}

		return toReturn;
	}

}