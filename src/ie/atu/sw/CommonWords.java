package ie.atu.sw;

import java.util.ArrayList;

// COMMOND WORDS : This class is responsible for building the common words array
public class CommonWords {

	// Build Common Words Array : This method returns a common words array from a given common words file location
	public static ArrayList buildCommonWordsArray(String commonWordsFileLocation) {

		// Call the Input Reader Method to return a string of the common words file contents
		String commonWordsString = InputReader.convertInputFileToString(commonWordsFileLocation);
		// Create an array to store the common words
		ArrayList<String> commonWordsArray = new ArrayList();
		String word = "";

		// Loop of the common words string to add each word to the array
		for (int letter = 0; letter < commonWordsString.length(); letter++) {

			char currentLetter = commonWordsString.charAt(letter);
			int currentLetterAsci = currentLetter;

			// If the current character is a new line then add the word to the array
			if (currentLetterAsci == 10) {
				commonWordsArray.add(word);
				word = "";
			}
			
			// If the current character is the last character add the word to the array
			if (letter == commonWordsString.length() - 1) {
				word = word + currentLetter;
				commonWordsArray.add(word);
				word = "";
			}

			// Else add the current character to the word
			else if (currentLetterAsci != 10) {
				word = word + currentLetter;
			}

		}

		// Return the common words array
		return commonWordsArray;

	}

}
