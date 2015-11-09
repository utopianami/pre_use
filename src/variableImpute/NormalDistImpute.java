package variableImpute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.ClassifierPreUse;
import util.ReadFile;
import util.WriteFile;
import util.WriteUserAvg;
import zero_injection.MinMaxNorm;
import zero_injection.SortedHash;

public class NormalDistImpute {

	public static void main(String[] args) {
		
		System.out.println("NormalDist");
		
		String path = "/Users/LeeYoungNam/Documents/pre_use/data";
		
		
		double percent = 0.9;
		ClassifierPreUse cp = new ClassifierPreUse();
		
		for (int i = 1; i<=5; i++){
			
			//file io
			String crossValidation= String.valueOf(i);
			String u1Path = path+"/ml-100k/u"+crossValidation+".base";
			String occfResultPath = path+"/predict/u"+crossValidation+".predict";
			String writePath = path+"/normalDist" + "/u"+crossValidation+ ".base";
			
			ReadFile rf = new ReadFile();
			HashMap<Integer, HashMap<Integer, Double>> resutU1base = rf.readU1base(u1Path);
			Map<Integer, HashMap<Integer, Double>> result = rf.readOccfResult(occfResultPath, resutU1base);
			
			
			HashMap<Integer, Double> valueDict = new HashMap<Integer, Double>();
			HashMap<Double, Integer> valueCount = new HashMap<Double, Integer>();
			HashMap<Double, Double> imputeValueDict = new HashMap<Double, Double>();
			int totalNum = 0;
			
			
			for (Object user : result.keySet().toArray()) {
				int userId = (int) user;
				Map<Integer, Double> sortedHash = SortedHash.getSrotedHash(result.get(userId));
				
				Object[] keys = sortedHash.keySet().toArray();
				int numberOfinjectionItem = (int) (sortedHash.keySet().toArray().length * percent); 
				int tmpIdx = 0;
				
				
				for (Object item : keys) {
					totalNum += 1;
					
					
					int itemId = (int) item;
					Double curValue = sortedHash.get(itemId);					
					
					if (valueCount.get(curValue) == null){
						valueDict.put(totalNum, curValue);
						valueCount.put(curValue, 1);
					}else{
						int tmp = valueCount.get(curValue) + 1;
						valueCount.put(curValue, tmp);
					}
					if (tmpIdx == numberOfinjectionItem)
						break;
					tmpIdx++;
				}				
			}
			
			Map<Integer, Double> sortedValue = SortedHash.getSrotedHashDouble(valueDict);
			Object[] occfValues = sortedValue.keySet().toArray();
			int curSum = 0;
			
			for (Object value : occfValues){
				Double curValue = valueDict.get(value);
				int numOfSameValue= valueCount.get(curValue);
				
				//백분위 계산
				double percentile = (curSum + 0.5*numOfSameValue)/totalNum;
				
				//값 결정
				//double imputevalue = 함수 호출
				
				//impute
				//imputeValueDict.put(value, imputevalue);
			}
			
			
			//setting
			
			
			for (Object user : result.keySet().toArray()) {
				int userId = (int) user;
				Map<Integer, Double> sortedHash = SortedHash.getSrotedHash(result.get(userId));
				
				Object[] keys = sortedHash.keySet().toArray();
				
				int numberOfinjectionItem = (int) (sortedHash.keySet().toArray().length * percent); 
				int tmpIdx = 0;
				
				
				for (Object item : keys) {
					int itemId = (int) item;
					

					//input
					Double curValue = sortedHash.get(itemId);
					Double imputevalue = 0.0;
					

					resutU1base.get(userId).put(itemId, imputevalue);
					
					if (tmpIdx == numberOfinjectionItem)
						break;
					tmpIdx++;
					
				}
			}
						
//			
//			WriteFile wf = new WriteFile();
//			wf.writeResultFile(resutU1base, writePath);
		}

		System.out.println("end");
		
		
	}

}
