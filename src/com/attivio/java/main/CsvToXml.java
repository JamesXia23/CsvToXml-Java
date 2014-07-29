package com.attivio.java.main;
import java.io.*;
import java.util.*;

import org.w3c.dom.*;

import com.attivio.java.parse.Converter;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
/**
 * CsvToXmlConverter.java
 * Purpose: Driver class that begins execution of Converter class and
 * notifies the user of successful or unsuccessful conversion of the given 
 * input file
 * @author shruti
 * @version 1.0
 * @since 2014-28-07
 *
 */

public class CsvToXml {
	/**
	 * This is the main method of the program which begins execution
	 * @param args not used
	 * @exception String representation of the exception
	 */
	public static void main(String[] args) {
		try {
			/* Initialize Converter class to process the given file*/	
			Converter con = new Converter();	
			int rowCount = con.convert("<input file>","<output file>");
			/* Check if the file was successfully parsed */
			if(rowCount > 0) {
				System.out.println("Finished Parsing");
				System.out.println("Rows parsed:"+ String.valueOf(rowCount));
			}
			else {
				/* Edge case: Incorrect format of file */
				System.out.println("Incorrect Format:Check the input CSV file for data");
			}
		}
		/* Show exception in console */
		catch(Exception exp) {
			System.out.println(exp.toString());
		}
	}

}
