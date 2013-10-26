package neural_net;
/**
 *@author cauthon
 */
public class StructuralInfo {
	
	private int numInputs;
	private int numOutputs;
	private int[] hiddenLayerInformation;
	
	public StructuralInfo(int numInputs, int numOutputs, int[] hiddenLayerInformation) {
		this.numInputs = numInputs;
		this.numOutputs = numOutputs;
		this.hiddenLayerInformation = hiddenLayerInformation;
	}

	public int getNumInputs() {
		return numInputs;
	}

	public void setNumInputs(int numInputs) {
		this.numInputs = numInputs;
	}

	public int getNumOutputs() {
		return numOutputs;
	}

	public void setNumOutputs(int numOutputs) {
		this.numOutputs = numOutputs;
	}

	public int[] getHiddenLayerInformation() {
		return hiddenLayerInformation;
	}

	public void setHiddenLayerInformation(int[] hiddenLayerInformation) {
		this.hiddenLayerInformation = hiddenLayerInformation;
	}
	

}
