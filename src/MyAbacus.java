// TO DO: add your implementation and JavaDocs

public class MyAbacus implements Abacus {

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	// Remember: Using an array in this class = no credit on the project!
	private int _base;
	private int _halfbase;
	private DynArr310<Integer> _top;
	private DynArr310<Integer> _bottom;

	public MyAbacus(int base) {
		// throws IllegalArgumentException if base is invalid
		// remember: an abacus should always have at least one
		// column!
		if (base < 2 || base % 2 != 0)
			throw new IllegalArgumentException("base - should be an even number and >= 2.");
		_base = base;
		_halfbase = base / 2;
		_top = new DynArr310<>(10);
		_bottom = new DynArr310<>(10);
		_top.add(0, 0);
		_bottom.add(0, 0);
	}
	
	public int getBase() {
		// O(1)
		return _base;
	}

	public int getNumPlaces() {
		// O(1)
		return _top.size();
	}
	
	public int getBeadsTop(int place) {
		// O(1)
		return _top.get(place);
	}
	
	public int getBeadsBottom(int place) {
		// O(1)
		return _bottom.get(place);
		//return -1; //default return, make sure to remove/change
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

		System.out.println("====================");
		System.out.println("Add: value = " + value);
		System.out.println("");
		DynArr310<Abacus> ret = new DynArr310<>(10);
		// starting state
		ret.add(ret.size(), this.clone());

		if (value.length() > _top.size())	// expand the number of columns
		{
			expandColumns(value.length());
			ret.add(ret.size(), this.clone());	// expanded state
		}
		for (int i = value.length()-1; i >= 0; i--)
		{
			String digitString = value.substring(value.length()-i-1, value.length()-i);
			int digit = Integer.parseInt(digitString);
			int topValue = _top.get(i);
			int bottomValue = _bottom.get(i);

			System.out.println("Apply: place = " + i + ", digit = " + digit + ", topValue = " + topValue + ", bottomValue = " + bottomValue);

			//int b1 = digit / _halfbase;
			if (digit > _halfbase)	// apply to top, the digit is >= half of base
			{
				System.out.println("apply: split: " + _halfbase + " and " + (digit-_halfbase));

				// _top.replace(i, topValue + 1);
				// captureState(ret);	// capture state for adding to top
				addTop(i, ret);
				digit -= _halfbase;
			}
			else
			{
				System.out.println("apply: bottom: " + digit);

				// if ((digit + bottomValue) > _halfbase)
				// {
				// 	System.out.println("apply: with exchange to top (+1) : " + (bottomValue + digit - _halfbase));

				// 	topValue = _top.get(i);
				// 	_top.replace(i, topValue + 1);
				// 	_bottom.replace(i, bottomValue + digit - _halfbase);
				// 	ret.add(ret.size(), this.clone());	// capture state for changing bottom
				// }
				// else
				// {
				// 	_bottom.replace(i, bottomValue + digit);
				// 	ret.add(ret.size(), this.clone());	// capture state for changing bottom
				// }
			}

			addBottom(i, digit, ret);

			topValue = _top.get(i);
			bottomValue = _bottom.get(i);
			System.out.println("Result: place = " + i + ", topValue = " + topValue + ", bottomValue = " + bottomValue);
			System.out.println("");
		}

		return ret; //default return, make sure to remove/change
	}
	
	/**
	 *  look for full beads on top and exchange for one in the previous (left) units bottom.
	 *  
	 *  @param place units place index in the Abacus
	 *  @param ret array that capture the state
	 *  @return true/false if the exchange occurred
	 */	
	private boolean addTop(int place, DynArr310<Abacus> ret)
	{
		int topValue = _top.get(place);

		System.out.println("addTop: place = " + place + ", (before) topValue = " + topValue);
		_top.replace(place, topValue + 1);

		topValue = _top.get(place);
		System.out.println("addTop: place = " + place + ", (after) topValue = " + _top.get(place) + ", bottomValue = " + _bottom.get(place));
		captureState(ret);	// capture state for adding to top
		
		chkTop(place, ret);		// check and exchange if necessary recursively

		return true;
	}

	/**
	 *  look for full beads on top and exchange for one in the previous (left) units bottom.
	 *  
	 *  @param place units place index in the Abacus
	 *  @param ret array that capture the state
	 *  @return true/false if the exchange occurred
	 */	
	private boolean addBottom(int place, int digit, DynArr310<Abacus> ret)
	{
		//int topValue = _top.get(place);
		int bottomValue = _bottom.get(place);

		System.out.println("addBottom: place = " + place + ", (before) bottomValue = " + bottomValue + ", digit = " + digit);

		if (digit == 0)
			return false;
		
		else if ((digit + bottomValue) > _halfbase)
		{
			addBottom(place, _halfbase - bottomValue, ret);
			//chkTop(place, ret);
			addBottom(place, bottomValue + digit - _halfbase, ret);

			// System.out.println("apply: with exchange to top (+1) : " + (bottomValue + digit - _halfbase));
	
			// _bottom.replace(place, _halfbase);
			// digit = bottomValue + digit - _halfbase;

			// System.out.println("addBottom: place = " + place + ", (after) topValue = " + _top.get(place) + ", bottomValue = " + _bottom.get(place));

			// captureState(ret);	// capture state for changing bottom
			// chkBottom(place, ret);
	
			// addBottom(place, digit, ret);
			//_top.replace(place, topValue + 1);
		}
		else
		{
			_bottom.replace(place, bottomValue + digit);

			System.out.println("addBottom: place = " + place + ", (after) topValue = " + _top.get(place) + ", bottomValue = " + _bottom.get(place));

			captureState(ret);	// capture state for changing bottom
			chkBottom(place, ret);
		}
		
		
		return true;
	}

	/**
	 *  look for full beads on top and exchange for one in the previous (left) units bottom.
	 *  
	 *  @param place units place index in the Abacus
	 *  @param ret array that capture the state
	 *  @return true/false if the exchange occurred
	 */	
	private boolean chkTop(int place, DynArr310<Abacus> ret)
	{
		int topValue = _top.get(place);
		int bottomValue = _bottom.get(place);

		if (topValue == 2)	// exchange
		{
			System.out.println("chkTop: place = " + place + " ... requires exchange");
			if (place == _top.size() - 1)	// expand
			{
				System.out.println("chkTop: place = " + place + " ... requires expansion");
				_bottom.add(place+1, 0);
				_top.add(place+1, 0);
				captureState(ret);
			}

			_top.replace(place, 0);
			bottomValue = _bottom.get(place+1);
			_bottom.replace(place+1, bottomValue+1);

			System.out.println("chkTop: place = " + place + ", topValue = " + _top.get(place) + ", bottomValue = " + _bottom.get(place));
			System.out.println("chkTop: place = " + (place+1) + ", topValue = " + _top.get(place+1) + ", bottomValue = " + _bottom.get(place+1));

			captureState(ret);

			chkBottom(place+1, ret);
			return true;
		}

		return false;
	}

	/**
	 *  look for full beads on bottom and exchange for one bead on the top.
	 *  
	 *  @param place units place index in the Abacus
	 *  @param ret array that capture the state
	 *  @return true/false if the exchange occurred
	 */	
	private boolean chkBottom(int place, DynArr310<Abacus> ret)
	{
		int topValue = _top.get(place);
		int bottomValue = _bottom.get(place);

		if (bottomValue == _halfbase)	// exchange
		{
			System.out.println("chkBottom: place = " + place + " ... requires exchange");

			_bottom.replace(place, 0);
			_top.replace(place, topValue+1);

			System.out.println("chkBottom: place = " + place + ", topValue = " + _top.get(place) + ", bottomValue = " + _bottom.get(place));

			captureState(ret);

			chkTop(place, ret);
			return true;
		}

		return false;
	}

	/**
	 *  copies Abacus state.
	 *  
	 *  @param ret array that capture the state
	 */	
	private void captureState(DynArr310<Abacus> ret)
	{
		//int topValue = _top.get(i);
		//int bottomValue = _bottom.get(i);
		//System.out.println("Capture: place = " + i + ", topValue = " + topValue + ", bottomValue = " + bottomValue);

		ret.add(ret.size(), this.clone());	// capture state	
	}

	/**
	 *  Returns copy of this Abacus to retaining the state.
	 *  
	 *  @return copy of this Abacus object
	 */
	public Abacus clone()
	{
		MyAbacus abacus = new MyAbacus(_base);
		abacus._top = DynArr310.clone(this._top);
		abacus._bottom = DynArr310.clone(this._bottom);
		return abacus;
	}

	/**
	 *  Expands the number of columns to a needed state.
	 *  
	 *  @param newSize the number of needed columns
	 *  @return copy of this Abacus object
	 */
	private void expandColumns(int newSize)
	{
		for (int i = _top.size(); i < newSize; i++)
		{
			_top.add(i, 0);
			_bottom.add(i, 0);
		}
	}

	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------
	public static void main(String[] args) {
		//this is the sequence from the project description
		Abacus a = new MyAbacus(10);
		DynArr310<Abacus> steps;
		AbacusGUI.printAbacus(a);
		assert (a.getNumPlaces() == 1);

		AbacusGUI.fullPrintAdd(a, "36");
		assert (a.getNumPlaces() == 2);
		assert (a.getBeadsTop(1) == 0 && a.getBeadsBottom(1) == 3);
		assert (a.getBeadsTop(0) == 1 && a.getBeadsBottom(0) == 1);

		AbacusGUI.fullPrintAdd(a, "12");
		assert (a.getNumPlaces() == 2);
		assert (a.getBeadsTop(1) == 0 && a.getBeadsBottom(1) == 4);
		assert (a.getBeadsTop(0) == 1 && a.getBeadsBottom(0) == 3);

		AbacusGUI.fullPrintAdd(a, "2");
		assert (a.getNumPlaces() == 2);
		assert (a.getBeadsTop(1) == 1 && a.getBeadsBottom(1) == 0);
		assert (a.getBeadsTop(0) == 0 && a.getBeadsBottom(0) == 0);

		AbacusGUI.fullPrintAdd(a, "12");
		assert (a.getNumPlaces() == 2);
		assert (a.getBeadsTop(1) == 1 && a.getBeadsBottom(1) == 1);
		assert (a.getBeadsTop(0) == 0 && a.getBeadsBottom(0) == 2);

		AbacusGUI.fullPrintAdd(a, "10");
		assert (a.getNumPlaces() == 2);
		assert (a.getBeadsTop(1) == 1 && a.getBeadsBottom(1) == 2);
		assert (a.getBeadsTop(0) == 0 && a.getBeadsBottom(0) == 2);

		AbacusGUI.fullPrintAdd(a, "2");
		assert (a.getNumPlaces() == 2);
		assert (a.getBeadsTop(1) == 1 && a.getBeadsBottom(1) == 2);
		assert (a.getBeadsTop(0) == 0 && a.getBeadsBottom(0) == 4);

		AbacusGUI.fullPrintAdd(a, "68");
		assert (a.getNumPlaces() == 3);
		assert (a.getBeadsTop(2) == 0 && a.getBeadsBottom(2) == 1);
		assert (a.getBeadsTop(1) == 0 && a.getBeadsBottom(1) == 4);
		assert (a.getBeadsTop(0) == 0 && a.getBeadsBottom(0) == 2);

		AbacusGUI.fullPrintAdd(a, "50");
		assert (a.getNumPlaces() == 3);
		assert (a.getBeadsTop(2) == 0 && a.getBeadsBottom(2) == 1);
		assert (a.getBeadsTop(1) == 1 && a.getBeadsBottom(1) == 4);
		assert (a.getBeadsTop(0) == 0 && a.getBeadsBottom(0) == 2);

		AbacusGUI.fullPrintAdd(a, "10");
		assert (a.getNumPlaces() == 3);
		assert (a.getBeadsTop(2) == 0 && a.getBeadsBottom(2) == 2);
		assert (a.getBeadsTop(1) == 0 && a.getBeadsBottom(1) == 0);
		assert (a.getBeadsTop(0) == 0 && a.getBeadsBottom(0) == 2);

		AbacusGUI.fullPrintAdd(a, "5");
		assert (a.getNumPlaces() == 3);
		assert (a.getBeadsTop(2) == 0 && a.getBeadsBottom(2) == 2);
		assert (a.getBeadsTop(1) == 0 && a.getBeadsBottom(1) == 0);
		assert (a.getBeadsTop(0) == 1 && a.getBeadsBottom(0) == 2);

		AbacusGUI.fullPrintAdd(a, "3");
		assert (a.getNumPlaces() == 3);
		assert (a.getBeadsTop(2) == 0 && a.getBeadsBottom(2) == 2);
		assert (a.getBeadsTop(1) == 0 && a.getBeadsBottom(1) == 1);
		assert (a.getBeadsTop(0) == 0 && a.getBeadsBottom(0) == 0);

		AbacusGUI.fullPrintAdd(a, "128");
		assert (a.getNumPlaces() == 3);
		assert (a.getBeadsTop(2) == 0 && a.getBeadsBottom(2) == 3);
		assert (a.getBeadsTop(1) == 0 && a.getBeadsBottom(1) == 3);
		assert (a.getBeadsTop(0) == 1 && a.getBeadsBottom(0) == 3);
		
		AbacusGUI.fullPrintAdd(a, "3000000");
		assert (a.getNumPlaces() == 7);
		assert (a.getBeadsTop(6) == 0 && a.getBeadsBottom(6) == 3);
		assert (a.getBeadsTop(5) == 0 && a.getBeadsBottom(5) == 0);
		assert (a.getBeadsTop(4) == 0 && a.getBeadsBottom(4) == 0);
		assert (a.getBeadsTop(3) == 0 && a.getBeadsBottom(3) == 0);
		assert (a.getBeadsTop(2) == 0 && a.getBeadsBottom(2) == 3);
		assert (a.getBeadsTop(1) == 0 && a.getBeadsBottom(1) == 3);
		assert (a.getBeadsTop(0) == 1 && a.getBeadsBottom(0) == 3);

		System.out.println("Done.");
	}
}