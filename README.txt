README File: CSV to XML converter software
---------------------------------------------------
Software Developer:
Shruti Palshikar
---------------------------------------------------
INSTALLATION AND CONFIGURATION
---------------------------------------------------
Pre requisites: 
-Java version 7 or higher
-Operating System : Windows
-File named "in.csv" should be present in the extracted folder  

ECLIPSE USAGE
1. Import the extracted folder into workspace
2. Run the project as a java application
3. Output file will be created in the project folder as 'out.xml'
--------------------
DEPENDENCIES
--------------------
Dependent jars can be found under the user library 'attivio' under the extracted folder

-------------------
ASSUMPTIONS:
-------------------
The file is named "in.csv" and is present  the project folder
(if using the eclipse method)
The first line in the CSV is assumed to be the column names before parsing

---------------------------
CLASS STRUCTURE
---------------------------
CsvToXml.java:(Under the package om.attivio.java.main) This is the driver class of the software containing the main() method. 
This class instantiates another class 'Convert', calling the respective method to process the given files and notifies the user 
if the file was successfully parsed stating the number of lines of the CSV files that were successfully converted into XML format

Converter.java: (Under the package om.attivio.java.parse) This class does the conversion of the input CSV file, 
reading it line by line and saving the generated output in a file called 'out.xml'.
This method returns an integer representing the number of rows that were successfully parsed as an indicator 
of whether or not the file was processed successfully

Tester.java:(Under the package om.attivio.java.test) This class tests the covert() method in the Converter class 
by providing it with a sample CSV file (in.csv)

in.csv: Sample input CSV file for XML generation(this contains 5 rows)

out.xml: Generated XML from in.csv file after successful execution

--------------------------
SPECIAL SITUATIONS:
---------------------------
Case A: Empty File
The program runs with a notification indicating that the file was empty

Case B: Values not comma separated in the input CSV file
The program considers it a valid CSV file with a single value and generates the corresponding XML

Case C: Incorrect file format of the CSV file
The program throws a "fileNotFound" exception. In this case one needs to check if the file is in correct format(.csv)

Case D: File not present in the root folder
The program throws a "fileNotFound" exception. In this case one should double check if the file is present in the specified folder

------------
TESTING:
------------
In order to test the program for the above mentioned cases, one needs to update the sample "in.csv" file that comes within the .rar version
------------------------------------------------------------------------------
IMPROVEMENTS AND ENHANCEMENTS IN THE NEXT VERSION
------------------------------------------------------------------------------
1. Executable version instead of eclipse set up
2. Web interface enabling user to upload a CSV file from their disk
3. Customized delimiter(comma, semicolon  etc)
4. Customized indentation of the XML document
5. Preview of the generated XML document
6. Ability to save the generated XML into any location on disk

-------------------------
TECHNIAL SUPPORT
-------------------------
shruti.palshikar@gmail.com