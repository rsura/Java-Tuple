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
        if (radius < 0){
            throw new InvalidParameterException("");
        }
        String basicInfo = "Circle";
        int diameter = radius * 2;
        double area = Math.PI * radius * radius;
        return new Tuple(basicInfo, radius, diameter, area);
    }

    /**
     * Main method to test the Java Tuple.
     * @param   args [UNUSED]
     */
    public static void main(String[] args) {
        System.out.println("-".repeat(60));
        Tuple t = new Tuple(1,'2',"3", new Tuple(4.0, null,"4\t\n", true));
        System.out.println("Format of Tuple Printing: " + t);

        Tuple circleProp = getCircleProperties(5);
        for (var v : circleProp) {
            System.out.print(v + " ");
        }
        System.out.println();
        System.out.println(circleProp.get(0).toString() + " has the following properties:");
        System.out.println("\tradius: " + circleProp.get(1));
        System.out.println("\tDiameter: " + circleProp.get(2));
        System.out.println("\tTotal Area: " + circleProp.get(3));

        Tuple numbers = new Tuple(1, 2.0, 3l);
        int i = numbers.get(0);
        double f = numbers.get(1);
        System.out.println(i + " " + f);

        System.out.println("-".repeat(60));
    }
}
