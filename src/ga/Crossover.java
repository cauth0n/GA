package ga;

import java.util.List;

public abstract class Crossover {
	
	protected abstract List<Individual> crossOver(Individual individual1, Individual individual2);

}

