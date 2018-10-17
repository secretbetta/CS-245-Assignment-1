import java.util.ArrayList;

public class NewSort implements SortingAlgorithm {
	
	public void sort(double[] arr, int runsize) {
		ArrayList<Integer> walls = findRuns(arr, runsize);
		printMini(arr, 0, arr.length - 1, "After find runs: ");
		System.out.println("Walls: " + walls);
		
		//Do merging here using walls
		
		printMini(arr, 0, arr.length - 1, "FullArray: ");
	}
	
	public static ArrayList<Integer> findRuns(double[] arr, int runsize) {
		ArrayList<Integer> walls = new ArrayList<Integer>();
		int count = 1;
		double min = Double.MIN_VALUE;
		int runstart = 0;
		int runend = 0;
		int temp1 = runstart;
		int temp2;
		//Find runs
		for (int x = 1; x < arr.length; x++) {
			min = arr[x - 1];
			runend = x - 1;
			if (min <= arr[x]) {
				
				count = 1;
				min = arr[x];
				
				while (x < arr.length && count < runsize && min <= arr[x]) {
					min = arr[x];
					runstart = x;
					count++;
					x++;
				}
				
				temp2 = runend;
				if (temp2 == 0) {
					temp2 = 1;
				}
				
				if ((runstart - runend) + 1 >= runsize) {
					QuickSort.sort(arr, temp1, temp2 - 1);
					printMini(arr, runend, runstart, "Run: ");
					temp1 = runstart;
					walls.add(runend);
					walls.add(runstart);
				}
			}
		}
		if (!walls.contains(19)) {
			walls.add(19);
		}
		if (!walls.contains(0)) {
			walls.add(0, 0);
		}
		return walls;
	}
	
	public static void printMini(double[] arr, int l, int h, String label) {
		System.out.print(label);
		for (int x = l; x < h; x++) {
			System.out.print(arr[x] + " ");
		}
		System.out.println(arr[h]);
	}

	/**
	 * Merges two arrays while comparing values to sort
	 * @param a array
	 * @param start starting index of array
	 * @param end last index of array
	 * @param mid midpoint index of array
	 */
	public static void merge(double[] a, int start, int end, int mid) {
		int len1 = mid - start + 1;
		int len2 = end - mid;
		
		double[] temp1 = new double[len1];
		double[] temp2 = new double[len2];
		
		for (int x = 0; x < len1; x++) {
			temp1[x] = a[start + x];
		}
		
		for (int x = 0; x < len2; x++) {
			temp2[x] = a[mid + 1 + x];
		}
		
		int index1 = 0;
		int index2 = 0;
		
		int index = start;
		while (index1 < len1 && index2 <  len2) { 
			if (temp1[index1] < temp2[index2]) {
				a[index] = temp1[index1];
				index1++;
			} else {
				a[index] = temp2[index2];
				index2++;
			}
			index++;
		}
		
		while (index1 < len1) {
			a[index] = temp1[index1];
			index++;
			index1++;
		}
		
		while (index2 < len2) {
			a[index] = temp2[index2];
			index++;
			index2++;
		}
	}
	
	public static void print(int[] arr, int start, int end) {
		for (int x = start; x < end - 1; x++) {
			System.out.print(arr[x] + " ");
		}
		System.out.println(arr[end - 1]);
	}
	
	public static void main(String[] args) {
////		double[] arr = {10.0, 18.0, 16.0, 12.0, 12.0, 13.0, 10.0, 1.0, 6.0,
////		16.0, 11.0, 4.0, 16.0, 1.0, 19.0, 5.0, 13.0, 13.0, 4.0, 5.0};
////		double[] arr = {66, 7, 82, 14, 96, 99, 76};
		
		NewSort o = new NewSort();
		int length = 20;
		double[] arr = new double[length];
		
		for (int x = 0; x < length; x++) {
			arr[x] = (int)(Math.random()*100 + 1);
		}
		
		int runsize = 3;
		printMini(arr, 0, arr.length - 1, "Start: ");
		System.out.println("Runsize: " + runsize);
		System.out.println();
		o.sort(arr, runsize);
	}
}
