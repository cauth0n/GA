package ga.selection;

import ga.Individual;
import ga.Population;
import ga.fitness.Fitness;

import java.util.ArrayList;
import java.util.List;

public class SelectionRankBasedElitist extends Selection {

	private int numElitist = 1;// keeps n best individuals

	public SelectionRankBasedElitist(Fitness fitness) {
		super(fitness);
	}

	@Override
	public void select(Population population) {
		super.select(population);
		
		//TODO -- fix. This basically returns the best individual multiple times. Need to only return that individual once.
		//Consider building a set of pairs of selections... 
		List<Individual> parents = new ArrayList<>(numElitist);
		
		population.sortPopulationByFitness();

		for (int nextBest = 0; nextBest < numElitist; nextBest++){
			population.getIndividuals().get(nextBest).setMutate(false);//make is so the elite cannot mutate. 
			parents.add(population.getIndividuals().get(nextBest));
		}
		for (int remaining = population.size() - 1; remaining >= numElitist; remaining--){
			population.getIndividuals().get(remaining).setMutate(true);//make it so every non-elite individual can mutate.
		}
		if (parents.size() < 2){
			parents.add(population.getRandomIndividual());
		}
		
		plan.add(parents.get(0), parents.get(1));
		
	}

}
