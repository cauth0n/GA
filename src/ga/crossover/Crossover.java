package ga.crossover;

import ga.Individual;
import ga.Population;
import ga.selection.MatingPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Crossover {
	
	Random random = new Random(11235);
	
	public Population crossOver(Population population, MatingPlan plan) {
		if (plan.size() == population.size()) {
			return new Population(crossOverOneChild(plan));
		} else if (plan.size() == (population.size() / 2)) {
			return new Population(crossOverTwoChildren(plan));
		} else {
			throw new IllegalArgumentException("Invalid MatingPlan size. Population size = "+population.size()+", MatingPlan size = "+plan.size());
		}
	}
	
	protected List<Individual> crossOverOneChild(MatingPlan plan) {
		List<Individual> children = new ArrayList<>(1);
		// TODO: maybe pick higher fitness
		Random random = new Random(11235);
		while (plan.hasNext()) {
			int child = 0;
			if (random.nextBoolean())
				child = 1;
			List<Individual> parents = plan.getNext();
			List<Individual> results = crossOverParents(parents.get(0), parents.get(1));
			Individual result1 = results.get(child);
			// if parent is mating with itself, do not allow mutation
			if (parents.get(0).equals(parents.get(1))) {
				result1.setMutate(false);
			}
			children.add(result1);
		}
		return children;
	}
	
	protected List<Individual> crossOverTwoChildren(MatingPlan plan) {
		List<Individual> children = new ArrayList<>(2);
		while (plan.hasNext()) {
			int child = 0;
			if (random.nextBoolean())
				child = 1;
			List<Individual> parents = plan.getNext();
			List<Individual> results = crossOverParents(parents.get(0), parents.get(1));
			Individual result1 = results.get(0);
			Individual result2 = results.get(1);
			children.add(result1);
			children.add(result2);
			// if parent is mating with itself, do not allow mutation
			if (parents.get(0).equals(parents.get(1))) {
				result1.setMutate(false);
				result2.setMutate(false);
			}
		}
		return children;
	}
	
	protected abstract List<Individual> crossOverParents(Individual parent1, Individual parent2);

}

