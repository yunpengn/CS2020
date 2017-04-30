package sg.edu.nus.cs2020;

import java.util.Iterator;
import java.util.LinkedList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

public class Woot implements IWoot {
	public int computeWoot(WootLinkedList<Integer> wootList) {
		Iterator<Integer> iter = wootList.iterator();
		int result = 0;
		int sign = 1;
		
		while(iter.hasNext()) {
			result += (iter.next() * sign);
			sign = - sign;
		}
		
		return result;
	}
	
	public WootLinkedList<Integer> processWOOT(LinkedList<Integer> originalList, int k) {
		WootLinkedList<Integer> woot = new WootLinkedList<Integer>();
		
		for (Integer x : originalList) {
			if(Math.abs(x) <= k) {
				woot.suspend(x);
			}
		}

		return woot;
	}

}
