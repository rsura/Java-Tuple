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
public class Tuple{
    
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
}