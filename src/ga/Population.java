package ga;
import java.util.List;

public class Population {
	private List<Individual> population;

	public Population(List<Individual> population) {
		this.population = population;
	}

	public List<Individual> getPopulation() {
		return population;
	}
	
	public int size() {
		return population.size();
	}
	
	public double getFitness(){
		double runningFitness = 0;
		for (Individual i : population){
			runningFitness += i.getFitness();
		}
		return runningFitness;
	}

}
