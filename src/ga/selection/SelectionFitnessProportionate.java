package ga.selection;

import ga.Individual;
import ga.Population;
import ga.fitness.Fitness;

import java.util.ArrayList;
import java.util.List;

public class SelectionFitnessProportionate extends Selection {
	
	public SelectionFitnessProportionate(Population population, Fitness fitness) {
		super(population, fitness);
	}
	
	public void select() {
		
		// evaluate fitness for every individual in population and find the sum
		double sum = fitness.getPopulationFitnessSum(population);
		
		// select the two parents weighted by fitness
		plan.add(selectParentProportionate(sum), selectParentProportionate(sum));
		
	}

}

