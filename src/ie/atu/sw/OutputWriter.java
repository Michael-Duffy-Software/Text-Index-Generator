package ie.atu.sw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// OUTPUT WRITER : This class is responsible for writing the text file to the given location by the user
public class OutputWriter {

	// WRITE OUTPUT FILE : This method writes the text file to the given location by the user using the wordIndex, dictionary and output file location
	public static void writeOutputFile(TreeMap<String, ArrayList> wordIndex, HashMap<String, String> dictionary, String outputFileLocation) throws IOException {

		// Create a file output string to write the file to
		String fileToOutput = outputFileLocation + "\\Text Index.txt";
		
		// prepare a file to write to
		File file = new File(fileToOutput);
		file.setWritable(true);
		file.setReadable(true);
		FileWriter fileWriter = new FileWriter(file);

		// Loop over each word in the word index
		for (Map.Entry<String, ArrayList> entry : wordIndex.entrySet()) {
			
			// Store the value of the current word
			String word = entry.getKey();
			// Store the value of the pages
			String pages = entry.getValue().toString();
			// Store the dictionary value of the current word by searching the dictionary array
			String dictionaryDefinition = dictionary.get(word);

			// If there is no dictionary definition store a default value
			if (dictionaryDefinition == null) {
				dictionaryDefinition = "No Dictionary Definition Found";
			}

			// Write the index information to the file
			fileWriter.append("WORD : " + word);
			fileWriter.append("\n");
			fileWriter.append("PAGES : " + pages);
			fileWriter.append("\n");
			fileWriter.append("DICTIONARY : " + dictionaryDefinition);
			fileWriter.append("\n");
			fileWriter.append("\n");

		}

		// Close the file
		fileWriter.flush();
		fileWriter.close();

	}

}
