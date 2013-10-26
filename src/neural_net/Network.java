package neural_net;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cauthon
 */
public class Network {
	private List<Layer> layers;
	private StructuralInfo structuralInfo;

	public Network(StructuralInfo structuralInfo) {
		this.structuralInfo = structuralInfo;
	}

	public void constructNetwork() {
		layers = new ArrayList<>();
		Layer input = new InputLayer(structuralInfo.getNumInputs());
		layers.add(0, input);
		for (int hiddenLayerNum = 0; hiddenLayerNum < structuralInfo.getHiddenLayerInformation().length; hiddenLayerNum++) {
			Layer hiddenLayer = new HiddenLayer(structuralInfo.getHiddenLayerInformation()[hiddenLayerNum]);
			layers.add(hiddenLayerNum, hiddenLayer);
		}
		Layer outputLayer = new OutputLayer(structuralInfo.getNumOutputs());
		layers.add(outputLayer);

		buildConnections();
	}

	private void buildConnections() {
		for (int layerNum = 0; layerNum < layers.size() - 1; layerNum++) {
			for (int neuronNum = 0; neuronNum < layers.get(layerNum).getNeurons().size(); neuronNum++) {
				List<Connection> outGoingConnections = new ArrayList<>();
				//for (nextNeuronNum = )
				//need to build connection objects
			}
		}
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}

}
