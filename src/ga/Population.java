package ga;
import java.util.ArrayList;
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
	
	public Individual getMostFit() {
		Individual mostFit = null;
		double mostFitValue = Double.MIN_VALUE;
		for (Individual individual : population) {
			if (individual.getFitness() > mostFitValue) {
				mostFit = individual;
				mostFitValue = individual.getFitness();
			}
		}
		return mostFit;
	}
	
	public int getSize() {
		return population.size();
	}
	
	public Population copy() {
		List<Individual> copyPopulation = new ArrayList<Individual>();
		for (Individual individual : population)
			copyPopulation.add(individual);
		return new Population(copyPopulation);
	}
	
	public void remove(Individual individual) {
		population.remove(individual);
	}
	
	public double[] getDiversity() {
		double minVal = Double.MAX_VALUE;
		double maxVal = Double.MIN_VALUE;
		for (Individual individual : population) {
			if (individual.getFitness() > maxVal)
				maxVal = individual.getFitness();
			if (individual.getFitness() < minVal)
				minVal = individual.getFitness();
		}
		return new double[]{minVal, maxVal, maxVal - minVal};
	}
	
	public void printDiversity() {
		double[] diversity = getDiversity();
		System.out.println("("+diversity[0]+","+diversity[1]+")  => "+diversity[2]);
	}

}
