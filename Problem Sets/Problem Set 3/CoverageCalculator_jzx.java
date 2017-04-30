package sg.edu.nus.cs2020;

import java.util.ArrayList;
import java.util.Comparator;

public class CoverageCalculator {
	//array of tower
	public ArrayList<Tower> towers = new ArrayList<Tower>();
	private int highwayLength;
	//comparator for towers
	static class TowerComparator implements Comparator<Tower> {

		@Override
		public int compare(Tower o1, Tower o2) {
			return o1.compareTo(o2);
		}
		
	}
	
	public CoverageCalculator(int highwayLength) {
		this.highwayLength = highwayLength;
	}
	
	public void addTower(int location, int range) {
		if(location > highwayLength) {
			// throw new InvalidLocationException();
			return;
		}
		towers.add(new Tower(location, range));
	}
	
	
	public int getCoverage(){
		
		//if no element return 0
		if(towers.size() > 0) {
			//sort the tower by locations 
			towers.sort(new TowerComparator());
			int distance;
			int startPoint = towers.get(0).getStart();
			
			//startpoint cannot be less than 0
			if(startPoint < 0) {
				startPoint = 0;
			}
			
			int endPoint = towers.get(0).getEnd();
			int nextEnd;
			//store the the amount of space
			int empty = 0;
			
			
			for(int i = 0; i < towers.size() - 1; i++) {
							
				//compute the space between the covered area if any
				distance = towers.get(i + 1).getStart() - endPoint;
				if(distance > 0) {
					empty += distance;
				}
				
				//update the endpoint
				nextEnd = towers.get(i + 1).getEnd();
				if(nextEnd > endPoint) {
					endPoint = nextEnd; 
				}
	
			}
			
			//if endpoint exceeds the highway length, the total length should be highwayLeng - start
			if(endPoint > highwayLength) {
				return highwayLength - towers.get(0).getStart() -empty;
			}
			return endPoint - startPoint - empty;
		} else {
			return 0;
		}
	}
	
}
