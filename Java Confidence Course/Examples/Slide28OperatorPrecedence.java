/**
 * @author Jiayee
 *
 */
public class Slide28OperatorPrecedence {

    public static void main(String[] args) {
        // Floats, doubles
        // Prefix and post-fix increment/decrement operators
        // Add/subtract/multiply/divide/modulo and assign operators
        // Comparators and logic operators

        double i = 1.23;
        i++;
        System.out.printf("i == (1.23 + 1) returns\t%b\n", i == (1.23 + 1));
        i *= 4.56;
        System.out.println(i == (1.23 + 1) * 4.56);
        System.out.println(++i == (1.23 + 1) * 4.56 + 1);
        System.out.println("i < 2017 returns " + (i < 2017)); // need parenthesis because precedence of arithmetic is higher than inequality
        System.out.println("!(i > 2017) returns " + !(i > 2017));

        float j = 7.89f;
        System.out.println("Postfix incrementor increments values only in the following line.");
        System.out.println("j: " + j++);
        System.out.println("j: " + j);

        float k = 10.12f;
        System.out.println("Prefix incrementor increments values within the line.");
        System.out.println("k: " + k);
        System.out.println("k: " + --k);

        j /= 3.45;
        System.out.println(j); // float is less precise than double

        k %= 6.78; // 9.12 - 6.78 = 2.34
        System.out.println(k);
        System.out.println("j == k && j != k returns " + (j == k && j != k) + " since it is a contradiction.");
        System.out.println("j <= j && j >= j returns " + (j <= j && j >= j) + " since it is a tautology.");
    }

}
