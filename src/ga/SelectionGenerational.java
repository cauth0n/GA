package ga;

import java.util.ArrayList;
import java.util.List;

public class SelectionGenerational extends Selection {
	
	public SelectionGenerational(Population population, Fitness fitness) {
		super(population, fitness);
	}
	
	public List<Individual> select() {
		List<Individual> parents = new ArrayList<Individual>();
		// TODO: selection algorithm
		return parents;
	}

}

