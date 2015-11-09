package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadFileTestFile {
	
	public Map<Integer, ArrayList<Integer>> readTestDict(String path) {

		String file = path;
		BufferedReader br = null;
		String line = "";
		
		
		Map<Integer, ArrayList<Integer>> testDict = new HashMap<Integer, ArrayList<Integer>>();
		
		
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				
				String[] cell = line.split("\\s+");

				int userId = Integer.parseInt(cell[0]);
				int itemId = Integer.parseInt(cell[1]);
				int rating = Integer.parseInt(cell[2]);
				
				if (!testDict.keySet().contains(userId)){
					testDict.put(userId, new ArrayList<Integer>());
				}
				
				if (rating == 5){
					testDict.get(userId).add(itemId);
				}
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
		System.out.println("testDict Done");
		return testDict;
	}	
	
	
	public Map<Integer, ArrayList<Integer>> readRecommendDict(String path) {

		String file = path;
		BufferedReader br = null;
		String line = "";
		
		
		Map<Integer, ArrayList<Integer>> recommendDict = new HashMap<Integer, ArrayList<Integer>>();
		
		
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				
				String[] cell = line.split("\\s+");

				int userId = Integer.parseInt(cell[0]);
				int itemId1 = Integer.parseInt(cell[1]);
				int itemId2 = Integer.parseInt(cell[2]);
				int itemId3 = Integer.parseInt(cell[3]);
				int itemId4 = Integer.parseInt(cell[4]);
				int itemId5 = Integer.parseInt(cell[5]);
				
				
				recommendDict.put(userId, new ArrayList<Integer>());
				recommendDict.get(userId).add(itemId1);
				recommendDict.get(userId).add(itemId2);
				recommendDict.get(userId).add(itemId3);
				recommendDict.get(userId).add(itemId4);
				recommendDict.get(userId).add(itemId5);
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
		System.out.println("recommendDict Done");
		return recommendDict;
	}	

}
