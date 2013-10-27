package ga;

import java.util.ArrayList;
import java.util.List;

public class SelectionFitnessProportionate extends Selection {
	
	public SelectionFitnessProportionate(Population population, Fitness fitness) {
		super(population, fitness);
	}
	
	public List<Individual> select() {
		List<Individual> parents = new ArrayList<Individual>();
		
		// evaluate fitness for every individual in population and find the sum
		population.evaluateFitness(fitness);
		double sum = fitness.getPopulationFitnessSum(population);
		
		// select the two parents weighted by fitness
		parents.add(selectParentProportionate(sum));
		parents.add(selectParentProportionate(sum));
		
		return parents;
	}

}
