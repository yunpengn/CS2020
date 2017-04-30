package sg.edu.nus.cs2020;

import java.util.LinkedList;

public interface IWoot {
	int computeWoot(WootLinkedList<Integer> wootList);
	WootLinkedList<Integer> processWOOT(LinkedList<Integer> originalList, int k);
}
