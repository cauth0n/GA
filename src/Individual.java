import java.util.List;

public class Individual {
	private double fitness;

	private List<Gene> genes;

	public Individual() {

	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		return fitness;
	}

	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	public List<Gene> getGenes() {
		return genes;
	}

}
