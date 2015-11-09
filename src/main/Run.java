package main;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.ReadFile;
import util.WriteFile;
import zero_injection.SortedHash;

public class Run {

	public static void main(String[] args) {
		
		
		String path = "/Users/LeeYoungNam/Documents/pre_use/data";
		ArrayList<Integer> imputePercente = new ArrayList<Integer>();
		
		ArrayList<Double> imputeValues = new ArrayList<Double>();
		imputeValues.add(0.0);
		
		imputePercente.add(10);
		
		for (Integer percents : imputePercente){
			for (int i = 1; i<=5; i++){
				String crossValidation= String.valueOf(i);
				
				for (Double imputevalue : imputeValues) {
					
					String imputeVal = imputevalue.toString();
					String imputePercentstr = percents.toString();
					
					String u1Path = path+"/ml-100k/u"+crossValidation+".base";
					String occfResultPath = path+"/predict/u"+crossValidation+".predict";
					String writePath = path+"/mnar/"+"/u"+crossValidation+"_imputed.base";
					
					
					double tmp = percents.doubleValue();
					double percent = tmp * 0.1;
					
					
					ReadFile rf = new ReadFile();
					HashMap<Integer, HashMap<Integer, Double>> resutU1base = rf.readU1base(u1Path);
					Map<Integer, HashMap<Integer, Double>> result = rf.readOccfResult(occfResultPath, resutU1base);
					
					
					for (Object user : result.keySet().toArray()) {
						int userId = (int) user;
						Object[] sortUser = SortedHash.getItems(result.get(userId));
						
						int numberOfinjectionItem = (int) (sortUser.length * percent); 
						int tmpIdx = 0;
						for (Object item : sortUser) {
							int itemId = (int) item;
							resutU1base.get(userId).put(itemId, imputevalue);
//							
//							if (tmpIdx == numberOfinjectionItem)
//								break;
							
//							tmpIdx++;
						}
					}
					
					
					WriteFile wf = new WriteFile();
					wf.writeResultFile(resutU1base, writePath);
					
				}
			}
			
		}

		
		
		
	}

}
