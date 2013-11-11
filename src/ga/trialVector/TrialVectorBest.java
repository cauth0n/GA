package ga.trialVector;

import ga.Gene;
import ga.Individual;

import java.util.ArrayList;
import java.util.List;

/**
 * Implemented Trial Vector used in Differential Evolution.
 */
public class TrialVectorBest extends TrialVector {

	/**
	 * Constructs a trial vector given three individuals.
	 * 
	 * @param one 		best individual in population
	 * @param two 		randomly selected individual
	 * @param three 	randomly selected individual
	 * @param beta 		learning rate
	 */
	public TrialVectorBest(Individual one, Individual two, Individual three, double beta) {
		super(one, two, three, beta);
	}

	/**
	 * Calculates and retrieves the trial vector by 
	 * performing operations on the vectors from the 
	 * three individuals provided.
	 */
	public Individual getTrialVector() {
		List<Gene> newGenes = new ArrayList<>(one.getGenes().size());
		// loop through all genes, constructing a new list of genes, or a vector
		for (int i = 0; i < one.getGenes().size(); i++) {
			// create new gene from the difference between the randomly selected individuals
			newGenes.add(two.getGenes().get(i).subtract(three.getGenes().get(i)));
			// multiply the new gene by a scalar learning rate
			newGenes.get(i).scalarMultiplyThis(beta);
			// add the best individual's vector to the newly created one
			newGenes.get(i).add(one.getGenes().get(i).getValue());
		}

		// return an individual with the new vector that was created through operations
		return new Individual(newGenes);
	}
}
