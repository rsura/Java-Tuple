import java.security.InvalidParameterException;

/**
 * This class is to demonstrate some examples 
 * and use cases of my Java Tuple implementation.
 * 
 * @author  Rahul Sura
 */
public class TupleEx {

    /**
     * Returns the properties of a circle from a given
     * radius.
     * @param   radius The radius for a given circle.
     * @return  A Tuple with the shape name, radius, diameter, and area.
     */
    public static Tuple getCircleProperties(int radius){
        if (radius < 0){ // Can't have negative radius
            throw new InvalidParameterException("");
        }
        String basicInfo = "Circle"; // Shape name (string)
        int diameter = radius * 2; // Diameter (int)
        double area = Math.PI * radius * radius; // Area (double)
        return new Tuple(basicInfo, radius, diameter, area); // Tuple of all those
    }

    /**
     * Main method to test the Java Tuple.
     * @param   args [UNUSED]
     */
    public static void main(String[] args) {
        System.out.println("-".repeat(60)); // Top printing divider

        // Example of Tuple being printed
        Tuple t = new Tuple(1,'2',"3", new Tuple(4.0, null,"4\t\n", true));
        System.out.println("Format of Tuple Printing: " + t);

        // Getting a Tuple of a circle's properties based on the radius of 5
        Tuple circleProp = getCircleProperties(5);
        
        // Enhanced for loop iteration. Can use "var" keyword in Java 10 or later or Object instead
        for (var v : circleProp) { 
            System.out.print(v + " ");
        }
        System.out.println();

        // Neatly printing each element of the Circle's properties
        System.out.println(circleProp.get(0).toString() + " has the following properties:");
        System.out.println("\tradius: " + circleProp.get(1));
        System.out.println("\tDiameter: " + circleProp.get(2));
        System.out.println("\tTotal Area: " + circleProp.get(3));

        // Showing variable assignment to Tuple elements
        Tuple numbers = new Tuple(1, 2.0, 3l);
        int i = numbers.get(0);
        double f = numbers.get(1);
        var unknownType = numbers.get(2); // If you don't know the data type, use "var" keyword in Java 10 or later
        System.out.println("Int: " + i + "; Float: " + f + "; unknownType's class: " + unknownType.getClass().getName());

        System.out.println("-".repeat(60)); // Bottom printing divider
    }
}
