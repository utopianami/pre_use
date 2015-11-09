package wAls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.MakeRatingReadFile;
import util.ReadFile;
import util.ReadFileTestFile;
import util.WriteFile;
import util.WriteFileOCCF;
import zero_injection.MinMaxNorm;
import zero_injection.SortedHash;

public class OccfResult {

	public static void main(String[] args) {
		
		System.out.println("all");
		
		String path = "/Users/LeeYoungNam/Documents/pre_use/data";
		
		
		Double newMin = (double) 0.001;
		Double newMax = (double) 0.999;
		
		MinMaxNorm minMaxNorm = new MinMaxNorm(newMin, newMax); 
		
		ReadFileTestFile rf1 = new ReadFileTestFile();
		ReadFile rf = new ReadFile();
		
		for (int i = 1; i<=5; i++){
			
			//file io
			String crossValidation= String.valueOf(i);
			String u1Path = path+"/ml-100k/u"+crossValidation+".base";
			String occfResultPath = path+"/predict/u"+crossValidation+".predict";
			String writePath = path+"/occf_weight/percentage70zero/" + "/u"+crossValidation+ ".base";
			
			
			
			HashMap<Integer, HashMap<Integer, Double>> resutU1base = rf.readU1base(u1Path);
			Map<Integer, HashMap<Integer, Double>> result = rf.readOccfResult(occfResultPath, resutU1base);
			
			
			double globalMin = 999999;
			double globalMax = -999999;
			
			
			for (Object user : result.keySet().toArray()) {
				int userId = (int) user;
				Map<Integer, Double> sortedHash = SortedHash.getSrotedHash(result.get(userId));
				
				Object[] keys = sortedHash.keySet().toArray();
				int numberOfzeroItem = sortedHash.keySet().toArray().length;
				
				double localMin = sortedHash.get(keys[0]);
				double localMax = sortedHash.get(keys[numberOfzeroItem-1]);
				
				if ( localMin < globalMin){
					globalMin = localMin;
				}
				
				if (localMax > globalMax){
					globalMax = localMax;
				}
			}
			
			for (Object user : result.keySet().toArray()) {
				int userId = (int) user;
				Map<Integer, Double> sortedHash = SortedHash.getSrotedHash(result.get(userId));
				Object[] keys = sortedHash.keySet().toArray();
				
				
				for (Object item : keys) {
					int itemId = (int) item;
					Double curValue = sortedHash.get(itemId);
					double imputevalue =  1- minMaxNorm.calculate(globalMin, globalMax, curValue);
					
					resutU1base.get(userId).put(itemId, imputevalue);
				}
			}			
			
			WriteFileOCCF wf = new WriteFileOCCF();
			wf.writeResultFile(resutU1base, writePath);
			
		}

		System.out.println("end");
		
		
	}

}
