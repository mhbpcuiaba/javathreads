package br.com.mhbp.crackingatcoding.ch01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ex_1_2 {

    public static void main(String[] args) {
        System.out.println(permutation("fabcdfedfaaa","abacdaedfaff"));
        System.out.println(permutationWithouSorting("fabcdfedfaaa","abacdaedfaff"));
    }


    public static boolean permutation(String s, String t) {

        if(s.length() != t.length())
            return false;

        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return chars1.equals(chars2);
    }


    //TODO it is wrong this one below
    // space complexity O(string.length)
    public static boolean permutationWithouSorting(String s, String t) {
        if(s.length() != t.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.toCharArray().length; i++) {

            if (map.containsKey(s.charAt(i))) {
                Integer count = map.get(s.charAt(i));
                count++;
            } else {
                map.put(s.charAt(i), Integer.valueOf(1));
            }
        }

        for (int i = 0; i < s.toCharArray().length; i++) {

            if (map.containsKey(s.charAt(i))) {
                Integer count = map.get(s.charAt(i));
                if (--count < 0)
                    return false;
            }
        }

        return true;
    }

    static int NO_OF_CHARS = 256;
    static boolean arePermutation(char str1[], char str2[]) //ASCII
    {
        // Create 2 count arrays and initialize
        // all values as 0
        int count1[] = new int [NO_OF_CHARS];
        Arrays.fill(count1, 0);
        int count2[] = new int [NO_OF_CHARS];
        Arrays.fill(count2, 0);
        int i;

        // For each character in input strings,
        // increment count in the corresponding
        // count array
        for (i = 0; i <str1.length && i < str2.length ;
             i++)
        {
            count1[str1[i]]++;
            count2[str2[i]]++;
        }

        // If both strings are of different length.
        // Removing this condition will make the program
        // fail for strings like "aaca" and "aca"
        if (str1.length != str2.length)
            return false;

        // Compare count arrays
        for (i = 0; i < NO_OF_CHARS; i++)
            if (count1[i] != count2[i])
                return false;

        return true;
    }
}
