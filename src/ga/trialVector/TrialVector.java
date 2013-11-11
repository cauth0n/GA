package ga.trialVector;

import ga.Individual;

/**
 * Abstract class for Trial Vector used in Differential Evolution.
 */
public abstract class TrialVector {

	protected Individual one;
	protected Individual two;
	protected Individual three;
	protected double beta;

	/**
	 * Constructs a trial vector given three individuals.
	 * 
	 * @param one 		best individual in population
	 * @param two 		randomly selected individual
	 * @param three 	randomly selected individual
	 * @param beta 		learning rate
	 */
	public TrialVector(Individual one, Individual two, Individual three, double beta) {
		super();
		this.one = one;
		this.two = two;
		this.three = three;
		this.beta = beta;
	}

	/**
	 * Creates a vector based on the best individual in the population
	 * and two randomly selected individuals.
	 * 
	 * @return	An individual whose genes are the newly created vector.
	 */
	public abstract Individual getTrialVector();

}
