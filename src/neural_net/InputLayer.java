package neural_net;
/**
 *@author cauthon
 */
public class InputLayer extends Layer{
	
	public InputLayer(int size) {
		super(size);
		layerActivation = new LinearActivation();
		buildLayer();
	}
	
}
