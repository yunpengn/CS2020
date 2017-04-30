package sg.edu.nus.cs2020;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrecisionTest {

	@Test
	public void samplePrecisionTest1() {
		Precision p = new Precision();
		assertTrue("0.2".equals(p.calculateInverse(5)));
	}
	
	@Test
	public void samplePrecisionTest2() {
		Precision p = new Precision();
		assertTrue("0.[142857]".equals(p.calculateInverse(7)));
	}

	
	@Test
	public void samplePrecisionTest3() {
		Precision p = new Precision();
		assertTrue("0.08[3]".equals(p.calculateInverse(12)));		
	}
	
	@Test
	public void PrecisionTestAll() {
		Precision p = new Precision();
		String[] allPrecisions = {
				"1.0", "0.5", "0.[3]", "0.25", "0.2", "0.1[6]", "0.[142857]", "0.125", "0.[1]", "0.1", "0.[09]",
				"0.08[3]", "0.[076923]", "0.0[714285]", "0.0[6]", "0.0625", "0.[0588235294117647]", "0.0[5]",
				"0.[052631578947368421]", "0.05", "0.[047619]", "0.0[45]", "0.[0434782608695652173913]", "0.041[6]",
				"0.04", "0.0[384615]", "0.[037]", "0.03[571428]", "0.[0344827586206896551724137931]", "0.0[3]",
				"0.[032258064516129]", "0.03125", "0.[03]", "0.0[2941176470588235]", "0.0[285714]", "0.02[7]",
				"0.[027]", "0.0[263157894736842105]", "0.[025641]", "0.025", "0.[02439]", "0.0[238095]",
				"0.[023255813953488372093]", "0.02[27]", "0.0[2]", "0.0[2173913043478260869565]",
				"0.[0212765957446808510638297872340425531914893617]", "0.0208[3]",
				"0.[020408163265306122448979591836734693877551]", "0.02", "0.[0196078431372549]", "0.01[923076]",
				"0.[0188679245283]", "0.0[185]", "0.0[18]", "0.017[857142]", "0.[017543859649122807]",
				"0.0[1724137931034482758620689655]", "0.[0169491525423728813559322033898305084745762711864406779661]",
				"0.01[6]", "0.[016393442622950819672131147540983606557377049180327868852459]", "0.0[161290322580645]",
				"0.[015873]", "0.015625", "0.0[153846]", "0.0[15]", "0.[014925373134328358208955223880597]",
				"0.01[4705882352941176]", "0.[0144927536231884057971]", "0.0[142857]", "0.[01408450704225352112676056338028169]",
				"0.013[8]", "0.[01369863]", "0.0[135]", "0.01[3]", "0.01[315789473684210526]", "0.[012987]", "0.0[128205]",
				"0.[0126582278481]", "0.0125", "0.[012345679]", "0.0[12195]",
				"0.[01204819277108433734939759036144578313253]", "0.01[190476]", "0.0[1176470588235294]",
				"0.0[116279069767441860465]", "0.[0114942528735632183908045977]", "0.011[36]",
				"0.[01123595505617977528089887640449438202247191]", "0.0[1]", "0.[010989]",
				"0.01[0869565217391304347826]", "0.[010752688172043]",
				"0.0[1063829787234042553191489361702127659574468085]", "0.0[105263157894736842]", "0.01041[6]",
				"0.[010309278350515463917525773195876288659793814432989690721649484536082474226804123711340206185567]",
				"0.0[102040816326530612244897959183673469387755]", "0.[01]", "0.01"
		};
		for (int i = 1; i <= 100; i++) {
			assertEquals(allPrecisions[i - 1], p.calculateInverse(i));
		}
	}

}
