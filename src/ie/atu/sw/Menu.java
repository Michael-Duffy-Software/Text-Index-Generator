package ie.atu.sw;

import java.util.Scanner;

//MENU : This class is responsible for the command line menu options
public class Menu {

	// Instance Variables : To Store File Path Information
	private String inputFileLocation = "Unspecified";
	private String outputFileLocation = "Unspecified";
	private String dictionaryFileLocation = "Unspecified";
	private String commonWordsFileLocation = "Unspecified";
	Scanner input = new Scanner(System.in);

	// MENU CONSTRUCTOR : Print the menu options when menu is first created
	public Menu() throws Exception {
		this.printMenuHeader();
	}

	// PRINT MENU HEADER : This method prints the menu header and calls the print
	// menu options method
	public void printMenuHeader() throws Exception {
		System.out.println("************************************************************");
		System.out.println("*       ATU - Dept. Computer Science & Applied Physics     *");
		System.out.println("*                                                          *");
		System.out.println("*              Virtual Threaded Text Indexer               *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		this.printOptionsMenu();
	}

	// PRINT OPTIONS MENU : This method prints the menu options and calls the select
	// menu option method
	public void printOptionsMenu() throws Exception {
		System.out.println();
		System.out.println("(1) Specify Text File Location");
		System.out.println("(2) Specify Dictionary File Location");
		System.out.println("(3) Specify Output File Location");
		System.out.println("(4) Specify Common Words File Location");
		System.out.println("(5) Execute");
		System.out.println("(6) Quit");
		System.out.println();
		this.selectMenuOption();
	}

	// SELECT MENU OPTION : This method waits for the user to select a menu option
	// and calls the appropriate method for the option selected
	public void selectMenuOption() throws Exception {
		boolean waitForInput = true;

		// Loop until acceptable option is given
		while (waitForInput) {
			System.out.println("Select an Option :");
			String optionSelectedString = input.nextLine();

			// Option 1 one was selected
			if (optionSelectedString.equals("1")) {
				waitForInput = false;
				this.specifyTextFile();
			}

			// Option 2 one was selected
			if (optionSelectedString.equals("2")) {
				waitForInput = false;
				this.configureDictionary();
			}

			// Option 3 one was selected
			if (optionSelectedString.equals("3")) {
				waitForInput = false;
				this.specifyOutputFile();
			}

			// Option 4 one was selected
			if (optionSelectedString.equals("4")) {
				waitForInput = false;
				this.specifyCommonWordsFile();
			}

			// Option 5 one was selected
			if (optionSelectedString.equals("5")) {
				waitForInput = false;
				this.execute();
			}

			// Option 6 one was selected
			if (optionSelectedString.equals("6")) {
				waitForInput = false;
				this.quit();
			}

		}

	}

	// SPECIFY TEXT FILE : This method displays the current text file location and
	// allows the user to update the location then returns to the options menu
	public void specifyTextFile() throws Exception {
		System.out.println();
		System.out.println("Current Text File Location : " + this.inputFileLocation);
		System.out.println("Enter New Text File Location :");
		this.inputFileLocation = input.nextLine();
		System.out.println("Current Text File Location : " + this.inputFileLocation);
		this.printOptionsMenu();
	}

	// CONFIGURE DICTIONARY: This method displays the current dictionary file
	// location and allows the user to update the location then returns to the
	// options menu
	public void configureDictionary() throws Exception {
		System.out.println();
		System.out.println("Current Dictionary File Location : " + this.dictionaryFileLocation);
		System.out.println("Enter New Dictionary File Location :");
		this.dictionaryFileLocation = input.nextLine();
		System.out.println("Current Dictionary File Location : " + this.dictionaryFileLocation);
		this.printOptionsMenu();
	}

	// SPECIFY OUTPUT FILE : This method displays the current output file location
	// and allows the user to update the location then returns to the options menu
	public void specifyOutputFile() throws Exception {
		System.out.println();
		System.out.println("Current Output File Location : " + this.outputFileLocation);
		System.out.println("Enter New Output File Location :");
		this.outputFileLocation = input.nextLine();
		System.out.println("Current Output File Location : " + this.outputFileLocation);
		this.printOptionsMenu();
	}

	// SPECIFY COMMON WORDS FILE: This method displays the current common words file
	// location and allows the user to update the location then returns to the
	// options menu
	public void specifyCommonWordsFile() throws Exception {
		System.out.println();
		System.out.println("Current Common Words File Location : " + this.commonWordsFileLocation);
		System.out.println("Enter New Common Words File Location :");
		this.commonWordsFileLocation = input.nextLine();
		System.out.println("Current Common Words File Location : " + this.commonWordsFileLocation);
		this.printOptionsMenu();
	}

	// EXECUTE : This methods first checks if file locations have been given by the
	// user and then calls the index builder method
	public void execute() throws Exception {

		// Check if any file path locations are unspecified and if so return to options
		// menu
		if (this.inputFileLocation.equals("Unspecified") || this.outputFileLocation.equals("Unspecified")
				|| this.dictionaryFileLocation.equals("Unspecified")
				|| this.commonWordsFileLocation.equals("Unspecified")) {
			System.out.println("");
			System.out.println("Settings Must Be Specified Before Executing :");
			System.out.println("Text File Location : " + this.inputFileLocation);
			System.out.println("Dictionary File Location : " + this.dictionaryFileLocation);
			System.out.println("Output File Location : " + this.outputFileLocation);
			System.out.println("Common Words File Location : " + this.commonWordsFileLocation);
			System.out.println("");
			this.printOptionsMenu();
		} else {
			// Create the index builder
			new IndexBuilder(this.inputFileLocation, this.outputFileLocation, this.dictionaryFileLocation,
					this.commonWordsFileLocation);
		}
	}

	// QUIT : This method exits the programme
	public void quit() {
		System.out.println("Programme Quiting...");
		System.exit(0);

	}

}
