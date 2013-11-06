package ga.crossover;

import ga.Individual;
import ga.Population;
import ga.selection.MatingPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Crossover {
	
	Random random = new Random(11235);
	
	public List<Individual> crossOver(Population population, MatingPlan plan) {
		if (plan.size() == population.size()) {
			return crossOverOneChild(plan);
		} else if (plan.size() == (population.size() / 2)) {
			return crossOverTwoChildren(plan);
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
			children.add(results.get(child));
		}
		return children;
	}
	
	protected List<Individual> crossOverTwoChildren(MatingPlan plan) {
		List<Individual> children = new ArrayList<>(1);
		while (plan.hasNext()) {
			int child = 0;
			if (random.nextBoolean())
				child = 1;
			List<Individual> parents = plan.getNext();
			List<Individual> results = crossOverParents(parents.get(0), parents.get(1));
			children.add(results.get(0));
			children.add(results.get(1));
		}
		return children;
	}
	
	protected abstract List<Individual> crossOverParents(Individual parent1, Individual parent2);

}

