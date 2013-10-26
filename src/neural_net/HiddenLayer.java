package neural_net;
/**
 *@author cauthon
 */
public class HiddenLayer extends Layer{

	public HiddenLayer(int size) {
		super(size);
		layerActivation = new LogisticActivation();
		buildLayer();
	}

}
