package MultiDimensionalSearch;

public class MaxAndMin {

	Double max=Double.MIN_VALUE;
	Double min=Double.MAX_VALUE;
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public MaxAndMin(Double max, Double min) {
		super();
		this.max = max;
		this.min = min;
	}
	
}
