package neural_net;

/**
 * @author cauthon
 */
public class OutputLayer extends Layer {

	public OutputLayer(int size) {
		super(size);
		layerActivation = new LinearActivation();
		buildLayer();
	}

}
