package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MakeRatingReadFile {
	
	private static final String SPACE = " ";
	private static final String NEW_LINE_SEPARATOR = "\n";
	FileWriter fileWriter = null;

	
	public void readU1base(String fileName, String path) {
		String file = path;
		BufferedReader br = null;
		String line = "";
		
		try {
			fileWriter = new FileWriter(fileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(fileName);
		System.out.println(path);
		int num = 0;
		try {
			
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				
				String[] cell = line.split("\\s+");

				Integer userId = Integer.parseInt(cell[0]);
				Integer itemId = Integer.parseInt(cell[1]);
				Integer value = Integer.parseInt(cell[2]);
				
				fileWriter.append(userId.toString());
				fileWriter.append(SPACE);
				
				fileWriter.append(itemId.toString());
				fileWriter.append(SPACE);
				
				fileWriter.append(value.toString());
				fileWriter.append(SPACE);
				fileWriter.append(NEW_LINE_SEPARATOR);
				
				num =+1 ;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
							} catch (IOException e) {
								e.printStackTrace();
								}
						}
					}
		
		try {
			fileWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(num);
		System.out.println("Done");
	}
	
	
	

}
