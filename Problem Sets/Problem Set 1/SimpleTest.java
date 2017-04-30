package sg.edu.nus.cs2020;

public class SimpleTest {
	
	public static void main(String[] args){
		
		VectorTextFile file = new VectorTextFile("src/sg/edu/nus/cs2020/texts/hamlet.txt");
		
		double angle = VectorTextFile.Angle(file, file);
		assert(angle == 0);
		
		System.out.println("Norm: " + file.Norm());
	}
}
