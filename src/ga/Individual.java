package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Individual {

	private double minValue = -0.3;
	private double maxValue = 0.3;
	private double fitness = -1.0;
	private List<Gene> genes;
	private List<Individual> parents;
	private StrategyParameters params = null;
	private boolean canMutate = true;
	private static int seed = 1;

	/**
	 * Constructs an individual with a specified number of random valued genes.
	 * 
	 * @param chromosomeSize	The number of genes that the individual will contain.
	 * @param seed				A seed for the gene value assignment.
	 */
	public Individual(int chromosomeSize) {
		genes = new ArrayList<>(chromosomeSize);
		Random rand = new Random(11235 * seed++);
		// loop through all genes and assign a random value
		for (int geneNum = 0; geneNum < chromosomeSize; geneNum++) {
			double initValue = minValue + (rand.nextDouble() * (maxValue - minValue));
			genes.add(new GeneReal(initValue));
		}
	}

	/**
	 * Constructs an individual with a specified list of genes.
	 * 
	 * @param genes	The list of genes that the individual will use.
	 */
	public Individual(List<Gene> genes) {
		List<Gene> newGenes = new ArrayList<Gene>(genes.size());
		// assign a copy of each gene to the individual
		for (Gene g : genes)
			newGenes.add(g.copy());
		setGenes(newGenes);
	}

	/**
	 * Sets the fitness of a particular individual.
	 * 
	 * @param fitness	The new fitness of the individual.	
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * @return	The fitness of the individual, assuming one has been calculated.
	 */
	public double getFitness() {
		// check to see if the fitness of this individual has actually been calculated
		if (fitness < 0.0)
			throw new IllegalArgumentException(
					"You must first evaluate fitness of the individual using a fitness function.");
		return fitness;
	}

	/**
	 * Sets the genes for the individual.
	 * 
	 * @param genes	The list of genes that the individual will now use.
	 */
	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	/**
	 * @return	The list of genes that make up the individual's chromosome.
	 */
	public List<Gene> getGenes() {
		return genes;
	}
	
	/**
	 * Sets the strategy parameters for the individual, if they are to be used.
	 * 
	 * @param params	The strategy parameters object for the individual (used in ES)
	 */
	public void setStrategyParameters(StrategyParameters params) {
		this.params = params;
	}
	
	/**
	 * @return	The object representing the list of strategy parameters for the individual.
	 */
	public StrategyParameters getStrategyParameters() {
		return this.params;
	}

	/**
	 * Prints a description of the individual to the screen.
	 * Used for debugging.
	 */
	public void describe() {
		String value = "[";
		for (Gene gene : genes) {
			value += gene.getValue() + ",";
		}
		value += "]\n";
		System.out.println(value);
	}

	/**
	 * Checks to see if two individuals are genetically equal.
	 * This does not require object addresses to be identical.
	 * 
	 * @param individual	The individual to compare to the current one.
	 * @return				True if the individuals are equivalent, False otherwise.
	 */
	public boolean geneticallyEquals(Individual individual) {
		// loop through all genes for each individual, comparing each
		for (int gene = 0; gene < genes.size(); gene++)
			if (this.genes.get(gene).getValue() != individual.genes.get(gene).getValue())
				return false;
		return true;
	}

	/**
	 * @return	Whether or not the individual can mutate.
	 */
	public boolean canMutate() {
		return canMutate;
	}

	/**
	 * Sets whether or not the individual can mutate.
	 * 
	 * @param canMutate	Whether or not the individual will be able to mutate.
	 */
	public void setMutate(boolean canMutate) {
		this.canMutate = canMutate;
	}
	
	/**
	 * Creates a copy of the current individual.
	 * 
	 * @return	An identical individual except for the address of the object itself.
	 */
	public Individual copy() {
		Individual toReturn = new Individual(this.genes);
		toReturn.setFitness(this.fitness);
		toReturn.setMutate(this.canMutate);
		toReturn.setStrategyParameters(this.params);
		return toReturn;
	}
	
	/**
	 * @return	Whether or not the individual uses strategy parameters.
	 */
	public boolean hasStrategyParameters() {
		return (params != null);
	}

}
