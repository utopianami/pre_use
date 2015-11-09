package util;

public class ClassifierPreUse {
	
	
	
	public int classifier(double value){
		
		if (value < -1.5)
			return 1;
		if (value >= -1.5 && value < -1.4)
			return 2;
		if (value >= -1.4 && value < -1.3)
			return 3;
		if (value >= -1.3 && value < -1.2)
			return 4;
		if (value >= -1.2 && value < -1.1)
			return 5;
		if (value >= -1.1 && value < -1.0)
			return 6;
		if (value >= -1.0 && value < -0.9)
			return 7;
		if (value >= -0.9 && value < -0.8)
			return 8;
		if (value >= -0.8 && value < -0.7)
			return 9;
		if (value >= -0.7 && value < -0.6)
			return 10;
		if (value >= -0.6 && value < -0.5)
			return 11;
		
		if (value >= -0.5 && value < -0.4)
			return 12;
		if (value >= -0.4 && value < -0.3)
			return 13;
		if (value >= -0.3 && value < -0.2)
			return 14;
		if (value >= -0.2 && value < -0.1)
			return 15;
		if (value >= -0.1 && value < -0.0)
			return 16;
		if (value >= 0.0 && value < 0.1)
			return 17;
		if (value >= 0.1 && value < 0.2)
			return 18;
		if (value >= 0.2 && value < 0.3)
			return 19;
		if (value >= 0.3 && value < 0.4)
			return 20;
		
		if (value >= 0.4 && value < 0.5)
			return 21;
		if (value >= 0.5 && value < 0.6)
			return 22;
		if (value >= 0.6 && value < 0.7)
			return 23;
		if (value >= 0.7 && value < 0.8)
			return 24;
		if (value >= 0.8 && value < 0.9)
			return 25;
		if (value >= 0.9 && value < 1.0)
			return 26;
		if (value >= 1.0 && value < 1.1)
			return 27;
		if (value >= 1.1 && value < 1.2)
			return 28;
		if (value >= 1.2 && value < 1.3)
			return 29;
		if (value >= 1.3 && value < 1.4)
			return 30;

		if (value >= 1.4 && value < 1.5)
			return 31;
		if (value > 1.5)
			return 32;
		

		
		
		
		
		
		
		
		
		
		return -1;
		
	}

}
