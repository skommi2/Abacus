// TO DO: add your implementation and JavaDocs

public class MyAbacus implements Abacus {

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	// Remember: Using an array in this class = no credit on the project!
	
	public MyAbacus(int base) {
		// throws IllegalArgumentException if base is invalid
		// remember: an abacus should always have at least one
		// column!
	}
	
	public int getBase() {
		// O(1)
		return -1; //default return, make sure to remove/change
	}

	public int getNumPlaces() {
		// O(1)
		return -1; //default return, make sure to remove/change
	}
	
	public int getBeadsTop(int place) {
		// O(1)
		return -1; //default return, make sure to remove/change
	}
	
	public int getBeadsBottom(int place) {
		// O(1)
		return -1; //default return, make sure to remove/change
	}
	
	public boolean equals(MyAbacus m) {
		// O(N) where N is the number of places currently
		// in use by the abacus
		return false; //default return, make sure to remove/change
	}
	
	public DynArr310<Abacus> add(String value) {
		// Hints:
		//   see: https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#parseInt-java.lang.String-int-
		//   and: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#charAt-int-
		// Also... I personally found a recursive helper function really, really useful here...
		
		// Important: each Abacus in the DynArr310<Abacus> returned should
		// be a copy of this abacus. If you just add this abacus over and over
		// you'll just get the final abacus shown multiple times in the GUI.

		return null; //default return, make sure to remove/change
	}
	
	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------
	public static void main(String[] args) {
		//this is the sequence from the project description
		Abacus a = new MyAbacus(10);
		DynArr310<Abacus> steps;
		AbacusGUI.printAbacus(a);
		AbacusGUI.fullPrintAdd(a, "36");
		AbacusGUI.fullPrintAdd(a, "12");
		AbacusGUI.fullPrintAdd(a, "2");
		AbacusGUI.fullPrintAdd(a, "12");
		AbacusGUI.fullPrintAdd(a, "10");
		AbacusGUI.fullPrintAdd(a, "2");
		AbacusGUI.fullPrintAdd(a, "68");
		AbacusGUI.fullPrintAdd(a, "50");
		AbacusGUI.fullPrintAdd(a, "10");
		AbacusGUI.fullPrintAdd(a, "5");
		AbacusGUI.fullPrintAdd(a, "3");
		AbacusGUI.fullPrintAdd(a, "128");
		AbacusGUI.fullPrintAdd(a, "3000000");
	}
}