package Distribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.ClassifierPreUse;
import util.ReadFile;
import util.WriteFile;
import util.WriteUserAvg;
import zero_injection.MinMaxNorm;
import zero_injection.SortedHash;

public class AllPreuse {

	public static void main(String[] args) {
		
		System.out.println("all");
		
		String path = "/Users/LeeYoungNam/Documents/pre_use/data";
		
		
		double percent = 0.9;
		ClassifierPreUse cp = new ClassifierPreUse();
		
		for (int i = 1; i<=5; i++){
			
			//file io
			String crossValidation= String.valueOf(i);
			String u1Path = path+"/ml-100k/u"+crossValidation+".base";
			String occfResultPath = path+"/predict/u"+crossValidation+".predict";
			String writePath = path+"/occf_Distribution/interesting" + "/u"+crossValidation+ ".dist";
			
			ReadFile rf = new ReadFile();
			HashMap<Integer, HashMap<Integer, Double>> resutU1base = rf.readU1base(u1Path);
			Map<Integer, HashMap<Integer, Double>> result = rf.readOccfResult(occfResultPath, resutU1base);
			
			
			HashMap<Integer, Double> preUseDistribution = new HashMap<Integer, Double>();
			
			
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
			
			// max, min
			preUseDistribution.put(999, globalMax);
			preUseDistribution.put(-999, globalMin);

			preUseDistribution.put(1, 0.0);
			preUseDistribution.put(2, 0.0);
			preUseDistribution.put(3, 0.0);
			preUseDistribution.put(4, 0.0);
			preUseDistribution.put(5, 0.0);
			preUseDistribution.put(6, 0.0);
			preUseDistribution.put(7, 0.0);
			preUseDistribution.put(8, 0.0);
			preUseDistribution.put(9, 0.0);
			preUseDistribution.put(10, 0.0);
			preUseDistribution.put(11, 0.0);
			preUseDistribution.put(12, 0.0);
			preUseDistribution.put(13, 0.0);
			preUseDistribution.put(14, 0.0);
			preUseDistribution.put(15, 0.0);
			preUseDistribution.put(16, 0.0);
			preUseDistribution.put(17, 0.0);
			preUseDistribution.put(18, 0.0);
			preUseDistribution.put(19, 0.0);
			preUseDistribution.put(20, 0.0);
			preUseDistribution.put(21, 0.0);
			preUseDistribution.put(22, 0.0);
			preUseDistribution.put(23, 0.0);
			preUseDistribution.put(24, 0.0);
			preUseDistribution.put(25, 0.0);
			preUseDistribution.put(26, 0.0);
			preUseDistribution.put(27, 0.0);
			preUseDistribution.put(28, 0.0);
			preUseDistribution.put(29, 0.0);
			preUseDistribution.put(30, 0.0);
			preUseDistribution.put(31, 0.0);
			preUseDistribution.put(32, 0.0);
			
			
			
			
			for (Object user : result.keySet().toArray()) {
				int userId = (int) user;
				Map<Integer, Double> sortedHash = SortedHash.getSrotedHash(result.get(userId));
				
				Object[] keys = sortedHash.keySet().toArray();
				int numberOfzeroItem = sortedHash.keySet().toArray().length;
				
				for (Object item : keys) {
					int itemId = (int) item;
					

					//input
					Double curValue = sortedHash.get(itemId);
					int classLabel = cp.classifier(curValue);
					
					Double value = preUseDistribution.get(classLabel) + 1;
					preUseDistribution.put(classLabel, value);
					

					
				}
			}
						
			
			WriteUserAvg wf = new WriteUserAvg();
			wf.writeResultFile(preUseDistribution, writePath);
		}

		System.out.println("end");
		
		
	}

}
