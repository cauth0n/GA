package ga.crossover;

import ga.Individual;
import ga.Population;
import ga.selection.MatingPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Crossover {
	
	Random random = new Random(11235);
	boolean twoChildren = false;
	
	/**
	 * Performs crossover on a population.
	 * 
	 * @param population	The population that will be bred.
	 * @param plan			The breeding plan that will be used for crossover.
	 * @return				The resulting population after crossover.
	 */
	public Population crossOver(Population population, MatingPlan plan) {
		// if twoChildren is set, use crossOverTwoChildren method
		if (twoChildren) {
			return new Population(crossOverTwoChildren(plan));
		// if twoChildren is not set, use crossOverOneChild method
		} else {
			return new Population(crossOverOneChild(plan));
		}
	}
	
	/**
	 * 
	 * @param plan
	 * @return
	 */
	protected List<Individual> crossOverOneChild(MatingPlan plan) {
		List<Individual> children = new ArrayList<>(1);
		Random random = new Random(11235);
		
		// get individuals that were held out to continue
		while (plan.hasNextReserve()) {
			// set reserved individual as child directly (no crossover)
			Individual child = plan.getNextReserve();
			// do not allow mutation on these individuals
			child.setMutate(false);
			// add child to list
			children.add(child);
		}
		
		// get mating pairs
		while (plan.hasNextPair()) {
			// choose a random child (0 or 1)
			int child = 0;
			if (random.nextBoolean())
				child = 1;
			// get parents from mating plan
			List<Individual> parents = plan.getNextPair();
			// perform crossover, storing children in results
			List<Individual> results = crossOverParents(parents.get(0), parents.get(1));
			// choose the child from the random index
			Individual result1 = results.get(child);
			// add child to list
			children.add(result1);
		}
		// return list of children formed
		return children;
	}
	
	protected List<Individual> crossOverTwoChildren(MatingPlan plan) {
		List<Individual> children = new ArrayList<>(2);
		
		// get individuals that were held out to continue
		while (plan.hasNextReserve()) {
			// set reserved individual as child directly (no crossover)
			Individual child = plan.getNextReserve();
			// do not allow mutation on these individuals
			child.setMutate(false);
			// add child to list
			children.add(child);
		}
		
		// get mating pairs
		while (plan.hasNextPair()) {
			// get parents from mating plan
			List<Individual> parents = plan.getNextPair();
			// perform crossover, storing children in results
			List<Individual> results = crossOverParents(parents.get(0), parents.get(1));
			// store results that were returned
			Individual result1 = results.get(0);
			Individual result2 = results.get(1);
			// add both children to list
			children.add(result1);
			children.add(result2);
		}
		// return list of children formed
		return children;
	}
	
	/**
	 * Sets whether or not to create two children or one child from crossover.
	 * 
	 * @param twoChildren	Whether or not to create two children during crossover.
	 */
	public void setTwoChildren(boolean twoChildren) {
		this.twoChildren = twoChildren;
	}
	
	/**
	 * Performs crossover given two parents. May be implemented in multiple ways.
	 * 
	 * @param parent1	The first parent to be used in crossover.
	 * @param parent2	The second parent to be used in crossover.
	 * @return			A list containing the two children that are created during crossover.
	 */
	protected abstract List<Individual> crossOverParents(Individual parent1, Individual parent2);

}

