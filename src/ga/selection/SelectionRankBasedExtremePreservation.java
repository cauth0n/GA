package ga.selection;

import ga.Individual;
import ga.Population;
import ga.fitness.Fitness;

import java.util.ArrayList;
import java.util.List;

public class SelectionRankBasedExtremePreservation extends Selection {
	
	private int holdoutBest = 3;
	private int holdoutWorst = 2;
	private int returnedBest = 0;
	private int returnedWorst = 0;

	public SelectionRankBasedExtremePreservation(Fitness fitness) {
		super(fitness);
	}

	public void select(Population population) {
		super.select(population);
		
		// best of population
		Individual best1 = population.getMostFit(1);
		Individual best2 = population.getMostFit(2);
		Individual best3 = population.getMostFit(3);
		// worst of population
		Individual worst1 = population.getLeastFit(1);
		Individual worst2 = population.getLeastFit(2);
		Individual worst3 = population.getLeastFit(3);
		
		// add custom pairs, holding out extremes
		best1.setMutate(false);
		plan.add(best1, best1);
		plan.add(best2, best2);
		plan.add(best3, best3);
		plan.add(worst1, worst1);
		//plan.add(worst2, worst2);
		//plan.add(worst3, worst3);
		
		// create a mating pair for every element in the population (will use single child crossover)
		while (plan.size() < population.size()) {
			Individual random1 = population.getRandomIndividual();
			Individual random2 = population.getRandomIndividual();
			plan.add(random1, random2);
		}
		
	}

}
