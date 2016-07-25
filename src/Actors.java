
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Actors {

	public static void generateOutputFile(String input, String output) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(input));
		try {
			 Path path = Paths.get(input);
			    try (Scanner scanner =  new Scanner(path)){
			    	  try (Writer writer = new BufferedWriter(new OutputStreamWriter(
			                  new FileOutputStream(output), "utf-8"))) {
						      String theName = "";	
						      while (scanner.hasNextLine()){
						        //process each line in some way
						        //sb.append(scanner.nextLine());
						    	  String line = scanner.nextLine();
						    	  if (line.length() > 0) {
							    	  if (line.charAt(0) != 9) { // First line that includes name
							    		  if (line.indexOf(9) != -1) {
								    		  theName = line.substring(0, line.indexOf(9));
								    		  String rest = line.substring(line.indexOf(9)).trim();
								    		  if (rest.indexOf(40) != -1) {
									    		  String title = rest.substring(0, rest.indexOf(40));
									    		  String year = rest.substring(rest.indexOf(40) + 1, rest.indexOf(40) + 5);
									    		  String result = theName + "\t" + title + "\t" + year + "\n";
									    		  writer.write(result);
										    	  System.out.println(result);
								    		  }
							    		  }
							    	  } else { // Rest of the lines
							    		  if (line.indexOf(9) != -1) {
								    		  String s = line.substring(line.indexOf(9)).trim();
								    		  if (s.indexOf(40) != -1) {
								    			  String title = s.substring(0, s.indexOf(40));
								    			  String year = s.substring(s.indexOf(40) + 1, s.indexOf(40) + 5);
								    			  String result = theName + "\t" + title + "\t" + year + "\n";
								    			  writer.write(result);
										    	  System.out.println(result);
								    		  }
							    		  }
							    	  }
						    	  }
						    }
			      }   
		    }
		} finally {
		    br.close();
		}
	}
}



