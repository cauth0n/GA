package ga;

public class GeneReal extends Gene {

	/**
	 * Constructs a gene with specified value.
	 * 
	 * @param value	The value to assign to the gene.
	 */
	GeneReal(double value) {
		this.value = value;
	}

	/**
	 * The operation used for adding two genes together.
	 * 
	 * @param gene2	The gene to add to this gene.
	 * @return		The gene resulting from the addition operation.
	 */
	public Gene add(Gene gene2) {
		return new GeneReal(this.value + gene2.value);
	}

	/**
	 * The operation used for subtracting a gene from another.
	 * 
	 * @param gene2	The gene to subtract from the current gene.
	 * @return		The gene resulting from the subtraction operation.
	 */
	public Gene subtract(Gene gene2) {
		return new GeneReal(this.value - gene2.value);
	}

	/**
	 * The operation used to multiply the gene by a scalar.
	 * 
	 * @param scalar	The scalar to multiply by the gene.
	 * @return			The gene resulting from the scalar multiplication operation.
	 */
	public Gene scalarMultiply(double scalar) {
		return new GeneReal(this.value * scalar);
	}

	/**
	 * A copy of the gene.
	 */
	public Gene copy() {
		return new GeneReal(this.value);
	}

	/**
	 * The operation used to multiply the gene by a scalar.
	 * 
	 * @param scalar	The scalar to multiply by the gene.
	 */
	public void scalarMultiplyThis(double scalar) {
		this.value *= scalar;
	}

	/**
	 * The operation used to add a scalar to the gene.
	 * 
	 * @param scalar	The scalar to add to the gene.
	 */
	public void add(double scalar) {
		this.value += scalar;
	}

}
