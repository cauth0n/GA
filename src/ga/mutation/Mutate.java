package ga.mutation;
import ga.Individual;
import ga.Population;

import java.util.ArrayList;
import java.util.List;


public abstract class Mutate {

	protected double spread = 0.05;
	
	protected Population mutate(Population population) {
		List<Individual> newPopulation = new ArrayList<Individual>();
		for (Individual individual : population.getIndividuals())
			newPopulation.add(mutate(individual, population));
		return new Population(newPopulation);
	}
	
	public abstract Individual mutate(Individual individual, Population population);

}

