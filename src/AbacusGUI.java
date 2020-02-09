import java.util.Scanner;

/**
 *  A little GUI to help you interact with the abacus.
 *  Use with the command:
 *      java AbacusGUI
 *  or
 *      java AbacusGUI full
 *  
 *  @author K. Raven Russell
 */	
public class AbacusGUI {
	/**
	 *  The main method that presents the GUI.
	 *  
	 *  @param args command line args (if first is "full" shows steps, otherwise ignored)
	 */
	public static void main(String[] args) {
		boolean full = (args.length == 1 && args[0].equals("full"));
		
		try (Scanner s = new Scanner(System.in)) {
			Abacus a = null;
			while(true) {
				try {
					System.out.print("Enter a base: ");
					a = new MyAbacus(s.nextInt());
					s.nextLine();
					printAbacus(a);
					break;
				}
				catch(RuntimeException e) {
					System.out.println(e.toString());
				}
			}
			
			while(true) {
				System.out.print("What would you like to add? (Enter anything invalid to quit.) ");
				if(full) {
					fullPrintAdd(a, s.nextLine());
				}
				else {
					a.add(s.nextLine());
					printAbacus(a);
				}
			}
		}
        catch(Exception e) {
			System.out.println("Goodbye!");
		}
	}
	
	/**
	 *  Adds the value to the abacus, then prints all the steps
	 *  this is probably useful for debugging.
	 *  
	 *  @param a the abacus to add to
	 *  @param value the value to add to the abacus
	 */
	public static void fullPrintAdd(Abacus a, String value) {
		DynArr310<Abacus> steps = a.add(value);
		
		System.out.println("------------------------------");
		System.out.println("- Starting State");
		System.out.println("------------------------------");
		printAbacus(steps.get(0));
		
		System.out.println("------------------------------");
		System.out.println("- Adding " + value);
		System.out.println("------------------------------");
		for(int i = 1; i < steps.size(); i++) {
			printAbacus(steps.get(i));
		}
		
		System.out.println("------------------------------");
		System.out.println("- Done");
		System.out.println("------------------------------");
	}
	
	/**
	 *  Prints a single abacus with amazing graphics.
	 *  
	 *  @param a the abacus to print
	 */
	public static void printAbacus(Abacus a) {
		StringBuilder sb = new StringBuilder();
		
		//build abacus structure
		int bottomSize = a.getBase()/2;
		int numPlaces = a.getNumPlaces();
		int width = (numPlaces*4)+4;
		
		//top
		sb.append("/ ");
		printRows(sb, numPlaces, "---"); //em-dashes
		sb.append(" \\\n");
		
		//top beads
		for(int i = 0; i < 3; i++) {
			sb.append("| ");
			printRows(sb, numPlaces, " | "); //em-dashes
			sb.append(" |\n");
		}
		
		//middle
		sb.append("| ");
		printRows(sb, numPlaces, "---");
		sb.append(" |\n");
		
		//bottom beads
		for(int i = 0; i < (bottomSize)+2; i++) {
			sb.append("| ");
			printRows(sb, numPlaces, " | "); //em-dashes
			sb.append(" |\n");
		}
		
		//bottom
		sb.append("\\ ");
		printRows(sb, numPlaces, "---");
		sb.append(" /\n");
		
		//put beads in place
		for(int i = 0; i < numPlaces; i++) {
			//top beads
			int topBeads = a.getBeadsTop(i);
			for(int j = 0; j < 3; j++) {
				if(topBeads != j) {
					int spot = calcSpotTop(i, width, 3-j);
					sb.setCharAt(spot,'O');
				}
			}
			
			int bottomBeads = a.getBeadsBottom(i);
			for(int j = 0; j < bottomBeads; j++) {
				int spot = calcSpotBottom(i, width, j);
				sb.setCharAt(spot,'O');
			}
			for(int j = 0; j < bottomSize-bottomBeads; j++) {
				int spot = calcSpotBottom(i, width, bottomSize-j+1);
				sb.setCharAt(spot,'O');
			}
		}
		
		System.out.println(sb.toString());
	}
	
	/**
	 *  Calculaton for the placement of a single bead
	 *  on the top.
	 *  
	 *  @param place the place (column) of the abacus (0 is rightmost)
	 *  @param width the width of the ascii art
	 *  @param num the bead position (1=top,2=middle,3=bottom)
	 *  @return the index into the ascii art to draw the bead
	 */
	private static int calcSpotTop(int place, int width, int num) {
		int spot = (width*num) + (width - ((place+1)*4) - 1);
		return spot;
	}
	
	/**
	 *  Calculaton for the placement of a single bead
	 *  on the bottom.
	 *  
	 *  @param place the place (column) of the abacus (0 is rightmost)
	 *  @param width the width of the ascii art
	 *  @param num the bead position (1 to base/2, 1 is at the top)
	 *  @return the index into the ascii art to draw the bead
	 */
	private static int calcSpotBottom(int place, int width, int num) {
		int spot = (width*(num+5)) + (width - ((place+1)*4) - 1);
		return spot;
	}
	
	/**
	 *  Prints a row of some pattern to the given StringBuilder.
	 *  
	 *  @param sb the builder to append to
	 *  @param numPlaces the number of the sequence to add
	 *  @param seq the sequence to repeat
	 */
	private static void printRows(StringBuilder sb, int numPlaces, String seq) {
		for(int i = 0; i < numPlaces; i++) {
			sb.append(seq); 
			if(i != numPlaces-1) sb.append(" ");
		}
	}
}