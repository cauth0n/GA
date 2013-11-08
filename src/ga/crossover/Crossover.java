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
	
	public Population crossOver(Population population, MatingPlan plan) {
		if (twoChildren) {
			return new Population(crossOverTwoChildren(plan));
		} else {
			return new Population(crossOverOneChild(plan));
		}
	}
	
	protected List<Individual> crossOverOneChild(MatingPlan plan) {
		List<Individual> children = new ArrayList<>(1);
		// TODO: maybe pick higher fitness
		Random random = new Random(11235);
		
		// get individuals that were held out
		while (plan.hasNextReserve()) {
			Individual child = plan.getNextReserve();
			child.setMutate(false);
			children.add(child);
		}
		
		// get mating pairs
		while (plan.hasNextPair()) {
			int child = 0;
			if (random.nextBoolean())
				child = 1;
			List<Individual> parents = plan.getNextPair();
			List<Individual> results = crossOverParents(parents.get(0), parents.get(1));
			Individual result1 = results.get(child);
			result1.setParents(parents);
			children.add(result1);
		}
		return children;
	}
	
	protected List<Individual> crossOverTwoChildren(MatingPlan plan) {
		List<Individual> children = new ArrayList<>(2);
		
		// get individuals that were held out
		while (plan.hasNextReserve()) {
			Individual child = plan.getNextReserve();
			child.setMutate(false);
			children.add(child);
		}
		
		while (plan.hasNextPair()) {
			int child = 0;
			if (random.nextBoolean())
				child = 1;
			List<Individual> parents = plan.getNextPair();
			List<Individual> results = crossOverParents(parents.get(0), parents.get(1));
			Individual result1 = results.get(0);
			Individual result2 = results.get(1);
			result1.setParents(parents);
			result2.setParents(parents);
			children.add(result1);
			children.add(result2);
		}
		return children;
	}
	
	public void setTwoChildren(boolean twoChildren) {
		this.twoChildren = twoChildren;
	}
	
	protected abstract List<Individual> crossOverParents(Individual parent1, Individual parent2);

}

