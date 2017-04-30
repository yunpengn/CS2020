package cs2020;

import java.io.*;
import java.util.HashMap;

/**
 * Created by jin on 15/3/14.
 *
 * Some notes:
 *
 * My first implementation of this analyzer was to
 * 1) Sort each line input with a Arrays.sort after converting the string into a byte array
 * 2) Get the MD5 hashsum of the array
 * 3) Convert the MD5 hashsum to a string
 * 4) Store hashsum in a hashmap if it's not an existing key, with a value that refers to
 * the number of times this hashsum has been computed (default: 1)
 * 5) Or, increment the counter if the hashsum already exists in the hashmap.
 * 6) With this kind of insertion, we can easily calculate the number of pair combinations
 * by keeping track of the values (counters) in the hashmap.
 *
 * One of the bigger downsides to this implementation is that it is very, very slow. The bulk of
 * the runtime is spent sorting the byte array and calculating the MD5 sum.
 *
 * I was thinking if there's a method to solve this problem without sorting the array and still keeping
 * track of hashcodes and their number of occurrences. A hashCode function that simply sums up the
 * int representation of each character in the string is not unique enough, which will
 * in turn produce many collisions.
 *
 * I came across one way accumulators and commutative hash functions on StackOverflow. If I understood
 * it correctly, these functions can be used to compute a hash of an unordered set (with repeated elements)
 * because of their commutative properties. I have implemented a version of it in charHashCode() below,
 * and it works pretty well.
 *
 * The new hash function brought a 3-4x improvement compared to the original MD5 method.
 *
 * Runtime: O(nk); n = number of lines in the file, k = average number of characters in each line
 *
 * Here are some of my own results..
 *
 * 4.in: Pairs: 47485 | 0.352168 |
 * 5.in: Pairs: 1667 | 0.523615 |
 * 6.in: Pairs: 45871 | 1.211783 |
 * 7.in: Pairs: 324 | 0.697687 |
 * 8.in: Pairs: 1057527 | 1.125942 |
 *
 */
public class DBAnalyzer {

    /**
     * Since the text file uses ASCII encoding, it makes sense to specify the encoding type for InputStreamReader.
     * http://docs.oracle.com/cd/E17802_01/j2ee/j2ee/1.4/docs/tutorial-update2/doc/Encodings.html
     */
    private final String encoding = "US-ASCII";
    private BufferedReader reader;
    private String filePath = null;
    private int pairs = 0;

    public DBAnalyzer (String filePath) {
        this.filePath = filePath;
    }

    public void analyze () {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encoding));
            this.pairs = countPairs();
            reader.close();
            System.out.print("Pairs: " + this.pairs + " | ");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads each line, gets the hash, and updates a HashMap accordingly
     * with the number of times this specific hash has been computed before.
     *
     * The variable pairCount keeps track of the total number of pairs computed. Whenever
     * a pre-computed hash is computed again, pairCount increases by the counter value of that
     * hash in the HashMap, since the String forms pairs with each of the previously
     * computed Strings that share the same hash. The counter value then increases by 1
     * accordingly.
     *
     * In essence, the total number of pairs can be computed by
     * 1) identifying all the keys with a value of >= 2 in the HashMap, since
     * keys with a value of 1 are all unique.
     * 2) Get the summation of each individual value v: 2 + 3 + 4 + .. + (v - 1)
     * 3) Sum up all of the summations to get the answer.
     *
     * Runtime: O(n); n = number of lines
     *
     * @return The number of identical pairs of Strings (lines) in the file.
     */
    public int countPairs(){
        int pairCount = 0;
        try {
            long hash;
            int count;

            // Initialize a HashMap with a capacity = 2 * the number of lines in the file.
            HashMap<Long, Integer> fingerprints = new HashMap<Long, Integer>(2 * Integer.parseInt(reader.readLine()));

            for (String line; (line = reader.readLine()) != null;) {
                hash = charHashCode(line);
                if (!fingerprints.containsKey(hash)) {
                    fingerprints.put(hash, 1);
                } else {
                    count = fingerprints.get(hash);
                    pairCount += count;
                    fingerprints.put(hash, count + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pairCount;
    }

    /**
     * Computes the hash code of a string. The hash function is not affected by
     * the ordering of the characters in the string because the hash code is
     * a sum of modified long representations of each character. Since summing
     * is commutative, lines that contain the same characters with different
     * ordering will produce the same hash code.
     *
     * Multiplication is not used because it will cause an overflow too quickly, even
     * though it is commutative as well.
     *
     * Before the hash code is returned, it is combined with the length of the line
     * to provide more uniqueness in the hash code. This is especially useful
     * when the line length is short (less characters -> less combinations -> more collisions).
     *
     * The hash code is a long because long provides more leeway before a possibility of overflowing than
     * int does. This results in less collisions because the co-domain is larger.
     *
     * Runtime: O(k); k = number of characters in the string
     *
     * @param line
     * @return a commutative hash code.
     */
    public long charHashCode(String line) {
        long total = 0;
        long charAt;

        for (int i = 0; i < line.length(); i++) {
            charAt = (long) line.charAt(i);
            total += (31 << charAt) ^ charAt;
        }

        return (total * line.length()) ^ line.length();
    }


    public static void main(String[] args) {
        final String absoluteFilePath = "/Users/jin/Code/CS2020/PS5Databases/";
        final String[] dbFileNames = {"4.in", "5.in", "6.in", "7.in", "8.in"};

        String dbPath;
        StopWatch watch = new StopWatch();

        for (String db: dbFileNames) {
            dbPath = absoluteFilePath.concat(db);
            DBAnalyzer dba = new DBAnalyzer(dbPath);
            System.out.print(db + ": ");
            watch.reset();
            watch.start();
            dba.analyze();
            watch.stop();
            System.out.println(watch.getTime() + " | ");
        }
    }
}
