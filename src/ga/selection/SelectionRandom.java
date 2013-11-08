package ga.selection;

import ga.Individual;
import ga.Population;
import ga.fitness.Fitness;

import java.util.ArrayList;
import java.util.List;

public class SelectionRandom extends Selection {

	public SelectionRandom(Fitness fitness) {
		super(fitness);
	}

	public void select(Population population) {
		super.select(population);
		
		// create a mating pair for every element in the population (will use single child crossover)
		while (plan.size() < returnSize) {
			Individual random1 = population.getRandomIndividual();
			Individual random2 = population.getRandomIndividual();
			plan.add(random1, random2);
		}
		
	}

}
