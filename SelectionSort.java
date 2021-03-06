package Algo;

public class SelectionSort {

	private static void sort(Comparable a[]) {
		int N = a.length;
		for(int i=0;i<N;i++){
			int min = i;
			for(int j = i+1;j<N;j++){
				if(less(a[j],a[min])){
					min = j;
				}
			}
			exch(a, i, min);
		}
	}

	private static boolean less(Comparable v, Comparable w) {

		return v.compareTo(w) < 0;

	}

	private static void exch(Comparable a[], int i, int j) {

		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;

	}

	private static boolean isSorted(Comparable a[]) {

		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1])) {
				return false;
			}
		}

		return true;

	}

	private static void show(Comparable[] a) { 
												
		for (int i = 0; i < a.length; i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String args[]){
		Integer a[] = {20,34,5,67,4,3,2};
		System.out.println("Before sort : ");
		show(a);
		sort(a);
		System.out.println("After sort : ");
		show(a);
	} 
}
