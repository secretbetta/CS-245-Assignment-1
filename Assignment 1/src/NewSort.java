import java.util.ArrayList;

public class NewSort implements SortingAlgorithm {
	
	public void sort(double[] arr, int runsize) {
		ArrayList<Integer> walls = findRuns(arr, runsize);
		
		//Do merging here using walls
		while (walls.size() > 2) {
			for (int x = 0; x < walls.size() - 2; x++) {
				merge(arr, walls.get(x), walls.get(x + 2) - 1, walls.get(x + 1) - 1);
				walls.remove(x + 1);
			}
		}
		double last = arr[arr.length - 1];
		for (int x = arr.length - 1; x > 1; x--) {
			if (last < arr[x]) {
				arr[x + 1] = arr[x];
			}
		}
	}
	
	public static ArrayList<Integer> findRuns(double[] arr, int runsize) {
		ArrayList<Integer> walls = new ArrayList<Integer>();
		int count = 1;
		double min = Double.MIN_VALUE;
		int runstart = 0;
		int runend = 0;
		int temp1 = runstart;
		
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
				
				if (count == runsize) {
					QuickSort.sort(arr, temp1, runend - 1);
					temp1 = runstart;
					walls.add(runend);
					walls.add(runstart);
				}
			}
		}
		
		QuickSort.sort(arr, temp1, arr.length - 1);
		
		if (!walls.contains(arr.length - 1)) {
			walls.add(arr.length - 1);
		}
		
		if (!walls.contains(0)) {
			walls.add(0, 0);
		}
		
		return walls;
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
}
