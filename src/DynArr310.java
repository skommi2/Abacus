import java.lang.reflect.Array;

// TO DO: add your implementation and JavaDocs

// Big Hint: Do not try to implement this in the
// order shown... read the assignment fully first.

public class DynArr310<T> {
	//default initial capacity / minimum capacity
	private static final int DEFAULT_CAPACITY = 2;
	
	//underlying array -- you must use this!
	//we will be "breaking in" to look at it,
	//even though it is private
	private T[] data;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	// number of values added to the array
	private int _length;

	@SuppressWarnings("unchecked")
	public DynArr310() {
		//constructor
		//initial capacity of the array should be DEFAULT_CAPACITY
		//Hint: Can't remember how to make an array of generic Ts? It's in the textbook...
		data = (T[])new Object[DEFAULT_CAPACITY];
		_length = 0;
	}

	@SuppressWarnings("unchecked")
	public DynArr310(final int initialCapacity) {
		// constructor
		// set the initial capacity of the smart array as initialCapacity
		// throw IllegalArgumentException if initialCapacity is smaller than 1
		if (initialCapacity < 1)
			throw new IllegalArgumentException();
		
		data = (T[])new Object[initialCapacity];
		_length = 0;
	}
	
	/**
	 *  Gets the number of beads in the top area of the
	 *  abacus which are in-use for a given place. (The
	 *  number of beads in the top which are pushed down
	 *  to the center.)
	 *  
	 *  @param newCapacity new size of the data array
	 */
	@SuppressWarnings("unchecked")
	private void extendCapcityIfNeeded()
	{
		if (_length < data.length)		// within capacity
			return;

		System.out.println("extendCapcityIfNeeded: before: capacity = " + data.length + ", length = " + _length);
		// will exceed capcity, so double capacity
		final int newCapacity = 2 * data.length;
		final T[] data2 = (T[])new Object[newCapacity];
		System.arraycopy(data, 0, data2, 0, data.length);
		data = data2;

		System.out.println("extendCapcityIfNeeded: after: capacity = " + data.length);
	}

	public int size() {	
		//report number of elements in the smart array
		// O(1)
		
		return _length;
	}  
	
	public int capacity() { 
		//report max number of elements before the next expansion
		// O(1)
		
		return data.length;
	}

	@SuppressWarnings("unchecked")
	public void add(final int index, final T value) {
		// insert value at index, shift elements if needed  
		// double the capacity if no space is available
		// throw IndexOutOfBoundsException for invalid index
		// O(N) where N is the number of elements in the array
		
		// Note: This method may be used to append items as
		// well as insert items, also "null" is a valid item
		// in a list.

		//System.out.println("add: index = " + index + ", length = " + _length + ", capcity = " + data.length);
		if (index < 0 || index > _length)
			throw new IndexOutOfBoundsException("Index should be <= " + _length);

		extendCapcityIfNeeded();

		// shift values to the right by one
		for (int i = _length; i > index; i--)
		{
			data[i] = data[i-1];
		}
		data[index] = value;
		_length++;
	} 
	
	public T get(final int index) {
		// return the item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		
		if (index < 0 || index >= _length)
			throw new IndexOutOfBoundsException("Index should be >= 0 and <= " + (_length - 1));
		
		return data[index];
	}
	
	public T replace(final int index, final T value) {
		// change item at index to be value	
		// return old item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		
		// Note: you cannot add new items with this method
		
		//System.out.println("replace: index = " + index);
		if (index < 0 || index >= _length)
			throw new IndexOutOfBoundsException("Index should be >= 0 and <= " + (_length - 1));

		final T oldItem = data[index];
		data[index] = value;

		return oldItem;
	}
	
	public int firstIndexOf(final T value) {
		// return the index of the first occurrence
		// or -1 if not found, remember, this may
		// have to search for nulls!
		// O(N) where N is the number of elements in the list
		
		for (int i = 0; i < _length; i++)
		{
			if (value == null)
			{
				if (data[i] == null)
					return i;
			}
			else if (data[i] != null)
			{
				if (data[i].equals(value))
					return i;
			}
		}

		return -1;
	}

	@SuppressWarnings("unchecked")
	public T delete(final int index) {
		// remove and return element at position index
		// shift elements to remove any gap in the array
		// throw IndexOutOfBoundsException for invalid index
		
		// halve capacity if the number of elements falls below 1/4 of the capacity
		// capacity should NOT go below DEFAULT_CAPACITY
		
		// O(N) where N is the number of elements in the list
		
		// check whether a valid index
		System.out.println("delete: index = " + index);
		if (index < 0 || index >= _length)
			throw new IndexOutOfBoundsException("Index should be >= 0 and <= " + (_length - 1));

		final T oldItem = data[index];

		// shift values to left by one
		for (int i = index+1; i < _length; i++)
		{
			data[i-1] = data[i];
		}
		_length--;
		
		if (_length < data.length/4)
			setCapacity(data.length/2);
		
		return oldItem;
	}  

	public int deleteAll(final T value) {
		// delete all values equal to the provided one
		// don't forget about nulls!
		// returns the number of items deleted
		
		// worst case _allowed_ is O(N^2), but see if you can
		// do it in O(N)! N is the number of elements in the list
		
		int numDeleted = 0;
		for (int i = 0; i < _length; i++)
		{
			if (value == null && data[i] == null)
			{
				numDeleted++;
			}
			else if (value != null && data[i] != null && data[i].equals(value))
			{
				numDeleted++;
			}
			else if (numDeleted > 0)	// shift items
			{
				data[i-numDeleted] = data[i];
			}
		}
		_length -= numDeleted;

		return numDeleted;
	}

	@SuppressWarnings("unchecked")
	public boolean setCapacity(final int newCapacity) {
		// change the max number of items allowed before next expansion to newCapacity
		
		// capacity should not be changed if:
		//   - newCapacity is below DEFAULT_CAPACITY; or 
		//   - newCapacity is not large enough to accommodate current number of items
		
		// return true if newCapacity gets applied; false otherwise
		// O(N) where N is the number of elements in the array
		
		System.out.println("setCapacity: before: capacity = " + data.length + ", #items = " + _length + ", newCapcity = " + newCapacity);
		if (newCapacity < _length || newCapacity <= DEFAULT_CAPACITY || newCapacity == data.length)
			return false;
		
		final T[] data2 = (T[])new Object[newCapacity];
		System.arraycopy(data, 0, data2, 0, _length);
		data = data2;

		System.out.println("setCapacity: after: capacity = " + data.length);

		return true; //default return, make sure to remove/change
	}
	
	@Override
	public boolean equals(final Object o) {
		// Two DynArr310s are equal if all their elements
		// are equal (remember, there might be nulls! they
		// need to be equal too).
		
		// O(N) where N is the number of elements in the array
	
		// DO NOT add @SuppressWarnings to this, just use <?>
		// correctly.
		
		DynArr310<T> right = (DynArr310<T>) o;
		if (_length == right._length)
		{
			for (int i = 0; i < _length; i++)
			{
				if (data[i] != null && right.data[i] != null)
				{
					if (!data[i].equals(right.data[i]))
						break;
				}
				else if (data[i] == null && right.data[i] == null)
				{
				}
				else	// one of them is a null, so different
				{
					break;
				}
			}
			return true;
		}

		return false;
	}
	
	public static <E> DynArr310<E> clone(final DynArr310<E> orig) {
		// makes a copy of the dynamic array and all its current
		// values don't forget to set the capacity!
		// O(N) where N is the number of elements in the array
	
		// Note: this should create a deep copy of the original,
		// BUT make _shallow_ copies of any elements _in_ the
		// original. So don't just do this: return orig;
		// But don't worry about trying to deep-copy Es
		// (since E can be any type that's complicated).
		
		final DynArr310<E> newArr = new DynArr310<E>(orig.capacity());
		System.arraycopy(orig.data, 0, newArr.data, 0, orig._length);
		newArr._length = orig._length;

		return newArr;
	}
	
	public static <E> boolean isClone(final DynArr310<E> arr1, final DynArr310<E> arr2) {
		// O(N) where N is the number of elements in the array
	
		// remember that clones are different than equal lists,
		// clones have to have a shallow copy of elements
		// and not be at the same memory address
		
		if (arr1._length == arr2._length)
		{
			for (int i = 0; i < arr1._length; i++)
			{
				if (arr1.data[i] != null && arr2.data[i] != null)
				{
					if (arr1.data[i] != arr2.data[i])
						return false;
				}
				else if (arr1.data[i] == null && arr2.data[i] == null)
				{
				}
				else	// one of them is a null, so different
				{
					return false;
				}
			}
			return true;
		}

		return false; //default return, make sure to remove/change
	}
	
	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	//
	// WARNING: This is NOT a complete set of tests!
	// For example there are no tests for nulls...
	// but there will be for grading! You need to do
	// additional testing.
	// --------------------------------------------------------
	public static void main(final String args[]) {

		System.out.println("\n==========================\nStep 1:");
		//create a dynamic array of integers
		final DynArr310<Integer> nums = new DynArr310<>();
		if((nums.size() == 0) && (nums.capacity() == 2)){
			System.out.println("Yay 1");
		}

		System.out.println("\n==========================\nStep 2:");
		//append some numbers 
		for(int i = 0; i < 3; i++) {
			nums.add(i,i*2);
		}
		
		if(nums.size() == 3 && nums.get(2) == 4 && nums.capacity() == 4){
			System.out.println("Yay 2");
		}
		
		System.out.println("\n==========================\nStep 3:");
		//create a dynamic array of strings
		final DynArr310<String> msg = new DynArr310<>();
		
		//insert some strings
		msg.add(0,"world");
		msg.add(0,"hello");
		msg.add(1,"new");
		msg.add(3,"!");
		
		//replace and checking
		if (msg.get(0).equals("hello") && msg.replace(1,"beautiful").equals("new") 
			&& msg.size() == 4 && msg.capacity() == 4){
			System.out.println("Yay 3");
		}
		
		//change capacity
		if (!msg.setCapacity(0) && !msg.setCapacity(3) && msg.setCapacity(20)
			&& msg.capacity() == 20){
			System.out.println("Yay 4");
		}	 

		//delete and shrinking
		if (msg.delete(1).equals("beautiful") && msg.get(1).equals("world")  
			&& msg.size() == 3 && msg.capacity() == 10 ){
			System.out.println("Yay 5");
		}

		System.out.println("\n==========================\nStep 4:");
		//firstIndexOf and clone
		//remember what == does on objects... not the same as .equals()
		final DynArr310<String> msgClone = DynArr310.clone(msg);
		if (msgClone != msg && msgClone.get(1) == msg.get(1)
			&& msgClone.size() == msg.size()
			&& msgClone.capacity() == msg.capacity()
			&& msgClone.firstIndexOf("world") == 1
			&& msgClone.firstIndexOf("beautiful") == -1) {
			System.out.println("Yay 6");
		}
		
		System.out.println("\n==========================\nStep 5:");
		//isClone() and equals()
		
		//make a cat class for testing
		class Cat {
			String name;
			public Cat(final String name) {
				this.name = name;
			}
			public boolean equals(final Object o) {
				if(o instanceof Cat) {
					return this.name.equals(((Cat)o).name);
				}
				return false;
			}
		}
		
		System.out.println("\n==========================\nStep 6:");
		final DynArr310<Cat> cats1 = new DynArr310<>();
		final DynArr310<Cat> cats2 = new DynArr310<>();
		cats1.add(0, new Cat("Fred"));
		cats2.add(0, new Cat("Fred"));
		final DynArr310<Cat> cats3 = DynArr310.clone(cats2);
		
		System.out.println("\n==========================\nStep 7:");

		if(!DynArr310.isClone(cats1, cats2) && cats1.equals(cats2)
			&& DynArr310.isClone(cats2, cats3) && cats2.equals(cats3)) {
			System.out.println("Yay 7");
		}
		
		System.out.println("\n==========================\nStep 8:");
		//and some misc items
		cats3.add(1, new Cat("Fred"));
		cats3.add(2, new Cat("Fred"));
		final DynArr310<Cat> cats4 = DynArr310.clone(cats2);
		cats4.replace(0, new Cat("Alex"));
		if(!DynArr310.isClone(cats2, cats3) && !cats2.equals(cats3)
			&& !DynArr310.isClone(cats2, cats4) && !cats2.get(0).equals(cats4.get(0))
			&& cats3.deleteAll(new Cat("Fred")) == 3 && cats3.size() == 0) {
			System.out.println("Yay 8");
		}

		System.out.println("\n==========================\nDone.");
	}
	
	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------
	
	// this toString() method might be useful for testing
	public String toString() {
		if(size() == 0) return "";
		
		final StringBuffer sb = new StringBuffer();
		sb.append(get(0));
		for(int i = 1; i < size(); i++) {
			sb.append(", ");
			sb.append(get(i));
		}
		return sb.toString();
	}
}