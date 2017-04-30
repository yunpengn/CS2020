package sg.edu.nus.cs2020;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {

	/**
	 * getRegister returns a shiftregister to test
	 * @param size
	 * @param tap
	 * @return a new shift register
	 * Description: to test a student's code, update this function
	 * to instantiate their shift register
	 */
	ILFShiftRegister getRegister(int size, int tap){
		return new ShiftRegister(size, tap);
	}
	
	/**
	 * Test shift with simple example
	 */
	@Test
	public void testShift1() {
		ILFShiftRegister r = getRegister(9, 7);		
		int[] seed = {0,1,0,1,1,1,1,0,1};
		r.setSeed(seed);
		int[] expected = {1,1,0,0,0,1,1,1,1,0};
		for (int i=0; i<10; i++){
			assertEquals("ShiftTest", expected[i], r.shift());
		}	
	}
	
	/**
	 * Test generate with simple example
	 */
	@Test
	public void testGenerate1() {
		ILFShiftRegister r = getRegister(9, 7);		
		int[] seed = {0,1,0,1,1,1,1,0,1};
		r.setSeed(seed);
		int[] expected = {6,1,7,2,2,1,6,6,2,3};
		for (int i=0; i<10; i++){
			assertEquals("GenerateTest", expected[i], r.generate(3));
		}	
	}
	
	/**
	 * Test generate with very big register example
	 */
	@Test
	public void testLargeShift() {
		ILFShiftRegister r = getRegister(67, 7);		
		int[] seed = {0,1,0,1,1,1,1,0,0,1,0,1,1,1,1,0,0,1,0,1,1,1,1,0,0,1,0,1,1,1,1,0,0,1,0,1,1,1,1,0,0,1,0,1,1,1,1,0,0,1,0,1,1,1,1,0,0,1,0,1,1,1,1,0,1,1,1};
		r.setSeed(seed);
		int[] expected = {1,0,0,1,0,1,0,1,1,1};
		for (int i=0; i<10; i++){
			assertEquals("ShiftTest", expected[i], r.shift());
		}	
		r.setSeed(seed);
		int[] expectedGen = {149,218,149,218,149,218,149,218,136,51};
		for (int i=0; i<10; i++){
			assertEquals("GenTest", expectedGen[i], r.generate(8));
		}
	}
	
	/**
	 * Test generate with long output
	 */
	@Test
	public void testLargeOutput() {
		ILFShiftRegister r = getRegister(13, 5);		
		int[] seed = {1,1,0,0,1,0,0,0,0,1,0,0,1};
		r.setSeed(seed);
		int[] expected = {895,535,696,86,367,474,531,115,692,668,925,941,37,215,997,426,21,83,948,700,401,814,525,927,783,797,571,370,644,276,879,159,618,360,931,589,285,894,267,927,3,958,543,697,70,235,427,777,372,993,109,596,900,568,448,56,905,226,694,574,165,321,690,1017,740,662,830,133,845,561,465,428,636,393,683,620,769,633,850,461,494,92,704,593,351,350,578,869,816,481,548,654,699,228,979,839,414,602,736,849,383,850,705,333,120,267,147,160,922,468,123,441,559,573,23,1021,47,116,205,706,755,615,946,213,75,561,733,271,600,578,105,915,453,495,76,836,544,69,89,791,404,761,456,57,921,358,711,804,418,20,67,560,717,139,553,856,366,710,820,38,101,345,823,920,634,992,881,883,977,233,517,146,956,701,385,682,636,645,520,584,202,187,685,777,632,834,73,415,326,967,772,942,151,619,376,551,572,7,633,94,366,458,919,2,942,923,712,348,492,254,504,189,968,624,835,89,27,311,733,515,763,614,930,337,58,811,986,90,169,907,64,398,210,562,623,947,197,463,576,967,8,781,179,416,954,984,248,913,359,727,672,467,270,324,869,60,322,512,325,121,283,279,209,640,211,302,840,998,532,522,746,1010,599,58,39,889,126,866,329,447,842,836,300,230,125,220,854,774,780,943,135,1007,265,829,827,338,136,407,327,983,640,991,397,876,45,214,1013,46,100,329,691,1001,864,743,548,386,536,192,24,389,97,158,374,79,1014,156,728,980,318,448,820,810,198,381,252,346,901,292,231,109,344,807,540,523,762,630,550,288,288,556,655,683,352,930,605,153,783,17,664,342,335,982,656,603,508,630,298,387,260,487,77,852,932,52,323,528,193,8,1,16,388,113,282,263,341,753,457,41,541,279,989,547,247,741,394,537,208,412,500,379,409,35,702,575,181,197,707,739,995,963,463,332,868,44,198,625,95,382,78,998,280,681,718,57,149,453,739,239,864,491,647,422,467,514,487,833,1015,896,1023,897,1007,517,926,799,665,586,104,899,65,414,342,579,885,692,400,830,905,1006,533,538,878,899,845,317,370,392,439,843,852,168,151,359,475,515,503,709,902,666,760,724,286,972,951,258,910,407,587,884,676,20,847,659,745,832,235,679,938,336,42,687,939,320,430,734,689,71,251,47,888,110,742,312,165,589,529,989,303,84,705,577,219,303,856,610,613,784,1005,679,166,499,14,356,361,191,874,328,431,718,821,54,481,296,557,671,815,273,696,858,460,510,472,689,843,88,11,179,684,793,1020,819,339,152,19,310,717,903,650,892,677,4,715,738,1011,583,446,86,611,377,567,952,118,867,345,59,827,606,43,435,652,277,895,283,539,114,676,792,1004,695,290,386,276,99,60,590,675,353,946,985,232,533,278,973,935,134,1023,141,844,545,85,477,870,142,1022,157,712,592,335,218,563,639,567,180,213,839,658,761,708,154,957,685,5,731,870,898,861,185,259,146,176,542,421,353,190,890,204,478,980,562,355,784,225,516,130,568,716,155,941,809,116,961,609,727,428,368,298,655,423,451,902,406,603,752,725,270,584,966,24,649,194,186,701,653,521,600,334,202,951,526,813,435,384,438,859,720,217,397,96,142,242,62,748,411,909,293,247,489,297,573,795,862,11,959,527,829,55,497,172,604,901,552,68,73,659,485,995,207,364,360,175,750,313,181,969,608,711,40,257,48,904,242,818,591,447,70,999,264,813,703,291,402,144,18,294,841,1014,912,635,1008,757,770,715,494,848,611,629,660,924,957,417,166,767,173,320,674,637,661,908,569,464,444,1016,504,945,875,596,136,667,484,1011,331,285,114,424,955,968,380,992,125,976,1013,802,199,365,376,299,671,35,434,668,145,782,1,796,295,85,721,965,170,53,607,823,148,729,964,186,433,558,557,403,908,309,371,408,51,826,590,431,450,918,18,554,1002,978,91,185,527,49,148,469,871,158,634,236,978,855,26,555,1018,598,42,419,776,356,613,28,846,643,877,817,497,928,767,929,483,646,438,87,627,253,582,674,369,566,936,498,786,67,316,622,175,482,410,145,2,162,824,748,663,814,257,828,811,214,249,141,64,642,113,534,420,369,314,779,470,217,641,195,170,825,764,787,863,27,571,638,551,304,164,605,917,940,53,339,916,176,274,262,325,885,440,307,794,66,300,1002,222,248,157,452,755,363,785,241,896,243,802,971,462,348,736,93,476,886,266,911,391,975,773,958,275,538,98,800,873,758,944,119,883,221,74,545,857,382,834,837,316,354,12,454,593};
		for (int i=0; i<1000; i++){
			assertEquals("LongTest", expected[i], r.generate(10));
		}			
	}
	
	
	
	/**
	 * Test register of length 1
	 */
	@Test
	public void testOneLength() {
		ILFShiftRegister r = getRegister(1, 0);		
		int[] seed = {1};
		r.setSeed(seed);
		int[] expected = {0,0,0,0,0,0,0,0,0,0,};
		for (int i=0; i<10; i++){
			assertEquals("GenerateTest", expected[i], r.generate(3));
		}	
	}
	
	/**
	 * Test with erroneous tap.
	 */
	@Test
	public void testError() {
		try{
			ILFShiftRegister r = getRegister(1, 1);		
			int[] seed = {1};
			r.setSeed(seed);
			r.shift();
			r.generate(4);
		} catch (NullPointerException e){
			assertTrue(false);
		} catch(ArrayIndexOutOfBoundsException  e){
			assertTrue(false);
		} catch (Exception e){
			// Ok if they throw a specific type of exception
		}
	}
	
	/**
	 * Test with negative or bad size.
	 */
	@Test
	public void testError2() {
		try{
			ILFShiftRegister r = getRegister(-7, -9);		
			int[] seed = {1};
			r.setSeed(seed);
			r.shift();
			r.generate(4);
		} catch (NullPointerException e){
			assertTrue(false);
		} catch(ArrayIndexOutOfBoundsException  e){
			assertTrue(false);
		} catch (Exception e){
			// Ok if they throw a specific type of exception
		}
	}
	
	/**
	 * Test with negative or bad size.
	 */
	@Test
	public void testError3() {
		try{
			ILFShiftRegister r = getRegister(2, 1);		
			int[] seed = {1,0,0,0,};
			r.setSeed(seed);
			r.shift();
			r.generate(4);
		} catch (NullPointerException e){
			assertTrue(false);
		} catch(ArrayIndexOutOfBoundsException  e){
			assertTrue(false);
		} catch (Exception e){
			// Ok if they throw a specific type of exception
		}
	}
	
	/**
	 * Test with bad seed containing non zero/one values.
	 */
	@Test
	public void testError4() {
		try{
			ILFShiftRegister r = getRegister(4, 1);		
			int[] seed = {0,2,0,0};
			r.setSeed(seed);
			r.shift();
			r.generate(4);
		} catch (NullPointerException e){
			assertTrue(false);
		} catch(ArrayIndexOutOfBoundsException  e){
			assertTrue(false);
		} catch (Exception e){
			// Ok if they throw a specific type of exception
		}
	}

}
