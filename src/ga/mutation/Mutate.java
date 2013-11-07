package ga.mutation;
import ga.Individual;
import ga.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Mutate {

	protected double spread = 0.3;
	protected Random random = new Random(11235);
	
	public Population mutate(Population population, Double probability) {
		List<Individual> newPopulation = new ArrayList<Individual>();
		

		
		for (Individual individual : population.getIndividuals()) {
			
			if (individual.canMutate() && random.nextDouble() < probability) {
				Individual mutated = mutate(individual, population);
				//newPopulation.add(mutated);
				//System.out.println("---");
				newPopulation.add(individual);
			} else {
				newPopulation.add(individual);
			}
			
		}
		
		return new Population(newPopulation);
	}
	
	public abstract Individual mutate(Individual individual, Population population);

}

