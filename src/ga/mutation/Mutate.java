package ga.mutation;
import ga.Individual;
import ga.Population;

import java.util.ArrayList;
import java.util.List;


public abstract class Mutate {

	protected double spread = 0.05;
	
	public Population mutate(Population population) {
		List<Individual> newPopulation = new ArrayList<Individual>();
		for (Individual individual : population.getIndividuals()) {
			if (individual.canMutate())
				newPopulation.add(mutate(individual, population));
			else
				newPopulation.add(individual);
		}
		return new Population(newPopulation);
	}
	
	public abstract Individual mutate(Individual individual, Population population);

}

