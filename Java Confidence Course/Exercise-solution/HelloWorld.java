/**
 * @author Jiayee
 *
 */
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        
        // Extra
        char[] charArray = {'H', 'e', 'l', 'l', 'o', ',', ' ', 'W', 'o', 'r', 'l', 'd', '!'};
        System.out.print(charArray);                                // print can accept a different data type
        System.out.printf("\n%s", "Hello, World!");                 // need prepend with newline
        System.out.printf("\n%s%s", "Hello, ", "World!");
        System.out.printf("\n%2$s%1$s", "World!", "Hello, ");       // 1$ refers to first argument
        System.out.printf("\n%2$s", "", "Hello, World!");
        System.out.println("\nHello, World!");
        System.out.println(String.format("%s", "Hello, World!"));   // no need newline because println has trailing newline
    }

}
