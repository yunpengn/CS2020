package coding_quiz;

import org.junit.Assert;
import org.junit.Test;

public class RotatedSearchTest {

    @Test
    public void testSingleElementArray() {
        Integer[] integers = {1};
        Integer i = RotatedSearch.searchMax(integers);
        Assert.assertEquals(i.intValue(), 1);
    }
	
    @Test
    public void testEvenIncreasingIntegerArray() {
        Integer[] integers = {1, 2, 3, 4};
        Integer i = RotatedSearch.searchMax(integers);
        Assert.assertEquals(i.intValue(), 4);
    }

    @Test
    public void testOddIncreasingIntegerArray() {
        Integer[] integers = {1, 2, 3, 4, 5};
        Integer i = RotatedSearch.searchMax(integers);
        Assert.assertEquals(i.intValue(), 5);
    }

    @Test
    public void testEvenIncreasingStringArray() {
        String[] strings = {"a", "b", "c", "d", "e", "f"};
        String s = RotatedSearch.searchMax(strings);
        Assert.assertEquals(s, "f");
    }


    @Test
    public void testOddIncreasingStringArray() {
        String[] strings = {"a", "b", "c", "d", "e"};
        String s = RotatedSearch.searchMax(strings);
        Assert.assertEquals(s, "e");
    }

    @Test
    public void testEvenArrayWithEvenRotation() {
        Integer[] integers = {30, 40, 50, 60, 10, 20};
        Integer i = RotatedSearch.searchMax(integers);
        Assert.assertEquals(i.intValue(), 60);
    }

    @Test
    public void testEvenArrayWithOddRotation() {
        Integer[] integers = {40, 50, 60, 10, 20, 30};
        Integer i = RotatedSearch.searchMax(integers);
        Assert.assertEquals(i.intValue(), 60);
    }

    @Test
    public void testOddArrayWithEvenRotation() {
        Integer[] integers = {50, 60, 70, 10, 20, 30, 40};
        Integer i = RotatedSearch.searchMax(integers);
        Assert.assertEquals(i.intValue(), 70);
    }

    @Test
    public void testOddArrayWithOddRotation() {
        Integer[] integers = {40, 50, 60, 70, 10, 20, 30};
        Integer i = RotatedSearch.searchMax(integers);
        Assert.assertEquals(i.intValue(), 70);
    }

    @Test
    public void testLargeArrayWithRotation() {
        Integer[] integers = new Integer[10000];
        for (int i=0; i < 10000; i += 1) {
            if (i >= 3000) {
            	integers[i - 3000] = i;
            }
            else {
            	integers[i + 7000] = i;
            }
        }
        Integer i = RotatedSearch.searchMax(integers);
        Assert.assertEquals(i.intValue(), 9999);
    }
    
    @Test
    public void testEmptyArray() {
        Integer[] integers = {};
        Integer i = RotatedSearch.searchMax(integers);
        Assert.assertNull(i);
    }
    
    @Test
    public void testNullPointer() {
        Integer i = RotatedSearch.searchMax(null);
        Assert.assertNull(i);
    }
}