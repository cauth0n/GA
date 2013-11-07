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

	public List<Individual> getIndividuals() {
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
		for (Individual i : population) {
			runningFitness += i.getFitness();
		}
		return runningFitness;
	}
	
	public Individual getMostFit() {
		return getMostFit(1);
	}

	/**
	 * Retrieves the ith most fit individual from the population.
	 * Assumes population is sorted.
	 * 
	 * @param position
	 * @return
	 */
	public Individual getMostFit(int position) {
		if (position < 1 || position > population.size())
			throw new IllegalArgumentException("Invalid position.");
		return population.get(position - 1);
	}

	/**
	 * Retrieves the ith least fit individual from the population.
	 * Assumes population is sorted.
	 * 
	 * @return
	 */
	public Individual getLeastFit(int position) {
		if (position < 1 || position > population.size())
			throw new IllegalArgumentException("Invalid position.");
		return population.get(population.size() - position);
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
		return new double[] { minVal, maxVal, maxVal - minVal };
	}

	public void printDiversity() {
		double[] diversity = getDiversity();
		System.out.println("(" + diversity[0] + "," + diversity[1] + ") ( "+getAverageFitness()+" ) => "
				+ diversity[2]);
	}

	/**
	 * A bubble sort method.
	 */
	public void sortPopulationByFitness() {
		for (int i = 0; i < population.size(); i++) {
			for (int j = 0; j < population.size() - 1 - i; j++) {
				if (population.get(j).getFitness() < population.get(j + 1).getFitness()) {
					Individual temp = population.get(j);
					this.population.set(j, population.get(j + 1));
					this.population.set(j + 1, temp);
				}
			}
		}
	}
	
	public double getAverageFitness() {
		double avg = 0.0;
		for (Individual i : population)
			avg += i.getFitness();
		avg /= population.size();
		return avg;
	}
	
}
