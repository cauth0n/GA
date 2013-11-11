package ga.selection;

import ga.Individual;
import java.util.ArrayList;
import java.util.List;

public class MatingPlan {
	
	private List<Individual> reserve = new ArrayList<>();
	private List<List<Individual>> pairs = new ArrayList<>();
	private int indexPair = 0;
	private int indexReserve = 0;
	
	/**
	 * Adds a mating pair to the plan.
	 * 
	 * @param parent1	The first parent in the pair.
	 * @param parent2	The second parent in the pair.
	 */
	public void add(Individual parent1, Individual parent2) {
		// create a pair list
		List<Individual> pair = new ArrayList<Individual>(2);
		// add both parents
		pair.add(parent1);
		pair.add(parent2);
		// add pair to list of pairs
		pairs.add(pair);
	}
	
	/**
	 * Reserves an individual from crossover.
	 * This individual will simply be passed to the
	 * new generation.
	 * 
	 * @param individual	The individual to holdout from crossover.
	 */
	public void reserve(Individual individual) {
		reserve.add(individual);
	}
	
	/**
	 * @return	Whether or not there is another pair in the list.
	 */
	public boolean hasNextPair() {
		if (indexPair < pairs.size())
			return true;
		else
			return false;
	}
	
	/**
	 * Iterator for MatingPlan pairs.
	 * 
	 * @return	The next pair of individuals in the list of mating pairs.
	 */
	public List<Individual> getNextPair() {
		return pairs.get(indexPair++);
	}
	
	/**
	 * @return	Whether or not there is another reserved individual in the list.
	 */
	public boolean hasNextReserve() {
		if (indexReserve < reserve.size())
			return true;
		else
			return false;
	}
	
	/**
	 * Iterator for MatingPlan reserved.
	 * 
	 * @return	The next reserved individual in the list of reserved individuals.
	 */
	public Individual getNextReserve() {
		return reserve.get(indexReserve++);
	}
	
	/**
	 * @return	The combined number of reserved individuals and pairs in the mating plan.
	 */
	public int size() {
		return reserve.size() + pairs.size();
	}

}
