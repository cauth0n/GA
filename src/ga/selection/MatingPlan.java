package ga.selection;

import ga.Individual;
import java.util.ArrayList;
import java.util.List;

public class MatingPlan {
	
	private List<List<Individual>> pairs = new ArrayList<>();
	private int index = 0;
	
	public MatingPlan() {
		
	}
	
	public void addPair(Individual parent1, Individual parent2) {
		List<Individual> pair = new ArrayList<Individual>(2);
		pair.add(parent1);
		pair.add(parent2);
		pairs.add(pair);
	}
	
	public boolean hasNext() {
		if (index < pairs.size())
			return true;
		else
			return false;
	}
	
	public List<Individual> getNext() {
		return pairs.get(index++);
	}

}
