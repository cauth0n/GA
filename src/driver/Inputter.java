package driver;

import java.util.List;

/**
 * @author cauthon
 */
public abstract class Inputter {
	protected int inputs;
	protected int outputs;
	protected List<DataPoint> data;
	protected List<String> possibleClasses;

	/**
	 * Abstract method to parse the file and create the list of data
	 * points. Needs to be implemented for each parser.
	 */
	public abstract void parseFile();

	/**
	 * Abstract method to build and find all the output classes. This
	 * information is stored in the possibleClasses list.
	 * 
	 * This information will eventually be used to determine the
	 * meaning of output neurons for non-numerical classification
	 * problems. The index in which a class exists in this possible
	 * classes list corresponds to which output neuron we hope get
	 * selected.
	 */
	public abstract void findClasses();

	public int getInputs() {
		return inputs;
	}

	public int getOutputs() {
		return outputs;
	}

	public List<DataPoint> getData() {
		return data;
	}
	
	public int getClassIndex(String classname) {
		return possibleClasses.indexOf(classname);
	}

}
