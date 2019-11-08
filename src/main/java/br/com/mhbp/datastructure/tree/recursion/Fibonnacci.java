package br.com.mhbp.datastructure.tree.recursion;

public class Fibonnacci {

    public int calc(int n) {

        if (n < 1 ) throw new IllegalArgumentException("parameter must be positive integer");

        if (n == 1 || n == 2) return n -1;

        return calc(n -1) + calc(n -2);
    }

    public static void main(String[] args) {
        System.out.println(new Fibonnacci().calc(3));
    }
}
