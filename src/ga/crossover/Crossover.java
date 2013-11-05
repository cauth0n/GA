package ga.crossover;

import ga.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Crossover {
	
	Random random = new Random(11235);
	
	public List<Individual> crossOverOneChild(Individual individual1, Individual individual2) {
		List<Individual> children = new ArrayList<>(1);
		// TODO: maybe pick higher fitness
		Random random = new Random(11235);
		int child = 0;
		if (random.nextBoolean())
			child = 1;
		children.add(crossOverTwoChildren(individual1, individual2).get(child));
		return children;
	}
	
	public abstract List<Individual> crossOverTwoChildren(Individual individual1, Individual individual2);

}

