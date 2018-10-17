
/**
 * Modified from original author @author David Guy Brizan
 * @author Andrew
 *
 */
public class SortingFactory {

	public final String defaultAlgorithm = "newsort";  // If an improper algorithm name is given, which should be used?
	public boolean useDefault = false;   // ... and should it be done?

	
	/**
	 * Constructor
	 * @param returnDefault
	 */
	public SortingFactory(boolean returnDefault) {
		// If the client does not provide a proper algorithm name: use the default, or return an error?
		useDefault = returnDefault;
	}
	
	
	/**
	 * Gets a SortingAlgorithm instance according to the parameter.
	 * @param algoName Sorting algorithm name; one of: selection, bubble, insertion, quick, merge.
	 * @return An instance of the sorting algorithm.
	 * @throws Exception If the name of the algorithm is invalid.
	 */
	public SortingAlgorithm getSortingAlgorithm(String algoName) throws Exception {
		String lowercaseAlgoName = algoName.toLowerCase();
		if (lowercaseAlgoName.contains("newsort")) {
			return new NewSort();
		}
		if (lowercaseAlgoName.contains("mergesort")) {
			return new MergeSort();
		}
		
		// Invalid algorithm name specified. Use default?
		if (useDefault) {
			System.out.println("Invalid algorithm name " + algoName + ". Returning " + defaultAlgorithm);
			return getSortingAlgorithm(defaultAlgorithm);
		}
		else {
			throw new Exception("Invalid algorithm name specified");
		}
	}


}
