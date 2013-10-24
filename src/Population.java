import java.util.List;

public class Population {
	private List<Individual> population;

	public Population(List<Individual> population) {
		this.population = population;
	}

	public List<Individual> getPopulation() {
		return population;
	}
	
	public double getFitness(){
		double runningFitness = 0;
		for (Individual i : population){
			runningFitness += i.getFitness();
		}
		return runningFitness;
	}

}
