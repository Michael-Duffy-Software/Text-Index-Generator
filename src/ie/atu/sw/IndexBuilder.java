package ie.atu.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// INDEX BUILDER : This class is responsible for calling static methods to build the index
public class IndexBuilder {

	// Instance variables : Store File Locations and Arrays Generated
	private String inputFileLocation = "";
	private String outputFileLocation = "";
	private String dictionaryFileLocation = "";
	private String commonWordsFileLocation = "";
	private ArrayList pagesArray;
	private ArrayList commonWordsArray;
	private ArrayList wordsPerPageArray;
	TreeMap<String, ArrayList> wordIndex;
	HashMap<String,String> dictionary;

// INDEX BUILDER : This constructor sets instance variables and calls the build index arrays method
	public IndexBuilder(String inputFileLocation, String outputFileLocation, String dictionaryFileLocation,
			String commonWordsFileLocation) throws Exception {
		this.inputFileLocation = inputFileLocation;
		this.outputFileLocation = outputFileLocation;
		this.dictionaryFileLocation = dictionaryFileLocation;
		this.commonWordsFileLocation = commonWordsFileLocation;
		this.buildIndexArrays();

	}

	// BUILD INDEX ARRAYS: This method uses the file locations given to build arrays
	private void buildIndexArrays() throws Exception {

		// Build the Pages Array Using the Input File Location
		this.pagesArray = Pages.parsePages(this.inputFileLocation);
		// Build the Common Words Array using the Common Words File Location
		this.commonWordsArray = CommonWords.buildCommonWordsArray(this.commonWordsFileLocation);
		// Build the Words Per Page Array Using the Pages Array and the Common Words Array
		this.wordsPerPageArray = Pages.buildWordsPerPageArray(this.pagesArray, this.commonWordsArray);
		// Build the Word Index Using the Words Per Page Array
		this.wordIndex = new TreeMap<String,ArrayList>(WordIndex.buildWordIndex(wordsPerPageArray));
		// Build the Dictionary HashMap Using the Dictionary Class
		this.dictionary = Dictionary.buildDictionary(dictionaryFileLocation);
		// Build an Output File Containing The Word Index and Dictionary Definitions
		OutputWriter.writeOutputFile(wordIndex,dictionary,outputFileLocation);
		// Let The User Know The Programme Is Complete
		System.out.println("Program Complete.");
		System.exit(0);
		
	}
}
