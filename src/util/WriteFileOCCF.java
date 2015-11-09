package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WriteFileOCCF {

	//Delimiter used in CSV file
	private static final String SPACE = " ";
	private static final String NEW_LINE_SEPARATOR = "\n";


	public void writeResultFile(HashMap<Integer, HashMap<Integer, Double>> resutU1base, String fileName) {
		FileWriter fileWriter = null;
		
		String imputeValue = "0";

		
		try {
			fileWriter = new FileWriter(fileName);
			
			Object[] userList = resutU1base.keySet().toArray();
			
			for (Object user : userList) {
				Integer userId = (int) user;
				Object[] itemList = resutU1base.get(userId).keySet().toArray();
				
				for (Object item : itemList) {
					Integer itemId = (int) item;
					Double value = resutU1base.get(userId).get(itemId);
					
					fileWriter.append(userId.toString());
					fileWriter.append(SPACE);
					
					if (value < 1.0){
						fileWriter.append(itemId.toString());
						fileWriter.append(SPACE);
						
						fileWriter.append(value.toString());
						fileWriter.append(SPACE);
						
						fileWriter.append(imputeValue);
						fileWriter.append(SPACE);
						
					}else{
						fileWriter.append(item.toString());
						fileWriter.append(SPACE);
						
						fileWriter.append("1.0");
						fileWriter.append(SPACE);
						
						fileWriter.append(value.toString());
						fileWriter.append(SPACE);						
					}
					fileWriter.append(NEW_LINE_SEPARATOR);
					

					
				}
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
