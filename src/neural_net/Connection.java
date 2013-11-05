package neural_net;

import java.util.Random;

/**
 * @author cauthon
 */
public class Connection {

	private Neuron fromNeuron;
	private Neuron toNeuron;
	private double weight;
	private double weightChange;

	public Connection(Neuron fromNeuron, Neuron toNeuron) {
		this.fromNeuron = fromNeuron;
		this.toNeuron = toNeuron;
		weight = getRandomWeight();
		weightChange = 0;
	}

	private double getRandomWeight() {
		Random rand = new Random();
		double toRet = rand.nextDouble();
		if (rand.nextBoolean()){
			toRet *= -1.0;
		}
		return toRet;
	}
	
	public void appendWeight(){
		this.weight += weightChange;
	}

	public Neuron getFromNeuron() {
		return fromNeuron;
	}

	public Neuron getToNeuron() {
		return toNeuron;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWeightChange() {
		return weightChange;
	}

	public void setWeightChange(double weightChange) {
		this.weightChange = weightChange;
	}

}
