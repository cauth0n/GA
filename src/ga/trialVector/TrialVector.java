package ga.trialVector;

import ga.Individual;

/**
 * @author cauthon
 */
public abstract class TrialVector {

	protected Individual one;
	protected Individual two;
	protected Individual three;
	protected double beta;

	public TrialVector(Individual one, Individual two, Individual three, double beta) {
		super();
		this.one = one;
		this.two = two;
		this.three = three;
		this.beta = beta;
	}

	public abstract Individual getTrialVector();

}
