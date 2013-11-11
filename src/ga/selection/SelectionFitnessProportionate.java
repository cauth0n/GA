package ga.selection;

import ga.Individual;
import ga.Population;
import ga.fitness.Fitness;

import java.util.ArrayList;
import java.util.List;

public class SelectionFitnessProportionate extends Selection {
	
	public SelectionFitnessProportionate(Fitness fitness) {
		super(fitness);
	}
	
	public void select(Population population) {
		super.select(population);
		
		// evaluate fitness for every individual in population and find the sum
		double sum = population.getFitness();
		
		// select the two parents weighted by fitness
		plan.add(selectParentProportionate(sum), selectParentProportionate(sum));
		
	}

}

