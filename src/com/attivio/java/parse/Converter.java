package com.attivio.java.parse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * Converter.java
 * Purpose: parse the given CSV file and generate the required XML document
 * @author shruti
 * @version 1.0
 * @since 2014-28-07
 */


public class Converter {
	
	/* Protected members to avoid instantiation */
	protected DocumentBuilderFactory domFactory = null;
	protected DocumentBuilder domBuilder = null;
	/* Constant strings */
	// Input CSV file
	final String INPUT_FILE = "in.csv";
	// Output XML document
	final String OUTPUT_FILE ="out.xml";
	// First element in the XML document
	final String FIRST_ELEMENT="CsvToXml";
	/* class constructor
	 * This is used to Create a parser for the DOM
	 * @exception FactoryConfigurationError indicating factory configuration error
	 * @exception ParserConfigurationException indicating configuration error */
	public Converter(){
		try {
			domFactory = DocumentBuilderFactory.newInstance();
			/* Obtaining instance of class DocumentBuilder */
			domBuilder = domFactory.newDocumentBuilder();
		}
		catch(ParserConfigurationException exp) {
			System.err.println(exp.toString());
			
		}
		catch(FactoryConfigurationError exp){
			System.err.println(exp.toString());
		}
		catch(Exception exp){
			System.err.println(exp.toString());
		}
	}
	/**
	 * This method converts the given CSV file into an XML document
	 * @param String csvFileName This is the file name of the CSV file that 
	 * needs to be converted into an XML file
	 * @param String xmlFileName This is file name of the XML file that will contain 
	 * the XML format of the file content of the input CSV file
	 * @return int returns the number of rows successfully parsed and converted 
	 * into XML from the given CSV file.
	 * @exception IOException on input error
	 * @exception String representation of exception
	 */
	public  int convert(String csvFileName, String xmlFileName) {
		int rowCount = -1;
		try {
			/* Initializing the XML document  */
			Document newDoc = domBuilder.newDocument();
			/* Creating the root element in the XML */
			Element rootElem = newDoc.createElement(FIRST_ELEMENT);
			newDoc.appendChild(rootElem);
			/* Reading the CSV file */
			BufferedReader csvFileReader;
			csvFileName = INPUT_FILE;
			csvFileReader = new BufferedReader(new FileReader(csvFileName));
			/* Initialize the number of fields to 0 */
			int fieldCount = 0;
			String[] csvFields = null;
			StringTokenizer stringTokenizer = null;
			
			/**
			 * Map the column names in the CSV file as the elements in the XML
			 * document, eliminate any other characters not eligible for XML element
			 * naming
			 */
			/* Initialize the current line variable */
			String currLine = csvFileReader.readLine();
			/* Loop until we reach the end of the file 
			 * edge case: Empty CSV file
			 * */
			
			if(currLine != null) {
				/* Separate fields based on commas */
				stringTokenizer = new StringTokenizer(currLine, ",");
				fieldCount = stringTokenizer.countTokens();
				/* If there is data in the CSV file */
				if(fieldCount > 0) {
					/* Initialize a String Array of Fields */
					csvFields = new String[fieldCount];
					int i = 0;
					/* Loop till all elements are found and save fields */
					while (stringTokenizer.hasMoreElements()) {
						csvFields[i++] = String.valueOf(stringTokenizer.nextElement());
					}
				}
				
			}
			else {
				System.out.println("Nothing to parse");
			}
			/* reading rows from the CSV file */
			while((currLine = csvFileReader.readLine()) != null) {
				stringTokenizer = new StringTokenizer(currLine, ",");
				fieldCount = stringTokenizer.countTokens();
				/* if rows exist in the CSV file*/
				if(fieldCount > 0) {
					/* Create the row element*/
					Element rowElem = newDoc.createElement("row");
					int i = 0;
					/* until there are more elements*/
					while(stringTokenizer.hasMoreElements()) {
						try {
							/* Append each element found to each row element*/
							String currValue = String.valueOf(stringTokenizer.nextElement());
							Element currElem = newDoc.createElement(csvFields[i++]);
							currElem.appendChild(newDoc.createTextNode(currValue));
							rowElem.appendChild(currElem);
						}
						catch(Exception exp) {
			
						}
					}
					/* Append the rows to the root element*/
					rootElem.appendChild(rowElem);
					rowCount++;
				}
			}
			/* Finish reading the CSV file */
			csvFileReader.close();
			
			/* Saving the generated XML doc into required format file to disk */
			TransformerFactory tranFactory = TransformerFactory.newInstance(); 
            Transformer aTransformer = tranFactory.newTransformer(); 
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
            aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            Source src = new DOMSource(newDoc);
            xmlFileName = OUTPUT_FILE;
            Result dest = new StreamResult(new File(xmlFileName)); 
            aTransformer.transform(src, dest); 
            
			rowCount++;
		}
		catch(IOException exp) {
			System.err.println(exp.toString());
		}
		catch(Exception exp) {
			System.err.println(exp.toString());
		}
		/* Number of rows parsed into XML */
		return rowCount;
		
	}

}
