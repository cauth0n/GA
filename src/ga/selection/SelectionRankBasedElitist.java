package ga.selection;

import ga.Individual;
import ga.Population;
import ga.fitness.Fitness;

import java.util.ArrayList;
import java.util.List;

public class SelectionRankBasedElitist extends Selection {

	private int numElitist = 2;// keeps n best individuals

	public SelectionRankBasedElitist(Population population, Fitness fitness) {
		super(population, fitness);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Individual> select() {
		List<Individual> parents = new ArrayList<>(2);
		
		population.sortPopulationByFitness();
		
		for (int nextBest = 0; nextBest < numElitist; nextBest++){
			population.getIndividuals().get(nextBest).setMutate(false);//make is so the elite cannot mutate. 
			population.getIndividuals().get(nextBest).setCrossover(false); //make is so the elite can crossover, but is immutable
			parents.add(population.getIndividuals().get(nextBest));
		}
		for (int remaining = population.getSize() - 1; remaining > numElitist; remaining--){
			population.getIndividuals().get(remaining).setMutate(true);//make it so every non-elite individual can mutate.
			population.getIndividuals().get(remaining).setCrossover(true);//make is so every non-elite individual is not immutable
		}
		
		return parents;
	}

}
