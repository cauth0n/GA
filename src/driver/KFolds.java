package driver;

import java.util.ArrayList;
import java.util.List;

public class KFolds {
	
	int foldIndex = 0;
	int k;
	List<DataPoint> data;
	List<DataPoint> train;
	List<DataPoint> test;
	
	/**
	 * Constructs a KFold validation class that will
	 * rotate the train and test set through the data
	 * k times.
	 * 
	 * @param data	The data found by Inputter.
	 * @param k		The number of folds that will be processed.
	 */
	KFolds(List<DataPoint> data, int k) {
		this.data = data;
		this.k = k;
	}
	
	/**
	 * Get the next fold, resetting the train and test sets
	 * in a rotational manner.
	 * 
	 * @return	True if another valid fold was found, False if all folds are completed.
	 */
	public boolean next() {
		if (foldIndex >= k - 1) {
			train = null;
			test = null;
			return false;
		}
		partition();
		foldIndex++;
		return true;
	}
	
	
	/**
	 * Partitions the data into train and test sets
	 * based on the current fold.
	 */
	private void partition() {
		// calculate test set size
		int testSetSize = data.size() / k;
		
		train = new ArrayList<>();
		test = new ArrayList<>();
		
		// loop through all datapoints
		for (int dataIndex = 0; dataIndex < data.size(); dataIndex++) {
			
			DataPoint datapoint = data.get(dataIndex);
			
			// calculate the min and max index values for the test set
			int minWindow = foldIndex * testSetSize;
			int maxWindow = minWindow + testSetSize;
			
			// if index falls within test window, add to test set
			// add everything else to train set
			if (dataIndex >= minWindow && dataIndex < maxWindow)
				test.add(datapoint);
			else
				train.add(datapoint);
		}
	}
	
	/**
	 * @return	The train set for the current fold.
	 */
	public List<DataPoint> getTrainSet() {
		return train;
	}
	
	/**
	 * @return	The test set for the current fold.
	 */
	public List<DataPoint> getTestSet() {
		return test;
	}

}
