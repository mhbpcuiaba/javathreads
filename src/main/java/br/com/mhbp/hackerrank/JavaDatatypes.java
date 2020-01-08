package br.com.mhbp.hackerrank;

import java.util.Scanner;

public class JavaDatatypes {

    public static void main(String []argh) {



        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();

        for(int i=0;i<t;i++) {

            try {
                long x=sc.nextLong();
                System.out.println(x+" can be fitted in:");
                //                -128                    127
                if (x >= Byte.MIN_VALUE && x<= Byte.MAX_VALUE) System.out.println("* byte");
                //              -32768                  32767
                if (x >= Short.MIN_VALUE && x<= Short.MAX_VALUE) System.out.println("* short");
                //         -2147483648             2147483647
                if (x>= Integer.MIN_VALUE && x <= Integer.MAX_VALUE) System.out.println("* int");
                //-9223372036854775808L   9223372036854775807L
                if (x>= Long.MIN_VALUE && x <= Long.MAX_VALUE) System.out.println("* long");
                //Complete the code
            }
            catch(Exception e) {
                System.out.println(sc.next()+" can't be fitted anywhere.");
            }

        }
    }
}
