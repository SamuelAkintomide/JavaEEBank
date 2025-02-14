package com.mycompany.test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

class test{
 
    String[] arr = {"Just","Put","Something","I","In","Order"};
    Double[] size = {20.1,20.5,30.,5.2,50.2,80.1};
    HashMap<Integer, String> hm = new HashMap<>();
    HashMap<Integer, Double> ihm = new HashMap<>();
    
    //Testing to see how HashMap Will work
    public void ex(){
       
        hm.put(0, "Just");
        hm.put(1, "Put");
        hm.put(2, "Something");
        hm.put(3, "I");
        hm.put(4, "In");
        hm.put(5, "Order");
        
        ihm.put(0,10.5);
        ihm.put(1,20.5);
        ihm.put(2,30.8);
        ihm.put(3,10.1);
        ihm.put(4,40.2);
        ihm.put(5,90.2);
        
        System.out.println("\nLast 6 transactions (order of size):");
        HashMap<Integer, Double> temp = 
                (HashMap<Integer, Double>) ihm.clone();
        
        int pos = 0;
        Integer[] posArr = new Integer[6];
        
        while(temp.size() > 0){
            Integer largest = 0;
            Double large = 0.0;
            boolean in = false;
            for(Entry<Integer, Double> x: temp.entrySet()){
                for(Integer v: posArr){
                    if((v != null)&&(x.getKey() == v)){
                        in = true;
                    }
                }
                if((x.getValue() >= large)&&(in == false)){
                    large = x.getValue();
                    largest = x.getKey();
                }
            }
            temp.remove(largest);
            
            posArr[pos] = largest;
            pos ++;
        }
        
        System.out.println(Arrays.toString(posArr));
        for(Integer x:posArr){
            System.out.println(hm.get(x));
        } 
    }
    
    //Replacing HashMap and Map.Entry with the use of Arrays - testing
    public void ex2(){
        
        Double[] nums = size.clone();
        int[] sortedArray = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int temp = i;
            for (int j = 0; j < nums.length; j++) {
                boolean in = false;
                for(int b: sortedArray){
                    if(j == b){
                        in = true;
                    }
                if ((nums[j] > nums[temp])&&(in != true)) {
                    temp = j;
                    }
                }
            }
            nums[temp] = 0.0;
            sortedArray[i] = temp;
        }
        for(int f: sortedArray){
            System.out.println(arr[f]);
        }
    }
    
    //Converting the code into one that is compatible with my Bank code
    public void recentTransactions() {
        System.out.println("\nLast 6 transactions (order of size):");
        Double[] nums = size.clone();
        int[] sortedArray = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int temp = i;
            for (int j = 0; j < nums.length; j++) {
                boolean in = false;
                for(int b: sortedArray){
                    if(j == b){
                        in = true;
                    }
                if ((nums[j] > nums[temp])&&(in != true)) {
                    temp = j;
                    }
                }
            }
            nums[temp] = 0.0;
            sortedArray[i] = temp;
        }
        for(int f: sortedArray){
            System.out.println(arr[f]);
        }
    }    
    
}

public class test2 {
    public static void main(String[] args){
        test b = new test();
        b.ex();
    }
}
