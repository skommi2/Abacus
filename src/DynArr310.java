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
	
	@SuppressWarnings("unchecked")
	public DynArr310() {
		//constructor
		//initial capacity of the array should be DEFAULT_CAPACITY
		//Hint: Can't remember how to make an array of generic Ts? It's in the textbook...
	}

	@SuppressWarnings("unchecked")
	public DynArr310(int initialCapacity) {
		// constructor
		// set the initial capacity of the smart array as initialCapacity
		// throw IllegalArgumentException if initialCapacity is smaller than 1
	}
	

	public int size() {	
		//report number of elements in the smart array
		// O(1)
		
		return -1; //default return, make sure to remove/change
	}  
		
	public int capacity() { 
		//report max number of elements before the next expansion
		// O(1)
		
		return -1; //default return, make sure to remove/change
	}

	@SuppressWarnings("unchecked")
	public void add(int index, T value) {
		// insert value at index, shift elements if needed  
		// double the capacity if no space is available
		// throw IndexOutOfBoundsException for invalid index
		// O(N) where N is the number of elements in the array
		
		// Note: This method may be used to append items as
		// well as insert items, also "null" is a valid item
		// in a list.
	} 
	
	public T get(int index) {
		// return the item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		
		return null; //default return, make sure to remove/change
	}
	
	public T replace(int index, T value) {
		// change item at index to be value	
		// return old item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		
		// Note: you cannot add new items with this method
		
		return null; //default return, make sure to remove/change
	}
	
	public int firstIndexOf(T value) {
		// return the index of the first occurrence
		// or -1 if not found, remember, this may
		// have to search for nulls!
		// O(N) where N is the number of elements in the list
		
		return -1; //default return, make sure to remove/change
	}

	@SuppressWarnings("unchecked")
	public T delete(int index) {
		// remove and return element at position index
		// shift elements to remove any gap in the array
		// throw IndexOutOfBoundsException for invalid index
		
		// halve capacity if the number of elements falls below 1/4 of the capacity
		// capacity should NOT go below DEFAULT_CAPACITY
		
		// O(N) where N is the number of elements in the list
		
		// check whether a valid index
		
		return null; //default return, make sure to remove/change
	}  

	public int deleteAll(T value) {
		// delete all values equal to the provided one
		// don't forget about nulls!
		// returns the number of items deleted
		
		// worst case _allowed_ is O(N^2), but see if you can
		// do it in O(N)! N is the number of elements in the list
		
		return -1; //default return, make sure to remove/change
	}

	@SuppressWarnings("unchecked")
	public boolean setCapacity(int newCapacity) {
		// change the max number of items allowed before next expansion to newCapacity
		
		// capacity should not be changed if:
		//   - newCapacity is below DEFAULT_CAPACITY; or 
		//   - newCapacity is not large enough to accommodate current number of items
		
		// return true if newCapacity gets applied; false otherwise
		// O(N) where N is the number of elements in the array
		
		return false; //default return, make sure to remove/change
	}
	
	@Override
	public boolean equals(Object o) {
		// Two DynArr310s are equal if all their elements
		// are equal (remember, there might be nulls! they
		// need to be equal too).
		
		// O(N) where N is the number of elements in the array
	
		// DO NOT add @SuppressWarnings to this, just use <?>
		// correctly.
		
		return false; //default return, make sure to remove/change
	}
	
	public static <E> DynArr310<E> clone(DynArr310<E> orig) {
		// makes a copy of the dynamic array and all its current
		// values don't forget to set the capacity!
		// O(N) where N is the number of elements in the array
	
		// Note: this should create a deep copy of the original,
		// BUT make _shallow_ copies of any elements _in_ the
		// original. So don't just do this: return orig;
		// But don't worry about trying to deep-copy Es
		// (since E can be any type that's complicated).
		
		return null; //default return, make sure to remove/change
	}
	
	public static <E> boolean isClone(DynArr310<E> arr1, DynArr310<E> arr2) {
		// O(N) where N is the number of elements in the array
	
		// remember that clones are different than equal lists,
		// clones have to have a shallow copy of elements
		// and not be at the same memory address
		
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
	public static void main(String args[]) {

		//create a dynamic array of integers
		DynArr310<Integer> nums = new DynArr310<>();
		if((nums.size() == 0) && (nums.capacity() == 2)){
			System.out.println("Yay 1");
		}

		//append some numbers 
		for(int i = 0; i < 3; i++) {
			nums.add(i,i*2);
		}
		
		if(nums.size() == 3 && nums.get(2) == 4 && nums.capacity() == 4){
			System.out.println("Yay 2");
		}
		
		//create a dynamic array of strings
		DynArr310<String> msg = new DynArr310<>();
		
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

		//firstIndexOf and clone
		//remember what == does on objects... not the same as .equals()
		DynArr310<String> msgClone = DynArr310.clone(msg);
		if (msgClone != msg && msgClone.get(1) == msg.get(1)
			&& msgClone.size() == msg.size()
			&& msgClone.capacity() == msg.capacity()
			&& msgClone.firstIndexOf("world") == 1
			&& msgClone.firstIndexOf("beautiful") == -1) {
			System.out.println("Yay 6");
		}
		
		//isClone() and equals()
		
		//make a cat class for testing
		class Cat {
			String name;
			public Cat(String name) {
				this.name = name;
			}
			public boolean equals(Object o) {
				if(o instanceof Cat) {
					return this.name.equals(((Cat)o).name);
				}
				return false;
			}
		}
		
		DynArr310<Cat> cats1 = new DynArr310<>();
		DynArr310<Cat> cats2 = new DynArr310<>();
		cats1.add(0, new Cat("Fred"));
		cats2.add(0, new Cat("Fred"));
		DynArr310<Cat> cats3 = DynArr310.clone(cats2);
		
		if(!DynArr310.isClone(cats1, cats2) && cats1.equals(cats2)
			&& DynArr310.isClone(cats2, cats3) && cats2.equals(cats3)) {
			System.out.println("Yay 7");
		}
		
		//and some misc items
		cats3.add(1, new Cat("Fred"));
		cats3.add(2, new Cat("Fred"));
		DynArr310<Cat> cats4 = DynArr310.clone(cats2);
		cats4.replace(0, new Cat("Alex"));
		if(!DynArr310.isClone(cats2, cats3) && !cats2.equals(cats3)
			&& !DynArr310.isClone(cats2, cats4) && !cats2.get(0).equals(cats4.get(0))
			&& cats3.deleteAll(new Cat("Fred")) == 3 && cats3.size() == 0) {
			System.out.println("Yay 8");
		}
	}
	
	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------
	
	// this toString() method might be useful for testing
	public String toString() {
		if(size() == 0) return "";
		
		StringBuffer sb = new StringBuffer();
		sb.append(get(0));
		for(int i = 1; i < size(); i++) {
			sb.append(", ");
			sb.append(get(i));
		}
		return sb.toString();
	}
}