package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WriteUserAvg {


	//Delimiter used in CSV file
	private static final String SPACE = " ";
	private static final String NEW_LINE_SEPARATOR = "\n";


	public void writeResultFile(HashMap<Integer, Double> result, String fileName) {
		FileWriter fileWriter = null;

		
		try {
			fileWriter = new FileWriter(fileName);
			
			Object[] userList = result.keySet().toArray();
			
			for (Object user : userList) {
				Integer userId = (int) user;
				Double itemId = result.get(userId);
				
//				fileWriter.append(userId.toString());
//				fileWriter.append(SPACE);
				fileWriter.append(itemId.toString());
				fileWriter.append(SPACE);
				fileWriter.append(NEW_LINE_SEPARATOR);
				
			}
			System.out.println("File was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in FileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	
	}
}
