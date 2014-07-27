import java.util.*;
import au.com.bytecode.opencsv.CSVReader;
import com.thoughtworks.xstream.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

class CsvToXml{
	public static void main(String[] args){
		String startFile = "./startData.csv";
        String outFile = "./outData.xml";

        try {
            CSVReader reader = new CSVReader(new FileReader(startFile));
            String[] line = null;

            String[] header = reader.readNext();

            List out = new ArrayList();

            while((line = reader.readNext())!=null){
                List<String[]> item = new ArrayList<String[]>();
                    for (int i = 0; i < header.length; i++) {
                    String[] keyVal = new String[2];
                    String string = header[i];
                    String val = line[i];
                    keyVal[0] = string;
                    keyVal[1] = val;
                    item.add(keyVal);
                }
                out.add(item);
            }

            XStream xstream = new XStream();

            xstream.toXML(out, new FileWriter(outFile,false));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		
	}
}