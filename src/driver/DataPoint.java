package driver;

import java.util.List;
import java.util.Map;

/**
 * @author cauthon
 */
public class DataPoint {

	private List<Double> features;
	private String output;

	public DataPoint(List<Double> features, String output) {
		this.features = features;
		this.output = output;
	}

	public List<Double> getFeatures() {
		return features;
	}

	public void setFeatures(List<Double> features) {
		this.features = features;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
