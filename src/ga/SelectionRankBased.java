package ga;

import java.util.ArrayList;
import java.util.List;

public class SelectionRankBased extends Selection {
	
	public SelectionRankBased(Population population, Fitness fitness) {
		super(population, fitness);
	}
	
	/**
	 * Chooses individuals based on highest fitness value first.
	 * Individuals are removed as chosen, so consecutive calls
	 * return different parents until another constructor is called.
	 */
	public List<Individual> select() {
		List<Individual> parents = new ArrayList<Individual>();
		
		// evaluate fitness of every individual based on fitness function provided
		population.evaluateFitness(fitness);
		
		// choose best ranking individual as parent 1
		Individual parent1 = population.getMostFit();
		parents.add(parent1);
		
		// remove parent 1 from population so it is not chosen every time
		population.remove(parent1);
		
		// choose next best individual for parent 2
		Individual parent2 = population.getMostFit();
		parents.add(parent2);
		
		// remove parent 2 from population so not available in next selection
		population.remove(parent2);
		
		return parents;
	}

}

