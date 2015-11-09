package zero_injection;

public class MinMaxNorm {
	
	public double newMin;
	public double newMax;
	
	public MinMaxNorm (double newMin, double newMax) {
		
		if (newMin > newMax)
			System.out.println("false!!!!!!!!!!!!!!");
		
		this.newMin = newMin;
		this.newMax = newMax;
	}
	
	
	public double calculate(double localMin, double localMax, double curVal){
		if (localMin > localMax)
			System.out.println("false!!!!!!!!!!!!!!");
		
		return (curVal - localMin) / (localMax - localMin) * (newMax - newMin) + newMin;
	}

}
