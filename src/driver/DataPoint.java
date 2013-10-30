package driver;

import java.util.List;

/**
 * @author cauthon
 */
public class DataPoint {

	private List<Double> features;
	private int output;

	public DataPoint(List<Double> features, int output) {
		this.features = features;
		this.output = output;
	}

	public List<Double> getFeatures() {
		return features;
	}

	public void setFeatures(List<Double> features) {
		this.features = features;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

}
