package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Object that contains a group of individuals for any given generation.
 */
public class Population {

	private List<Individual> population;
	private Random random = new Random(11235);

	/**
	 * Constructs a population given a list of individuals.
	 * 
	 * @param population	The list of individuals that will be used in the population.
	 */
	public Population(List<Individual> population) {
		this.population = population;
	}

	/**
	 * @return	The list of individuals contained in a population.
	 */
	public List<Individual> getIndividuals() {
		return population;
	}

	/**
	 * @return	A random individual from the population.
	 */
	public Individual getRandomIndividual() {
		return population.get(random.nextInt(population.size()));
	}

	/**
	 * @return	The number of individuals in the population.
	 */
	public int size() {
		return population.size();
	}

	/**
	 * @return	The total fitness of each individual.
	 */
	public double getFitness() {
		double runningFitness = 0;
		// loop through every individual, adding its fitness to the running total
		for (Individual i : population) {
			runningFitness += i.getFitness();
		}
		return runningFitness;
	}

	/**
	 * @return	The individual with the highest fitness in the population.
	 */
	public Individual getMostFit() {

		Individual best = population.get(0);
		// loop through entire population, storing the individual with highest fitness in best
		for (Individual i : population) {
			if (i.getFitness() > best.getFitness()) {
				best = i;
			}
		}

		return best;
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
	
	/**
	 * Appends a population to the current one.
	 * 
	 * @param newPopulation	The population to append to the existing one.
	 */
	public void add(Population newPopulation) {
		// loop through each individual in the new population, adding them to the current population
		for (Individual individual : newPopulation.getIndividuals())
			population.add(individual);
	}

	/**
	 * @return	A copy of the population, containing the same individuals (shallow copy).
	 */
	public Population copy() {
		List<Individual> copyPopulation = new ArrayList<Individual>();
		// loop through each individual in the population, copying it to the new population
		// these individuals are not copies themselves, so they have identical addresses
		for (Individual individual : population)
			copyPopulation.add(individual);
		return new Population(copyPopulation);
	}

	/**
	 * Removes an individual from the population.
	 * 
	 * @param individual	The individual to remove from the population.
	 */
	public void remove(Individual individual) {
		population.remove(individual);
	}

	/**
	 * @return	An array containing the min, max, and (max - min) fitnesses that may be used to determine diversity.
	 */
	public double[] getDiversity() {
		double minVal = Double.MAX_VALUE;
		double maxVal = Double.MIN_VALUE;
		// loop through each individual in the population
		for (Individual individual : population) {
			// store max value
			if (individual.getFitness() > maxVal)
				maxVal = individual.getFitness();
			// store min value
			if (individual.getFitness() < minVal)
				minVal = individual.getFitness();
		}
		return new double[] { minVal, maxVal, maxVal - minVal };
	}

	/**
	 * Prints the min, max, and (max - min) fitness values to standard out to potentially be used to determine diversity.
	 */
	public void printDiversity() {
		double[] diversity = getDiversity();
		System.out.println("(" + diversity[0] + "," + diversity[1] + ") ( " + getAverageFitness() + " ) => " + diversity[2]);
	}

	/**
	 * A bubble sort method performed on each individual's fitness.
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

	/**
	 * @return	The average fitness of every individual in the population.
	 */
	public double getAverageFitness() {
		double avg = 0.0;
		// sum up the fitness for everyone in the population
		for (Individual i : population)
			avg += i.getFitness();
		// divide by population size to determine average
		avg /= population.size();
		return avg;
	}

}
