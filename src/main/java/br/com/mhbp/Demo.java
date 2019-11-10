package br.com.mhbp;
import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        int[] array = {1,1,3,3,3,4,5,5,5,5};
        new Demo().solution(array, 2);


    }

    public int solution(int[] A, int Y) {
       Arrays.sort(A);

       int[] aux = new int [A.length -1];

        /* auxiliar array to count occurencies*/
        for (int i = 0; i < A.length; i++) {
            aux[A[i]]++;
        }

        Arrays.sort(aux);
        int j = aux.length - 2;
        int currentNumber = aux[aux.length - 1];
        int currentIndex = aux.length - 1;
        int auxExtended[] = makeAuxiliarCopy(aux);

        int tmpY = Y;
        while (j >= 0 && currentIndex >= 0) {

            if (aux[j] == 0)
                break;

            int aux1 = aux[j];
            int  diff = currentNumber - aux1; // 2 e Y==2

            while (tmpY >= diff) {
                auxExtended[currentIndex]++;
                tmpY = tmpY - diff;
            }
            currentIndex--;
        }

        Arrays.sort(auxExtended);
       return auxExtended[auxExtended.length -1];
    }

    int[] makeAuxiliarCopy(int[] a) {
        int auxExended[] = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            auxExended[i] = a[i];
        }

        return auxExended;
    }
}
/*

0
0
0
0
0
1 -> 4
2 -> 1
3 -> 3
4 -> 5


int[] array = {1,1,3,3,3,4,5,5,5,5};



* */