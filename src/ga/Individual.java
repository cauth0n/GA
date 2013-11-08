package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Individual {

	private double minValue = -0.3;
	private double maxValue = 0.3;
	private double fitness = -1.0;
	private List<Gene> genes;
	private StrategyParameters params = null;
	private boolean canMutate = true;
	private boolean canCrossover = true;

	public Individual(int chromosomeSize, int seed) {
		genes = new ArrayList<>(chromosomeSize);
		Random rand = new Random(11235 * seed);
		for (int geneNum = 0; geneNum < chromosomeSize; geneNum++) {
			double initValue = minValue
					+ (rand.nextDouble() * (maxValue - minValue));
			genes.add(new GeneReal(initValue));
		}
	}

	public Individual(List<Gene> genes) {
		List<Gene> newGenes = new ArrayList<Gene>(genes.size());
		for (Gene g : genes)
			newGenes.add(g.copy());
		setGenes(newGenes);
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		if (fitness < 0.0)
			throw new IllegalArgumentException(
					"You must first evaluate fitness of the individual using a fitness function.");
		return fitness;
	}

	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	public List<Gene> getGenes() {
		return genes;
	}
	
	public void setStrategyParameters(StrategyParameters params) {
		this.params = params;
	}
	
	public StrategyParameters getStrategyParameters() {
		return this.params;
	}

	public void describe() {
		String value = "[";
		for (Gene gene : genes) {
			value += gene.getValue() + ",";
		}
		value += "]\n";
		System.out.println(value);
	}

	public boolean geneticallyEquals(Individual individual) {
		for (int gene = 0; gene < genes.size(); gene++)
			if (this.genes.get(gene).getValue() != individual.genes.get(gene)
					.getValue())
				return false;
		return true;
	}

	public boolean canMutate() {
		return canMutate;
	}

	public void setMutate(boolean canMutate) {
		this.canMutate = canMutate;
	}

	public boolean canCrossover() {
		return canCrossover;
	}

	public void setCrossover(boolean canCrossover) {
		this.canCrossover = canCrossover;
	}
	
	public void setImmutable(boolean immutable) {
		this.canMutate = immutable;
		this.canCrossover = immutable;
	}
	
	public Individual copy() {
		Individual toReturn = new Individual(this.genes);
		toReturn.setFitness(this.fitness);
		toReturn.setCrossover(canCrossover);
		toReturn.setMutate(canMutate);
		return toReturn;
	}
	
	public boolean hasStrategyParameters() {
		return (params != null);
	}

}
