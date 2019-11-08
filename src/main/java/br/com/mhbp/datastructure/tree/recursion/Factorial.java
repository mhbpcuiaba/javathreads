package br.com.mhbp.datastructure.tree.recursion;

public class Factorial {


    public int factorial(int n) {
        if ( n <= 0 ) throw new IllegalArgumentException("Parameter n must be positive integer");
        if( n == 1) return n;
        return  n * factorial(n-1);
    }

    public static void main(String[] args) {
        System.out.println(new Factorial().factorial(3));
    }
}
