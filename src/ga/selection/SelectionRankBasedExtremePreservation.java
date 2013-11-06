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
	private Selection selection;

	public SelectionRankBasedExtremePreservation(Population population, Fitness fitness) {
		super(population, fitness);
		selection = new SelectionTournament(population, fitness, 5);
		selection.setPopulation(population);
	}

	public List<Individual> select() {
		
		List<Individual> parents = new ArrayList<Individual>(2);
		
		// ensure that the best and worst remain intact
		if (returnedBest < holdoutBest) {
			returnedBest++;
			parents = getBest();
			if (parents.size() < 2)
				parents.add(population.getRandomIndividual());
			return parents;
		} else if (returnedWorst < holdoutWorst) {
			returnedWorst++;
			parents = getWorst();
			if (parents.size() < 2)
				parents.add(population.getRandomIndividual());
			return parents;
		}
		
		return selection.select();
	}
	
	private List<Individual> getWorst() {
		List<Individual> parents = new ArrayList<Individual>(2);
		
		// choose best ranking individual
		Individual parent = population.getLeastFit();
		parents.add(parent);
		parents.add(parent);
				
		return parents;
	}
	
	private List<Individual> getBest() {
		List<Individual> parents = new ArrayList<Individual>(2);
		
		// choose worst ranking individual
		Individual parent = population.getMostFit();
		parents.add(parent);
		parents.add(parent);
		
		return parents;
	}
	
	public void setPopulation(Population population) {
		super.setPopulation(population);
		if (selection != null)
			selection.setPopulation(population);
		returnedBest = 0;
		returnedWorst = 0;
	}

}
