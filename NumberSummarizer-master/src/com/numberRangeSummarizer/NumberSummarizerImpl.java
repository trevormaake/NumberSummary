package com.numberRangeSummarizer;

import java.util.*;

public class NumberSummarizerImpl implements NumberRangeSummarizer {

    public static void main(String[] args) {
        NumberSummarizerImpl nr = new NumberSummarizerImpl();
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";

        try {
            Collection<Integer> collectionInput = (List<Integer>) nr.collect(input);
            String range = nr.summarizeCollection(collectionInput);

            System.out.println(range);
        } catch (ClassCastException exc) {
            System.out.println("Please review the string there might be a character");
        }
    }

    public Collection<Integer> collect(String input) {
        String[] arrayOfString = input.split(",");
        int[] integers = new int[arrayOfString.length];

        for (int i = 0; i < arrayOfString.length; i++) {
            integers[i] = Integer.parseInt(arrayOfString[i]);
        }

        Arrays.sort(integers);
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < integers.length; i++) {
            list.add(integers[i]);
        }

        return list;
    }

    public String summarizeCollection(Collection<Integer> input) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> inputList = new ArrayList<Integer>(input);
        int length = inputList.size();
        int start;
        int next;

        for (int a = 0; a < length; a++) {
            if (a == length - 1) {
                if (inputList.get(length - 2) != inputList.get(length - 1)) {
                    sb.append(inputList.get(a)).append(",");
                }
                break;
            }
            start = inputList.get(a);
            next = inputList.get(a + 1);
            if (next == start + 1) {
                count++;
                int lowestBound = start;
                for (int i = a + 1;; i++) {
                    start = inputList.get(i);
                    next = inputList.get(i + 1);
                    if (next == start + 1) {
                        count++;
                    } else {
                        a = i;
                        if (count != 0) {
                            sb.append(lowestBound).append(" - ").append(inputList.get(i)).append(", ");
                        }
                        break;
                    }
                }
                count = 0;
            } else {
                sb.append(start).append(", ");
            }
        }
        String ranges = sb.toString();
        return ranges.substring(0, ranges.length() - 1);
    }

}
