public class MergeSort implements SortingAlgorithm {

	@Override
	public void sort(double[] a, int runsize) {
		sort(a, 0, a.length - 1);
	} 
	
	public void merge(double[] arr, int l, int m, int r) {
        int n1 = m - l + 1; 
        int n2 = r - m;
        
        double L[] = new double[n1]; 
        double R[] = new double[n2]; 
        
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i]; 
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1+ j]; 
        }
        
        int i = 0;
        int j = 0;
        
        int k = l; 
        while (i < n1 && j < n2) { 
            if (L[i] <= R[j]) { 
                arr[k] = L[i]; 
                i++; 
            } else { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        }
        
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        }
        
        while (j < n2) { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
	}
	
    public void sort(double[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r)/2; 
            
            sort(arr, l, m); 
            sort(arr , m + 1, r); 
            
            merge(arr, l, m, r); 
        } 
    }
    
    public static void printArray(double[] arr) {
    	for (int i = 0; i < arr.length; i++) {
    		System.out.print(arr[i] + " ");
    	}
        System.out.println();
    }
    
    public static void main(String args[]) 
    { 
        double[] arr = {12, 11, 13, 5, 6, 7}; 
  
        System.out.println("Given Array"); 
        printArray(arr); 
  
        MergeSort ob = new MergeSort(); 
        ob.sort(arr, 0); 
  
        System.out.println("\nSorted array"); 
        printArray(arr); 
    }
} 