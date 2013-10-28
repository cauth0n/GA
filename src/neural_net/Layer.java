package neural_net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cauthon
 */
public abstract class Layer {
	protected List<Neuron> neurons;
	protected int size;
	protected ActivationFunction layerActivation;
	protected Map<Neuron, List<Connection>> outGoingConnections;

	public Layer(int size) {
		this.size = size;
		neurons = new ArrayList<>(size);
		outGoingConnections = new HashMap<Neuron, List<Connection>>();
	}

	public void buildLayer() {
		for (int i = 0; i < size; i++) {
			neurons.add(new Neuron(layerActivation));
		}
	}

	public List<Neuron> getNeurons() {
		return neurons;
	}

	public void setNeurons(List<Neuron> neurons) {
		this.neurons = neurons;
	}

	public Map<Neuron, List<Connection>> getOutGoingConnections() {
		return outGoingConnections;
	}

	public void setOutGoingConnections(Map<Neuron, List<Connection>> outGoingConnections) {
		this.outGoingConnections = outGoingConnections;
	}

	public void addKeyValuePair(Neuron key, List<Connection> value) {
		outGoingConnections.put(key, value);
	}

}
