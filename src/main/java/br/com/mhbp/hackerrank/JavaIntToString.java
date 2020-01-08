package br.com.mhbp.hackerrank;

import java.security.Permission;
import java.util.Scanner;

public class JavaIntToString {


    public static void main(String[] args) {
        DoNotTerminate.forbidExit();

        try {
            Scanner in = new Scanner(System.in);
            int n = in .nextInt();
            in.close();
//            String s = convert(n);
            String s = String.valueOf(n);
            if (n == Integer.parseInt(s)) {
                System.out.println("Good job");
            } else {
                System.out.println("Wrong answer.");
            }
        } catch (DoNotTerminate.ExitTrappedException e) {
            System.out.println("Unsuccessful Termination!!");
        }
    }

    private static String convert(int i) {
        String s = String.valueOf(i);
        if (i > 0 && i < 27 ) {
            return String.valueOf((char)(i + 64));
        }
        throw new DoNotTerminate.ExitTrappedException();
    }
}


class DoNotTerminate {

    public static class ExitTrappedException extends SecurityException {

        private static final long serialVersionUID = 1;
    }

    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}