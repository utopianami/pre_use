package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.ReadFile;
import util.WriteFile;
import zero_injection.MinMaxNorm;
import zero_injection.SortedHash;

public class MultipleImputationUser {

	public static void main(String[] args) {
		System.out.println("user");
		
		String path = "/Users/LeeYoungNam/Documents/pre_use/data";
		
		
		double percent = 0.9;
		Double newMin = (double) 1.0;
		Double newMax = (double) 3.0;
		
		MinMaxNorm minMaxNorm = new MinMaxNorm(newMin, newMax);
		String folderName = newMin.toString() + "_" + newMax.toString(); 
		
		

		
		for (int i = 1; i<=5; i++){
			
			//file io
			String crossValidation= String.valueOf(i);
			String u1Path = path+"/ml-100k/u"+crossValidation+".base";
			String occfResultPath = path+"/predict/u"+crossValidation+".predict";
			String writePath = path+"/MultipleImputation/user/"+ folderName + "/u"+crossValidation+ ".base";
			
			ReadFile rf = new ReadFile();
			HashMap<Integer, HashMap<Integer, Double>> resutU1base = rf.readU1base(u1Path);
			Map<Integer, HashMap<Integer, Double>> result = rf.readOccfResult(occfResultPath, resutU1base);
			

			
			
			for (Object user : result.keySet().toArray()) {
				int userId = (int) user;
				Map<Integer, Double> sortedHash = SortedHash.getSrotedHash(result.get(userId));
				
				Object[] keys = sortedHash.keySet().toArray();
				int numberOfzeroItem = sortedHash.keySet().toArray().length;
				
				int numberOfinjectionItem = (int) (numberOfzeroItem * percent); 
				int tmpIdx = 0;
				
				
				double localMin = sortedHash.get(keys[0]);
				double localMax = sortedHash.get(keys[numberOfinjectionItem]);
				
				
				for (Object item : keys) {
					int itemId = (int) item;
					Double curValue = sortedHash.get(itemId);
					double imputevalue =  minMaxNorm.calculate(localMin, localMax, curValue);
					
					resutU1base.get(userId).put(itemId, imputevalue);
					
					if (tmpIdx == numberOfinjectionItem)
						break;
					tmpIdx++;
					
				}
			}


			
			
			WriteFile wf = new WriteFile();
			wf.writeResultFile(resutU1base, writePath);
			
		}

		
		System.out.println("end");
		
	}

}
