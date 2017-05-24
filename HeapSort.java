package Algo;

import lib.StdIn;
import lib.StdOut;

public class HeapSort {

	private HeapSort(){}
	
	public static void sort(Comparable[] pq){
		int n = pq.length;
		for(int k = n/2;k>=1;k--){
			sink(pq, k, n);
		}
		while(n>1){
			exch(pq, 1, n--);
			sink(pq, 1, n);
		}
	}
	
	private static void sink(Comparable[] pq,int k,int n){
		while(2*k <= n){
			int j = 2*k;
			if(j<n && less(pq,j,j+1)) j++;
			if(!less(pq,k,j)) break;
			exch(pq,k,j);
			k = j;
		}
	}
	
	private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
    
    public static void main(String[] args) {
        String[] a = "5,2,1,3,7".split(",");
        HeapSort.sort(a);
        show(a);
    }
}
