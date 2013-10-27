package ga;

import java.util.List;
import java.util.Random;

public abstract class Crossover {
	
	Random random = new Random(11235);
	
	protected abstract Individual crossOverOneChild(Individual individual1, Individual individual2);
	
	protected abstract List<Individual> crossOverTwoChildren(Individual individual1, Individual individual2);

}

