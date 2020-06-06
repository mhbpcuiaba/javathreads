package br.com.mhbp.geeksforgeek.aws;

import java.util.Arrays;

public class MaximumSumInTheConfiguration {

    public static void main(String[] args) {
        int[] array = {8, 3, 1, 2};
        int n = 4;
    }
    /*
    Above the configuration possible by rotating elements are

    8 3 1 2 here sum is 8*0 + 3*1 + 1*2 + 2*3 =  11
    2 8 3 1 here sum is 2*0 + 8*1 + 3*2 + 1*3 = 17
    1 2 8 3 here sum is 1*0 + 2*1 + 8*2 + 3*3 = 27
    3 1 2 8 here sum is 3*0 + 1*1 + 2*2 + 8*3 = 29            <=== best config

    Here the max sum is 29
     */
    int max_sum(int A[],int N)    {
        Arrays.sort(A);
        int maxSum = Integer.MIN_VALUE;
        return -1;
    }
}
