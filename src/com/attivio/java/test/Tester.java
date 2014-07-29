package com.attivio.java.test;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.JUnit4;

import com.attivio.java.parse.Converter;
/**
 * fileTest.java
 * Purpose: Tests the convert() method responsible for converting CSV file to XML
 * @author shruti
 * @version 1.0
 * @since 2014-28-07
 */
public class Tester {

	/*  Test Method: 
	 * 	Tests the convert() method, when a CSV file with 5 rows is given as input,
	 *  the method returns  an Integer 5, indicating the number of rows converted 
	 *  into CSV
	 *  @return Nothing
	 *  @param Nothing
	 * */
	// Constants
	public final int FIVE = 5;
	final String INPUT_FILE = "in.csv";
	final String OUTPUT_FILE ="out.xml";
	@Test
	public void FiveRowsTest() {
		Converter test = new Converter();
		/* Testing a SV file with 5 rows */
		int result = test.convert(INPUT_FILE,OUTPUT_FILE);
		assertEquals(FIVE,result);
		
	}
	
}
