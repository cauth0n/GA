package driver;

public class ConfusionMatrix {
	
	private int size;
	private int[][] matrix;
	
	public ConfusionMatrix(int size) {
		this.size = size;
		matrix = new int[size][size];
	}
	
	public int size() {
		return size;
	}
	
	public void increment(int expected, int actual) {
		this.matrix[expected][actual]++;
	}
	
	public void printMatrix() {
		System.out.println(this);
	}
	
	public String toString() {
		String value = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				value += String.format("%4s", this.matrix[i][j]);
			}
			value += "\n";
		}
		return value;
	}

}
