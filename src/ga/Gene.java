package ga;

/**
 * Abstract gene class used by individuals.
 */
public abstract class Gene {

	// The actual value of the gene
	protected double value;

	/**
	 * Empty constructor.
	 */
	Gene() {

	}

	/**
	 * Constructs a gene with specified value.
	 * 
	 * @param value	The value to assign to the gene.
	 */
	public Gene(double value) {
		setValue(value);
	}

	/**
	 * The operation used for adding two genes together.
	 * 
	 * @param gene2	The gene to add to this gene.
	 * @return		The gene resulting from the addition operation.
	 */
	public abstract Gene add(Gene gene2);

	/**
	 * The operation used for subtracting a gene from another.
	 * 
	 * @param gene2	The gene to subtract from the current gene.
	 * @return		The gene resulting from the subtraction operation.
	 */
	public abstract Gene subtract(Gene gene2);

	/**
	 * The operation used to multiply the gene by a scalar.
	 * 
	 * @param scalar	The scalar to multiply by the gene.
	 * @return			The gene resulting from the scalar multiplication operation.
	 */
	public abstract Gene scalarMultiply(double scalar);

	/**
	 * @return	The underlying value from the gene.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value	The underlying value of the gene.
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * The operation used to multiply the gene by a scalar.
	 * 
	 * @param scalar	The scalar to multiply by the gene.
	 */
	public abstract void scalarMultiplyThis(double scalar);
	
	/**
	 * The operation used to add a scalar to the gene.
	 * 
	 * @param scalar	The scalar to add to the gene.
	 */
	public abstract void add(double scalar);

	/**
	 * @return	A copy of the gene.
	 */
	public abstract Gene copy();

}
