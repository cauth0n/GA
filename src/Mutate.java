import java.util.ArrayList;
import java.util.List;


public abstract class Mutate {
	
	protected Population mutate(Population population) {
		List<Individual> newPopulation = new ArrayList<Individual>();
		for (Individual individual : population.getPopulation())
			newPopulation.add(mutate(individual));
		return new Population(newPopulation);
	}
	
	protected abstract Individual mutate(Individual individual);

}

