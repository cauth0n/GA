package ga.prune;

import ga.Individual;
import ga.Population;

import java.util.List;

public class PruneDefault extends Prune {

	public PruneDefault() {
		
	}
	
	public Population prune(Population population, int newSize) {
		int toRemove = (population.size() - newSize);
		Population newPopulation = population.copy();
		List<Individual> list = newPopulation.getIndividuals();
		for (int pruneIndex = 0; pruneIndex < toRemove; pruneIndex++) {
			list.remove(list.size() - 1);
		}
		return newPopulation;
	}
	
}
