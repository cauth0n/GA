package neural_net;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cauthon
 */
public abstract class Layer {
	protected List<Neuron> neurons;
	protected int size;
	protected ActivationFunction layerActivation;
	protected List<Connection> outGoingConnections;

	public Layer(int size) {
		this.size = size;
		neurons = new ArrayList<>(size);
	}

	public void buildLayer() {
		for (int i = 0; i < size; i++){
			neurons.add(new Neuron(layerActivation));
		}
	}

	public List<Neuron> getNeurons() {
		return neurons;
	}

	public void setNeurons(List<Neuron> neurons) {
		this.neurons = neurons;
	}

	public List<Connection> getOutGoingConnections() {
		return outGoingConnections;
	}

	public void setOutGoingConnections(List<Connection> outGoingConnections) {
		this.outGoingConnections = outGoingConnections;
	}
	
	

}
