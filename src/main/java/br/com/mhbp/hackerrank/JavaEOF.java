package br.com.mhbp.hackerrank;

import java.io.*;
import java.util.Scanner;

public class JavaEOF {

    public static void main(String[] args) throws IOException {
        int count = 1;
        Scanner sc = new Scanner(System.in);
        while( sc.hasNext()) {
            System.out.println(count++ + " " + sc.nextLine());
        }
    }
}
