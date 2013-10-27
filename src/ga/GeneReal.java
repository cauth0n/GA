package ga;

public class GeneReal extends Gene {
	
	GeneReal(double value) {
		this.value = value;
	}
	
	public Gene add(Gene gene2) {
		return new GeneReal(this.value + gene2.value);
	}
	
	public Gene subtract(Gene gene2) {
		return new GeneReal(this.value - gene2.value);
	}
	
	public Gene scalarMultiply(double scalar) {
		return new GeneReal(this.value * scalar);
	}
	
}

