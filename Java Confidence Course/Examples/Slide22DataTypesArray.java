/**
 * @author Jiayee
 *
 */
public class Slide22DataTypesArray {

    public static void main(String[] args) {
        boolean isSaturday = true;
        int year = 2017;
        double cumulativeAveragePoint = 5.00;
        char firstLetterOfAlphabet = 'A';
        String reminder = "Watch out for the type of quotation mark!";

        int[] arrayOfIntegers = {1, 2, 3};

        int[] anotherArrayOfIntegers = new int[3];

        int[] yetAnotherArrayOfIntegers;
        yetAnotherArrayOfIntegers = new int[]{7, 8, 9}; // need to indicate what {7, 8, 9} means

        anotherArrayOfIntegers[0] = 4;
        anotherArrayOfIntegers[1] = 5;
        arrayOfIntegers[2] = 6;

        // 2D arrays
        int[][] matrix = new int[5][10];
        
        /*
        int[][] matrix = new int[5][];
        matrix[0] = new int[10];
        matrix[1] = new int[10];
        matrix[2] = new int[10];
        matrix[3] = new int[10];
        matrix[4] = new int[10];
        */

        /*
        int[][] matrix = new int[][]{
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        */

        // Source: http://stackoverflow.com/questions/12231453/syntax-for-creating-a-two-dimensional-array

        System.out.println(arrayOfIntegers.length);
    }

}
