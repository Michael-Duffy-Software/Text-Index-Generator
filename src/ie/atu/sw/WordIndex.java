package ie.atu.sw;

import java.util.ArrayList;
import java.util.HashMap;

// WORD INDEX : This class is responsible for building a HashMap that contains words as keys and an array list of page numbers the word appears on as values
public class WordIndex {
	
	// BUILD WORD INDEX : This method loops over each page string and calls a method to parse the words on the page to the hash map
	public static HashMap buildWordIndex(ArrayList<ArrayList> wordsPerPageArray) {
		HashMap<String,ArrayList> wordIndex = new HashMap();
		
		// Loop over each page
		for (int page = 0; page < wordsPerPageArray.size();page++) {
			// Call the parse page word method
			WordIndex.parsePageWordsToHashMap(wordsPerPageArray.get(page),wordIndex, page+1);
		}
		
		return wordIndex;
	}
	
	// PARSE PAGE WORDS TO HASH MAP : This method checks each word on the current page and adds the word and page number to the hash map
	private static void parsePageWordsToHashMap(ArrayList wordsOnCurrentPage,HashMap<String,ArrayList> wordIndex, int pageNumber) {
		
		// Loop over each word
		for (int i = 0; i < wordsOnCurrentPage.size();i++) {
			
			// Get the current word to check
			String currentWord = wordsOnCurrentPage.get(i).toString();
			
			// If the hash map does not already contain the word then add the word and the page number
			if (!wordIndex.containsKey(currentWord)) {
				ArrayList pageNumbersWithWord = new ArrayList();
				pageNumbersWithWord.add(pageNumber);
				wordIndex.put(currentWord, pageNumbersWithWord);
			}
			
			// If the hash map already contains the word then update the page number array list to add the current page number
			else if (wordIndex.containsKey(currentWord)) {
				ArrayList pageNumbersWithWord = wordIndex.get(currentWord);
				pageNumbersWithWord.add(pageNumber);
				wordIndex.replace(currentWord, pageNumbersWithWord);
			}
			
		}
		
	}

}
