package driver;

import ga.DE;

public class Simulator {
	
	public static void main(String[] args) {
		DE de = new DE(10, 0.5, 0.2);
		de.run();
	}
	
}
