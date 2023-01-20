package ie.atu.sw;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.Executors;

// PAGES : This class is responsible for generating arrays based on the page numbers of the given text
public class Pages {

	// PARSE PAGES : This method returns an array of strings where each string is a
	// page of the given text
	public static ArrayList<String> parsePages(String inputFileLocation) {
		// Call the input reader method to convert the text file path given to a string
		String textString = InputReader.convertInputFileToString(inputFileLocation);
		// Call a private method that returns an array of strings (one string per page)
		// from a given lower case string (the full text string)
		return stringToPagesArray(textString.toLowerCase());
	}

	// STRING TO PAGES ARRAY : This method takes the text string and returns an
	// array of strings (one string per page)
	private static ArrayList<String> stringToPagesArray(String textString) {
		// Create an array to store Page Strings
		ArrayList<String> pagesArray = new ArrayList();
		int sentanceCount = 0;
		String pageToWriteToArray = " ";

		// Loop over each letter in the text string
		for (int letter = 0; letter < textString.length(); letter++) {

			char currentLetter = textString.charAt(letter);
			int charAsci = currentLetter;

			// If the current character is a letter or a space add it to the page string
			if ((charAsci < 122 && charAsci > 96) || charAsci == 32) {
				pageToWriteToArray = pageToWriteToArray + currentLetter;
			}

			// If the current character ends a sentence increase the sentence count
			if (currentLetter == '.' || currentLetter == '!' || currentLetter == '?') {
				sentanceCount++;
			}

			// If the sentence count has reached 40 add the page string to the array
			if (sentanceCount == 40) {
				pagesArray.add(pageToWriteToArray);
				sentanceCount = 0;
				pageToWriteToArray = " ";
			}

			// If the character is the last character add the string to the array
			if (letter == (textString.length() - 1)) {
				pagesArray.add(pageToWriteToArray);
			}

		}

		// Return the Pages Array
		return pagesArray;

	}

	// BUILD WORDS PER PAGE ARRAY : This method returns an array of words on each page using virtual threads
	public static ArrayList buildWordsPerPageArray(ArrayList pagesArray, ArrayList commonWordsArray) throws Exception {

		ArrayList wordsPerPageArray = new ArrayList();

		// Loop over each page string and start a virtual thread to process each page string
		for (int pageNumber = 0; pageNumber < pagesArray.size(); pageNumber++) {

			Pages.virtualThread(pageNumber, wordsPerPageArray, pagesArray.get(pageNumber).toString(), commonWordsArray);

		}

		return wordsPerPageArray;

	}

	// VIRTUAL THREAD : This method creates a virtual thread to process a page string
	public static void virtualThread(int pageNumber, ArrayList wordsPerPageArray, String pageText,ArrayList commonWordsArray) throws Exception {

		try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {

			// Use the virtual thread to execute the method to process the page string
			pool.execute(() -> processWordsPerPage(pageNumber, wordsPerPageArray, pageText, commonWordsArray));

		}

	}

	// PROCESS WORDS PER PAGE : This method creates an array of words on a particular page and adds it to the array of words for all pages
	public static void processWordsPerPage(int pageNumber, ArrayList wordsPerPageArray, String pageText,ArrayList commonWordsArray) {
		
		// Create an Array to store the words from the current page
		ArrayList wordsFromCurrentPageArray = new ArrayList();

		String word = "";

		// Loop over the page text string to add each word to the array
		for (int letter = 0; letter < pageText.length(); letter++) {

			char currentLetter = pageText.charAt(letter);
			int currentLetterAsci = currentLetter;

			// If the current character is a new line or the last character then add the word to the array
			if ((currentLetterAsci == 32 && word != "" && word != " ") || (letter == pageText.length() - 1 && word != "" && word != " ")) {
				
				// If the current character is not a space
				if (currentLetterAsci != 32) {
					word = word + currentLetter;
				}
				
				// Remove any spaces
				word = word.trim();
				
				// Do not add the word to the array if it is already in the array or if it is a common word
				if (!Pages.wordIsInArray(word,wordsFromCurrentPageArray) && !Pages.wordIsInArray(word, commonWordsArray)) {
				wordsFromCurrentPageArray.add(word);
				}
				
				// Set the word to empty for the next loop
				word = "";
			}
			
			// Else add the current character to the word if it is not the last letter
			else if (currentLetterAsci < 122 && currentLetterAsci > 96 && letter != pageText.length() - 1) {
				word = word + currentLetter;
			}

		}
		
		//Add the array of words for the current page to the Words Per Page Array at the index of the current page
		wordsPerPageArray.add(pageNumber, wordsFromCurrentPageArray);
	}
	
	// WORD IS ALREADY IN ARRAY : This method checks if a word is in an array and returns true or false
	public static boolean wordIsInArray(String wordToCheck, ArrayList arrayToCheck) {
		
		// Loop over each word in array
		for (int i = 0; i < arrayToCheck.size();i++) {

			// If the word to check is in the array return true
			if (wordToCheck.equals(arrayToCheck.get(i).toString())) {
				return true;
			}
			
		}
		
		// If word is not in array return false
		return false;
	}
	

}
