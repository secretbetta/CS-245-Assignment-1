public class QuickSort {
    public static int partition(double arr[], int low, int high) {
    	double pivot = arr[high];
    	double temp;
    	int i = low - 1;
    	for (int j = low; j < high; j++) {
    		if (arr[j] <= pivot) {
				i++;
				
    			temp = arr[i];
    			arr[i] = arr[j];
    			arr[j] = temp;
    		}
    	}
    	
    	temp = arr[i + 1];
    	arr[i + 1] = arr[high];
    	arr[high] = temp;
    	
    	return i + 1;
    }
    
    /**
     * Main sort function
     * @param arr Array of doubles
     * @param low
     * @param high
     * @return 
     */
    public static void sort(double arr[], int low, int high) {
        if (low < high) {
        	int pi = partition(arr, low, high);
            
        	sort(arr, low, pi-1);
        	sort(arr, pi+1, high);
        }
    }

    /**
     * Prints array
     * @param arr Array of doubles
     */
    public static void printArray(double arr[]) {
    	int n = arr.length;
    	
        for (int i=0; i<n; ++i) {
        	System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    
}