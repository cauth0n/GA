package ga.trialVector;

import ga.Gene;
import ga.Individual;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cauthon
 */
public class TrialVectorBest extends TrialVector {

	/**
	 * 
	 * @param one
	 *            Best individual in population
	 * @param two
	 *            randomly selected individual
	 * @param three
	 *            randomly selected individual
	 * @param beta
	 *            learning rate
	 */
	public TrialVectorBest(Individual one, Individual two, Individual three, double beta) {
		super(one, two, three, beta);

	}

	@Override
	public Individual getTrialVector() {
		List<Gene> newGenes = new ArrayList<>(one.getGenes().size());
		for (int i = 0; i < one.getGenes().size(); i++) {
			newGenes.add(two.getGenes().get(i).subtract(three.getGenes().get(i)));//creates new gene object
			newGenes.get(i).multiply(beta); //does not create new gene object
			newGenes.get(i).add(one.getGenes().get(i).getValue()); //does not create new gene object
		}

		return new Individual(newGenes);
	}
}
