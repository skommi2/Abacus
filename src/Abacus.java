// --------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE
// --------------------------------------------------------

/**
 *  Interface for an abacus for the AbacusGUI to use.
 *  @author K. Raven Russell
 */	
public interface Abacus {
	/**
	 *  Gets the number base of the abacus. This base
	 *  will never be an odd number and never be less
	 *  than 2.
	 *  
	 *  @return the number base of the abacus
	 */
	public int getBase();
	
	/**
	 *  Returns the number of places (bead columns) the
	 *  abacus is using to represent the number. This
	 *  will never be less than one.
	 *  
	 *  @return the number of places in use
	 */
	public int getNumPlaces();
	
	/**
	 *  Gets the number of beads in the top area of the
	 *  abacus which are in-use for a given place. (The
	 *  number of beads in the top which are pushed down
	 *  to the center.)
	 *  
	 *  @param place the beads column of interest (0 is the right-most place)
	 *  @return the number of beads currently in use
	 *  @throws IndexOutOfBoundsException if the place requested is not in use
	 */
	public int getBeadsTop(int place);
	
	/**
	 *  Gets the number of beads in the bottom area of the
	 *  abacus which are in-use for a given place. (The
	 *  number of beads in the bottom which are pushed up
	 *  to the center.)
	 *  
	 *  @param place the beads column of interest (0 is the right-most place)
	 *  @return the number of beads currently in use
	 *  @throws IndexOutOfBoundsException if the place requested is not in use
	 */
	public int getBeadsBottom(int place);
	
	/**
	 *  Adds the given string representation of a number to
	 *  the current value of the abacus. The abacus is updated
	 *  to this new position. It returns the steps to perform
	 *  the add (snap shots of the abacus at each step). The
	 *  abacus may be left in an "improper state" if the
	 *  provided arguements are invalid.
	 *  
	 *  <p>A snapshot is required for each of the following steps:
	 *  <ul>
	 *  <li>- the initial state
	 *  <li>- the final state
	 *  <li>- expansions (beads should not be moved, the abacus just
	 *    becomes bigger/smaller)
	 *  <li>- exchanges (beads are exchanged in one step)
	 *  <li>- movement of X beads up OR down (not both at the same time)
	 *    in ONE place on the bottom OR top of the abacus (not both
	 *    at the same time)
	 *  </ul>
	 *  
	 *  @param value the string representation of the value to add (e.g. "100" in base 10, or "1f" in base 16)
	 *  @return the different positions the abacus was in (including the start and finish states)
	 *  @throws NumberFormatException if string is not correct for the base
	 */
	public DynArr310<Abacus> add(String value);
}