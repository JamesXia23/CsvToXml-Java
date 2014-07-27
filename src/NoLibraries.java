import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class NoLibraries {
	BufferedReader reader = new BufferedReader(new InputStreamReader(
			Csv2Xml.class.getResourceAsStream("test.csv")));
	StringBuilder xml = new StringBuilder();
	String lineBreak = System.getProperty("line.separator");
	String line = null;
	List<String> headers = new ArrayList<String>();
	boolean isHeader = true;
	int count = 0;
	int entryCount = 1;
	xml.append("<root>");
	xml.append(lineBreak);
	while ((line = reader.readLine()) != null) {
		StringTokenizer tokenizer = new StringTokenizer(line, ",");
		if (isHeader) {
			isHeader = false;
			while (tokenizer.hasMoreTokens()) {
				headers.add(tokenizer.nextToken());
			}
		} else {
			count = 0;
			xml.append("\t<entry id=\"");
			xml.append(entryCount);
			xml.append("\">");
			xml.append(lineBreak);
			while (tokenizer.hasMoreTokens()) {
				xml.append("\t\t<");
				xml.append(headers.get(count));
				xml.append(">");
				xml.append(tokenizer.nextToken());
				xml.append("</");
				xml.append(headers.get(count));
				xml.append(">");
				xml.append(lineBreak);
				count++;
			}
			xml.append("\t</entry>");
			xml.append(lineBreak);
			entryCount++;
		}
	}
	xml.append("</root>");
	System.out.println(xml.toString());

}
