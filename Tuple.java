import java.io.Serializable;
import java.lang.Iterable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * This Tuple class is meant to be similar to the Python 
 * data structure, tuple. It keeps the limitations,
 * functionality, and style of Java in mind while implementing 
 * as much of Python as possible. Contents are unmodifiable.
 * 
 * @author  Rahul Sura
 */
public final class Tuple implements Iterable, Cloneable, Serializable{
    
    /**
     * The contents of the Tuple.
     */
    private final Object[] tupleContents;

    /**
     * A default empty Tuple that can be used without creating a new Tuple.
     */
    public static final Tuple emptyTuple = new Tuple();

    /**
     * Color class for formatting terminal output colors (mainly for 
     * {@link #toString}).
     */
    private static class Color{

        /**
         * ANSI Code for purple.
         */
		static final String purple = "\u001b[35m";

        /**
         * ANSI Code for red.
         */
		static final String red = "\u001b[31m";

        /**
         * ANSI Code for resetting the formatting.
         */
		static final String reset = "\u001b[0m";
	}
    
    /**
     * Default constructor. Initializes an empty tuple, equivalent to the
     * {@link #emptyTuple}. Has no contents.
     */
    public Tuple(){
		tupleContents = new Object[0];
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

    /**
     * Prints the current Tuple.
     */
    public void print(){
		System.out.println(this);
	}

    /**
     * Checks if the provided Tuple is equal to this Tuple.
     * @param   t A Tuple to be compared with.
     * @return  A boolean if the two Tuples are the same.
     */
	public boolean equals(Tuple t){
		if (this.tupleContents.length != t.tupleContents.length){
			return false;
		}
		if (this.tupleContents == t.tupleContents){
			return true;
		}

		for (int i = 0; i < tupleContents.length; i++) {
			if (t.get(i).getClass() == Tuple.class){
				if(!equals(t.get(i))) return false;
			}
			if (!t.get(i).equals(this.get(i))){
				return false;
			}
		}
		return true;
	}

    /**
     * Checks if the provided Tuple is equal to the passed Object.
     * @param   t A Tuple to be compared with.
     * @return  A boolean if the two Tuples are the same.
     */
    @Override
	public boolean equals(Object obj) {
		if(obj.getClass() == Tuple.class){
			return equals((Tuple) obj);
		}
		return false;
	}

    /**
     * Checks if an object's declaring class has an overriden
     * toString method. 
     * @param   o The object to check.
     * @return  A boolean representing if the toString is overriden.
     */
    private static boolean containsToString(Object o) {
        if (o == null){
            return false;
        }
		try {
			return o.getClass().getMethod("toString").getDeclaringClass() != Object.class;
		} catch (Exception ignored){}
		return false;
	}

    /**
     * Replaces a string's new line character with a literal "\n" and tab character 
     * with a "\t" literal. This is to prevent a Tuple being printed on multiple lines.
     * @param   s A string to be modified.
     * @return  The modified string.
     */
	private static String removeNewLineAndTabs(String s){
		StringBuilder condensed = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c != '\n' && c != '\t'){
				condensed.append(c);
			} else if (c == '\n'){
				condensed.append(Color.purple).append("\\n").append(Color.reset);
			} else {
				condensed.append(Color.purple).append("\\t").append(Color.reset);
			}
		}

		return condensed.toString();
	}

    /**
     * Appends elements of the Tuple in a cleaner way to make the toString more visible
     * of the different types of data.
     * @param   o The object (element) to append to the stringbuilder.
     * @param   s The stringbuilder to append the object to.
     */
    private void appendElement(Object o, StringBuilder s){
		if (Objects.isNull(o)){
			s.append(Color.purple).append("null").append(Color.reset);
		} else if (o == this) {
			s.append(Color.purple).append("this Tuple").append(Color.reset);
		} else if(o.getClass() == Integer.class || o.getClass() == Double.class ||
				o.getClass() == Float.class || o.getClass() == Long.class ||
				o.getClass() == Byte.class || o.getClass() == Short.class ||
				o.getClass() == BigInteger.class || o.getClass() == BigDecimal.class
		){
			s.append(o);
		} else if (o.getClass() == Boolean.class) {
			s.append(Color.purple).append(o).append(Color.reset);
		} else if (o.getClass() == Character.class){
			s.append("'").append(removeNewLineAndTabs(String.valueOf((char) o))).append("'");
		} else if (o.getClass() == String.class ||
				o.getClass() == StringBuilder.class){
			s.append('"').append(removeNewLineAndTabs(o.toString())).append('"');
		} else if (o.getClass() == Tuple.class){
			s.append(o);
		} else if (o.getClass().isArray()){
			Object[] oArr = new Object[Array.getLength(o)];
			for(int i = 0; i < oArr.length; ++i){
				oArr[i] = Array.get(o, i);
			}
			s.append('[');
			for (int i = 0; i < oArr.length - 1; ++i) {
				if (oArr[i].getClass().isArray() && Arrays.equals(oArr, (Object[]) oArr[i])) {
					s.append("(this Collection)");
				} else {
					appendElement(oArr[i], s);
				}
				s.append(", ");
			}
			if (oArr.length > 0) {
				if (oArr[oArr.length - 1].getClass().isArray() && Arrays.equals(oArr, (Object[]) oArr[oArr.length - 1])) {
					s.append("(this Collection)");
				} else {
					appendElement(oArr[oArr.length - 1], s);
				}
			}
			s.append(']');
		} else if (o instanceof Iterable<?>){
			List<Object> list = new LinkedList<>();
			((Iterable<?>) o).forEach(list::add);
			appendElement(list.toArray(), s);
		} else if (containsToString(o)){
			s.append('"')
				.append(removeNewLineAndTabs(o.toString()))
				.append('"');
		} else {
			s.append(o);
		}
	}

    /**
     * Returns an elegant version of the Tuple's string representation. Makes
     * it relatively evident what type of data each element in the Tuple is,
     * such as putting 'single quotes' around a character or highlighting a null
     * element with different colored text.
     * @return  A string representing the visual of the Tuple.
     */
    @Override
    public String toString(){
		StringBuilder s = new StringBuilder("(");
		for (int i = 0; i < tupleContents.length - 1; i++) {
			appendElement(tupleContents[i], s);
			s.append(", ");
		}
		if(tupleContents.length > 0) appendElement(tupleContents[tupleContents.length - 1], s);
		s.append(")");
		return s.toString();
	}
}