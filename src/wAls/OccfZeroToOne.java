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

public class OccfZeroToOne {

	public static void main(String[] args) {
		
		System.out.println("all");
		
		String path = "/Users/LeeYoungNam/Documents/pre_use/data";
		
		
		Double percentage = 0.9;
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
			String writePath = path+"/occf_weight/percentage70two" + "/u"+crossValidation+ ".base";
			
			
			
			
			HashMap<Integer, HashMap<Integer, Double>> resutU1base = rf.readU1base(u1Path);
			Map<Integer, HashMap<Integer, Double>> result = rf.readOccfResult(occfResultPath, resutU1base);
			
			
			
			
	
			int counttmp=0;
			
			for (Object user : result.keySet().toArray()) {
				
				
				int userId = (int) user;
				Map<Integer, Double> sortedHash = SortedHash.getSrotedHash(result.get(userId));
				Object[] keys = sortedHash.keySet().toArray();
				int percentageLength = (int) (keys.length * percentage);
				int itemCount = 0;
				
				for (Object item : keys) {
					itemCount += 1;
					
					int itemId = (int) item;
					double imputevalue;
					
					if (itemCount < percentageLength){
						counttmp +=1;
						imputevalue = -1.0;
					}else
						imputevalue = 0.0;
					resutU1base.get(userId).put(itemId, imputevalue);
				}
			}			
			System.out.println(counttmp);
			
//			WriteFileOCCF wf = new WriteFileOCCF();
//			wf.writeResultFile(resutU1base, writePath);
			
		}

		System.out.println("end");
		
		
	}

}
