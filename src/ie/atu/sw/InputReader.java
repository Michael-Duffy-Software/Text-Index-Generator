package ie.atu.sw;

import java.io.File;
import java.io.FileInputStream;

// INPUT READER : This class is responsible for reading input from file locations
public class InputReader {
	
	// CONVERT INPUT FILE TO STRING : This Method Opens The File Specified By The User And Returns A String Containing The Contents of The File
			public static String convertInputFileToString(String inputFileLocation) {

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
					// Create A String To Store Contents of Input File
					String inputString = "";

					// Read Input File
					int reading = 0;

					while ((reading = inputStream.read()) != -1) {
						// Write Each Character of Input File To Input String
						inputString = inputString + ((char) reading);
					}

					// Close The Input File Stream
					inputStream.close();
					
					// Let The User Know The Input File Has Been Read
					System.out.println("Input File Has Been Read : " + inputFileLocation);

					// Return The Input File String
					return inputString;

				}

				// Input File Could Not Be Opened
				catch (Exception e) {
					System.out.println();
					System.out.println("Could Not Open Input File : " + inputFileLocation);
					System.out.println("Program Quitting...");
					System.exit(0);
					return "FAILED";
				}

			}

}
