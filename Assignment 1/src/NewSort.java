import java.util.ArrayList;

/**
 * My own hybrid sorting algorithm using a combination of quicksort and merge sort
 * @author Andrew
 *
 */
public class NewSort implements SortingAlgorithm {
	
	/**
	 * The main sorting method. Sorts using a hybrid of quicksort and mergesort
	 * @param arr Array to sort
	 * @param runsize Sizes of run
	 * 
	 * @see #findRuns(double[], int)
	 * @see #merge(double[], int, int, int)
	 */
	public void sort(double[] arr, int runsize) {
		// Creates walls between each run. Also turns nonruns into runs
		ArrayList<Integer> walls = findRuns(arr, runsize);
		
		//Do merging here using walls
		while (walls.size() > 2) { //Walls will always have [0,...,arr.length - 1]
			for (int x = 0; x < walls.size() - 2; x++) {
				merge(arr, walls.get(x), walls.get(x + 2) - 1, walls.get(x + 1) - 1);
				// When two arrays merges, the midpoint of the arrays get removed
				walls.remove(x + 1);
			}
		}
		
		// Insertion last element to correct index
		double last = arr[arr.length - 1];
		int index = arr.length - 1;
		
		for (int x = arr.length - 1; x > 1; x--) {
			if (last < arr[x]) {
				arr[x + 1] = arr[x];
				index = x;
			}
		}
		arr[index] = last;
	}
	
	/**
	 * Finds runs and sorts all nonruns. Creates an arraylist of ranges for the runs
	 * @param arr Array to find runs in
	 * @param runsize Size of each run
	 * @return walls Ranges of the runs
	 */
	public static ArrayList<Integer> findRuns(double[] arr, int runsize) {
		ArrayList<Integer> walls = new ArrayList<Integer>();
		int count = 1;
		double min = Double.MIN_VALUE;
		int runstart = 0;
		int runend = 0;
		int nonrunstart = runstart;
		
		//Find runs
		for (int x = 1; x < arr.length; x++) {
			min = arr[x - 1];
			runend = x - 1;
			if (min <= arr[x]) { //When the elements in arr is incrementing
				
				count = 1;
				min = arr[x];
				
				// Checks if elements always increment until the number of ascending elements equal runsize
				while (x < arr.length && count < runsize && min <= arr[x]) {
					min = arr[x];
					runstart = x;
					count++;
					x++;
				}
				
				if (count == runsize) { // If number of ascending elements equal runsize
					QuickSort.sort(arr, nonrunstart, runend - 1);
					nonrunstart = runstart;
					walls.add(runend);
					walls.add(runstart);
				}
			}
		}
		
		// Makes sure any nonrun elements at the end of the array is sorted
		QuickSort.sort(arr, nonrunstart, arr.length - 1);
		
		if (!walls.contains(arr.length - 1)) { // Creates a wall at the end of array
			walls.add(arr.length - 1);
		}
		
		if (!walls.contains(0)) { // Creates wall at beginning of array
			walls.add(0, 0);
		}
		
		return walls;
	}
	
	/**
	 * Merges two arrays while comparing values to sort
	 * Borrowed from merge sort program
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
