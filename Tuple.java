import java.io.Serializable;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This Tuple class is meant to be similar to the Python 
 * data structure, tuple. It keeps the limitations,
 * functionality, and style of Java in mind while implementing 
 * as much of Python as possible. Contents are unmodifiable.
 * 
 * @author  Rahul Sura
 */
public class Tuple implements Iterable, Cloneable, Serializable{
    
    /**
     * The contents of the Tuple.
     */
    private final Object[] tupleContents;

    /**
     * A default empty Tuple that can be used without creating a new Tuple.
     */
    public static final Tuple emptyTuple = new Tuple();
    
    /**
     * Default constructor. Initializes an empty tuple, equivalent to the
     * {@link #emptyTuple}. Has no contents.
     */
    public Tuple(){
		tupleContents = emptyTuple.tupleContents;
	}

    /**
     * Constructor with a dynamic amount of elements. Can be manually typed in,
     * or can take an object array as input.
     * @param   objects All the objects to be placed in the Tuple.
     */
    public Tuple(Object... objects){
		tupleContents = objects.clone();
	}

    /**
     * Constructor that takes in an iterable and creates a Tuple out of it. The
     * Tuple's contents are still considered objects and will need to be down casted
     * when extracting data from the Tuple.
     * @param   iterable Any data structure that implements the Iterable<?> interface.
     */
    public Tuple(Iterable<?> iterable){
		List<Object> tempList = new LinkedList<>();
		while (iterable.iterator().hasNext()){
			tempList.add(iterable.iterator().next());
		}
		this.tupleContents = tempList.toArray().clone();
	}

    /**
     * Copy constructor. Creates an identical tuple, also with unmodifiable contents.
     * @param   tuple The Tuple to be copied.
     */
    public Tuple(Tuple tuple){
		tupleContents = tuple.tupleContents.clone();
	}

    /**
     * Gets the object from the Tuple. Doesn't require casting when assigning it
     * to a variable, as long as the variable's data type matches, such as in
     * {@code int i = new Tuple(1, "Hello World!").get(0)}. May need casting where 
     * variable type is ambiguous, such as {@code System.out.println(String s);} 
     * versus {@code System.out.println(char[] c);}. In Java 10 or later, the return 
     * contents of this method can be assigned using {@code var obj = myTuple.get(0);}.
     * @param   index The index of the object you want to obtain in the Tuple.
     * @return  The auto-castable object from the Tuple at a given index.
     * @throws  ArrayIndexOutOfBoundsException If the index is not in the Tuple.
     */
    public <Any> Any get(int index) throws ArrayIndexOutOfBoundsException{
		if (index < 0 || index >= tupleContents.length){
			throw new ArrayIndexOutOfBoundsException("Tuple index out of range");
		}
		return ((Any) tupleContents[index]);
	}

    /**
     * Gets the number of objects in the Tuple. Identical to {@link #length()}.
     * @return  The number of objects in the Tuple.
     */
    public int size(){
		return this.tupleContents.length;
	}

    /**
     * Gets the number of objects in the Tuple. Identical to {@link #size()}.
     * @return  The number of objects in the Tuple.
     */
	public int length(){
		return this.tupleContents.length;
	}

    /**
     * Clones the Tuple into another Tuple. Similar to the
     * copy constructor {@link #Tuple(Tuple)}. Implemented method
     * for {@link Cloneable} interface.
     */
    @Override
    public Tuple clone(){
		Object[] oArr = tupleContents.clone();
		return new Tuple(oArr);
	}

    /**
     * An overriden method to allow iteration with the enhanced 
     * {@code for} statement.
     * @return  An iterator for the enhanced for loop.
     */
    @Override
	public Iterator<Object> iterator() {
		return new Iterator<>() {
			private int i = -1;
			@Override
			public boolean hasNext() {
				return i < tupleContents.length - 1;
			}

			@Override
			public Object next() {
				return hasNext() ? tupleContents[++i] : null;
			}
		};
	}
}