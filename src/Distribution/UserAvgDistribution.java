package Distribution;

import java.util.HashMap;
import java.util.Map;

import util.ReadFile;
import util.WriteFile;
import util.WriteUserAvg;
import zero_injection.SortedHash;

public class UserAvgDistribution {

	public static void main(String[] args) {
		System.out.println("useravgDistribution");
		
		String path = "/Users/LeeYoungNam/Documents/pre_use/data";
		double percent = 0.9; 
		double lowRating = 2.5;
		
		

		
		for (int i = 1; i<=5; i++){
			
			//file io
			String crossValidation= String.valueOf(i);
			String u1Path = path+"/ml-100k/u"+crossValidation+".base";
			String occfResultPath = path+"/predict/u"+crossValidation+".predict";
			String writePath = path+"/MultipleImputation/userAvgDistribution" + "/u"+crossValidation+ ".base";
			
			ReadFile rf = new ReadFile();
			HashMap<Integer, HashMap<Integer, Double>> resutU1base = rf.readU1base(u1Path);
			Map<Integer, HashMap<Integer, Double>> result = rf.readOccfResult(occfResultPath, resutU1base);
			
			
			double x = 0.0;
			double ratingSum = 0.0;
			double ratingAvg = 0.0;
			int userCount = 0;
			HashMap<Integer, Double> userAvg = new HashMap<Integer, Double>();
			
	
			//caculate avg
			for (Object user : result.keySet().toArray()) {
				userCount += 1;
				
				
				int userId = (int) user;
				
				HashMap<Integer, Double> userRating = resutU1base.get(userId);	
				
				int numOfItems = userRating.keySet().toArray().length;
				double userRatingSum = 0.0;
				double userRatingAvg = 0.0;
				
				for (Object item : userRating.keySet().toArray()){
					int itemId = (int) item;
					userRatingSum += userRating.get(itemId);
				}
				
				
				userRatingAvg = userRatingSum / numOfItems;
				ratingSum += userRatingAvg;
				userAvg.put(userId, userRatingAvg);
			}
			
			ratingAvg = ratingSum/userCount;
			
			x = ratingAvg - lowRating;
			
			System.out.println(ratingAvg);
			System.out.println(userCount);
			
			
			WriteUserAvg wf = new WriteUserAvg();
			wf.writeResultFile(userAvg, writePath);
			
		}

		
		System.out.println("end");
		
	}

}