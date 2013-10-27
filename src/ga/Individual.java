package ga;
import java.util.List;

public class Individual {
	
	private double fitness = -1.0;
	private List<Gene> genes;

	public Individual() {

	}
	
	public Individual(List<Gene> genes) {
		setGenes(genes);
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		if (fitness < 0.0)
			throw new IllegalArgumentException("You must first evaluate fitness of the individual usinga fitness function.");
		return fitness;
	}

	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	public List<Gene> getGenes() {
		return genes;
	}

}
