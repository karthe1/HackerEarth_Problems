package com.karthick.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author karthick.r1
 */
public class DrugPacketsOptimization {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        LinkedList<Integer> noOfPacketsArray = new LinkedList<>();
        LinkedList<Integer> optimizedPackets = new LinkedList<>();
        while (testCases>0) {
            int arraySize = Integer.parseInt(br.readLine());
            for (int i = 0; i < arraySize; i++) {
                noOfPacketsArray.add(i, Integer.parseInt(br.readLine()));
            }
            Collections.sort(noOfPacketsArray);
            optimizedPackets.add(getOptimizedPackets(noOfPacketsArray));
            noOfPacketsArray.clear();
            testCases--;
        }
        for (int a:
                optimizedPackets) {
            System.out.println(a);
        }
    }

    private static int getOptimizedPackets(LinkedList<Integer> noOfPacketsArray) {

        LinkedList<Boolean> newList = new LinkedList<>();
        for (int i = 0; i < noOfPacketsArray.size(); i++) {
            newList.add(false);
        }

        int startIndex = 0;
        for (int i = 0; i < noOfPacketsArray.size(); i++) {
            if(i!=noOfPacketsArray.size()-1) {
                for (int j = 0; j < noOfPacketsArray.size(); ) {
                    if(startIndex!=j) {
                        boolean isCompared = newList.get(j);
                        if ((noOfPacketsArray.get(startIndex).equals(noOfPacketsArray.get(j))) ||
                                ((noOfPacketsArray.get(startIndex) < noOfPacketsArray.get(j)) && isCompared)) {
                            j++;
                        } else if ((noOfPacketsArray.get(startIndex) < noOfPacketsArray.get(j)) && !isCompared) {
                            noOfPacketsArray.remove(startIndex);
                            newList.set(j,true);
                            newList.remove(startIndex);
                            j = 0;
                        }

                    } else {
                        j++;
                    }
                }
            } else {
                break;
            }
        }
        return noOfPacketsArray.size();
    }
}
