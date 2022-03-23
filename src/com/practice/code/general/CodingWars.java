package com.practice.code.general;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CodingWars {


    // Complete the getCountOfPossibleTeams function below.
    static int getCountOfPossibleTeams(List<Integer> coders) {
        int n=coders.size();
        /*for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(!(coders.get(i)==coders.get(j)))
                for(int k=j+1;k<n;k++){
                    if(acceptableInputs(coders.get(i),coders.get(j),coders.get(k))){
                        out++;
                    }
                }
            }
        }*/
        /*Map<Integer,Integer> temp=new HashMap();
        for(int i=0;i<n;i++){
            Set<Integer> currValues= temp.keySet();
            for(int curr:currValues){
                if(curr<coders.get(i)){
                    
                }
            }
        }*/
        
        int out=countOfTeam(coders,1,n-1,coders.get(0),2,true);
        System.out.println(out);
        out+=countOfTeam(coders,1,n-1,coders.get(0),2,false);
        System.out.println(out);
        System.out.println(coders);
        return out;
    }
    static int countOfTeam(List<Integer> coders,int start,int end,int lastElement,int elementsNeeded,boolean ascOrder){
        int out=0;
        for(int i=start;i<=end;i++){
            if(ascOrder && lastElement<coders.get(i)){
                if(elementsNeeded>1){
                    out+=countOfTeam(coders,i+1,end,coders.get(i),elementsNeeded-1,ascOrder);
                } else {
                    out++;
                }
            }
            if(!ascOrder && lastElement>coders.get(i)){
                if(elementsNeeded>1){
                    out+=countOfTeam(coders,i+1,end,coders.get(i),elementsNeeded-1,ascOrder);
                } else {
                    out++;
                }
            }
            if(i+2<end) {
	            out+=countOfTeam(coders,i+1,end,coders.get(i),2,true);
	            out+=countOfTeam(coders,i+1,end,coders.get(i),2,false);
            }
        }
        return out;
    }
    static boolean acceptableInputs(int a,int b,int c){
        if((a<b && b<c) || (a>b && b>c)) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int codersCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> codersTemp = new ArrayList<>();

        IntStream.range(0, codersCount).forEach(i -> {
            try {
                codersTemp.add(bufferedReader.readLine().replaceAll("\\s+$", ""));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> coders = codersTemp.stream()
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int res = getCountOfPossibleTeams(coders);

      System.out.println(res);
    }
}
