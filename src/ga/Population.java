package ga;
import java.util.List;
import java.util.Random;

public class Population {
	
	private List<Individual> population;
	private Random random = new Random(11235);

	public Population(List<Individual> population) {
		this.population = population;
	}

	public List<Individual> getPopulation() {
		return population;
	}
	
	public Individual getRandomIndividual() {
		return population.get(random.nextInt(population.size()));
	}
	
	public int size() {
		return population.size();
	}
	
	public double getFitness() {
		double runningFitness = 0;
		for (Individual i : population){
			runningFitness += i.getFitness();
		}
		return runningFitness;
	}
	
	public void evaluateFitness(Fitness fitness) {
		for (Individual individual : population)
			individual.setFitness(fitness.getIndividualFitness(individual));
	}
	
	public Individual getMostFit() {
		Individual mostFit = population.get(0);
		for (Individual individual : population)
			if (individual.getFitness() > mostFit.getFitness())
				mostFit = individual;
		return mostFit;
	}
	
	public int getSize() {
		return population.size();
	}

}
