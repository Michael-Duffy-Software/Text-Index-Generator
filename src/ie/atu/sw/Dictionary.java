package ie.atu.sw;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

// DICTIONARY : This class is responsible for creating a hashmap of dictionary words and definitions
public class Dictionary {

	// BUILD DICTIONARY : This method uses the dictionary file location to build a hash map
	public static HashMap<String, String> buildDictionary(String dictionaryFileLocation) {

		// Create a hashmap to store the dictionary
		HashMap<String, String> dictionaryHashMap = new HashMap();
		// Call the parse CSV method to read the dictionary file location and create a hash map
		Dictionary.parseCsvToHashMap(dictionaryFileLocation, dictionaryHashMap);
		return dictionaryHashMap;
	}

	// PARSE CSV TO HASH MAP : This method takes a file location and hashmap and parses words from the file to add to the hashmap
	public static void parseCsvToHashMap(String inputFileLocation, HashMap dictionaryHashMap) {

		// Attempt To Open The Input File
		try {
			// Let The User Know the File Is Being Read
			System.out.println("Reading Input File : " + inputFileLocation);
			// Determine The Location and Name of Input File From Settings Given By User
			String fileToOpen = inputFileLocation;
			// Open Input File
			File inputFile = new File(fileToOpen);
			// Prepare To Read From Input File
			FileInputStream inputStream = new FileInputStream(inputFile);
			// Create A String To Store The Key
			String keyString = "";
			// Create A String To Store The Value
			String valueString = "";
			// Record if the key has been completely written : this allows for the loop to distinguish between the key and the value
			boolean keyIsWritten = false;

			// Read Input File
			int reading = 0;

			// Read each character in the input file
			while ((reading = inputStream.read()) != -1) {

				// Store the current character
				char currentCharacter = (char) reading;
				// Store the asci value of the current character
				int currentCharacterAsci = currentCharacter;
				
				// If the key has not yet been completely written add the current character to the key
				if (!keyIsWritten) {

					if (currentCharacter != ',')
						keyString = keyString + currentCharacter;

					// If the current character is a comma then the key has been completely written
					else if (currentCharacter == ',') {
						keyIsWritten = true;
						
					}

				// If the key has been completely written the write the characters to the value
				} else if (keyIsWritten) {

					// If the current character is not a new line then add the current character to the value string
					if (currentCharacterAsci != 10)
						valueString = valueString + currentCharacter;

					// If the current character is a new line then add both the key and value to the haskmap and set the key and value to empty for the next loop
					else if (currentCharacterAsci == 10) {
						keyIsWritten = false;
						valueString = valueString.trim();
						valueString = valueString.toLowerCase();
						keyString = keyString.trim();
						keyString = keyString.toLowerCase();
						dictionaryHashMap.put(keyString, valueString);
						keyString = "";
						valueString = "";
					}

				}

			}

			// Close The Input File Stream
			inputStream.close();

			// Let The User Know The Input File Has Been Read
			System.out.println("Input File Has Been Read : " + inputFileLocation);

		}

		// Input File Could Not Be Opened
		catch (Exception e) {
			System.out.println();
			System.out.println("Could Not Open Input File : " + inputFileLocation);
			System.out.println("Program Quitting...");
			System.exit(0);
		}

	}

}
