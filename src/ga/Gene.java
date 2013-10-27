package ga;

public abstract class Gene {
	
	protected double value;
	
	Gene() {
		
	}
	
	public abstract Gene add(Gene gene2);
	
	public abstract Gene subtract(Gene gene2);
	
	public abstract Gene scalarMultiply(double scalar);

}
