package ga;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Individual {
	
	private double minValue = -0.3;
	private double maxValue = 0.3;
	private double fitness = -1.0;
	private List<Gene> genes;

	public Individual(int chromosomeSize, int seed) {
		genes = new ArrayList<>(chromosomeSize);
		Random rand = new Random(11235 * seed);
		for (int geneNum = 0; geneNum < chromosomeSize; geneNum++){
			double initValue = minValue + (rand.nextDouble() * (maxValue - minValue));
			genes.add(new GeneReal(initValue));
		}
	}
	
	public Individual(List<Gene> genes) {
		setGenes(genes);
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		if (fitness < 0.0)
			throw new IllegalArgumentException("You must first evaluate fitness of the individual using a fitness function.");
		return fitness;
	}

	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	public List<Gene> getGenes() {
		return genes;
	}
	
	public String toString() {
		String value = "[";
		for (Gene gene : genes) {
			value += gene.getValue()+ ",";
		}
		value += "]\n";
		return value;
	}
	
	public void describe() {
		System.out.println(this);
	}

}
