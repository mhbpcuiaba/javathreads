package br.com.mhbp.crackingatcoding.ch01;

import java.util.Arrays;

public class Ex_1_1 {

    public static void main(String[] args) {
        String str = "paskdmnuie";

        if(isAllUnique(str)) {
            System.out.println("all unique");
        } else {
            System.out.println("not all unique");
        }
    }

    private static boolean isAllUnique(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);

        System.out.println(chars);
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i+1]) {
                return false;
            }
        }
        return true;
    }
}
