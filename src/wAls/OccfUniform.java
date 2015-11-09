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

public class OccfUniform {

	public static void main(String[] args) {
		
		System.out.println("all");
		
		String path = "/Users/LeeYoungNam/Documents/pre_use/data";
		
		
		Double percentage = 0.7;
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
			String writePath = path+"/occf_weight/uniformzero" + "/u"+crossValidation+ ".base";
			
			
			
			
			HashMap<Integer, HashMap<Integer, Double>> resutU1base = rf.readU1base(u1Path);
			Map<Integer, HashMap<Integer, Double>> result = rf.readOccfResult(occfResultPath, resutU1base);
			
			HashMap<Integer, Double> tmpResult = new HashMap<Integer, Double>();

			int tmp = 0;
			for (Object user : result.keySet().toArray()) {
				int userId = (int) user;
				Map<Integer, Double> sortedHash = SortedHash.getSrotedHash(result.get(userId));
				Object[] keys = sortedHash.keySet().toArray();
				
				for (Object item : keys) {
					int itemId = (int) item;
					Double curValue = sortedHash.get(itemId);
					tmpResult.put(tmp, curValue);
					tmp += 1;
				}
			}
			
			
			Map<Integer, Double> tmpResultSort = SortedHash.getSrotedHashDouble(tmpResult);
			HashMap<Double, Double> countGrade = new HashMap<Double, Double>();
			
			double nextGrade = 1.0;
			for (Integer tmpKey : tmpResultSort.keySet()) {
				
				Double value = tmpResult.get(tmpKey);
				
				
				if (!countGrade.keySet().contains(value)){
					countGrade.put(value, nextGrade);
				}
				nextGrade += 1;
			}
			
			double globalMax = nextGrade;
			double globalMin = 1;
			
		
			for (Object user : result.keySet().toArray()) {
				int userId = (int) user;
				Map<Integer, Double> sortedHash = SortedHash.getSrotedHash(result.get(userId));
				Object[] keys = sortedHash.keySet().toArray();
				
				
				for (Object item : keys) {
					int itemId = (int) item;
					Double curValue = sortedHash.get(itemId);
					
					double grade = countGrade.get(curValue);
					double imputevalue =  1- minMaxNorm.calculate(globalMin, globalMax, grade);
					
					resutU1base.get(userId).put(itemId, imputevalue);
				}
			}			
			
			WriteFileOCCF wf = new WriteFileOCCF();
			wf.writeResultFile(resutU1base, writePath);
		
		}

		System.out.println("end");
		
		
	}

}
