package driver;

import java.util.List;

/**
 * @author cauthon
 */
public class DataPoint {

	private List<Double> features;
	private List<Double> outputs;
	private int classIndex = -1;

	public DataPoint(List<Double> features, List<Double> outputs) {
		setFeatures(features);
		setOutputs(outputs);
	}
	
	public void printFeatures() {
		for (Double value : features)
			System.out.print(value+" ");
		System.out.println();
	}
	
	public void printOutputs() {
		for (Double value : outputs)
			System.out.print(value+" ");
		System.out.println();
	}

	public List<Double> getFeatures() {
		return features;
	}

	public void setFeatures(List<Double> features) {
		this.features = features;
	}
	
	public void setOutputs(List<Double> outputs) {
		this.outputs = outputs;
	}
	
	public List<Double> getOutputs() {
		return outputs;
	}
	
	public int getClassIndex() {
		// find class index if necessary
		if (classIndex < 0) {
			boolean found = false;
			for (int output = 0; output < outputs.size(); output++) {
				if (outputs.get(output) > 0.0) {
					if (found)
						throw new IllegalArgumentException("Output vector must have only one nonzero value for classification.");
					classIndex = output;
					found = true;
				}
			}
		}
		// return the class index
		return classIndex;
	}

}
