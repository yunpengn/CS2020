package tutorial;

abstract class Module {
	public String moduleCode;
	public String moduleName;
	public int moduleQuota;

	public abstract void bid();
}

class Test extends Module {
	public Test(String code, String name, int quota) {
		moduleCode = code;
		moduleName = name;
		moduleQuota = quota;
	}

	public void bid() {
		moduleQuota--;
	}
}

public class VeryAbstract {
	public static void main(String[] args) {
		Test me = new Test("CS1101S", "Programming Methodology", 115);

		for (int i = 0; i < 10; i++) {
			me.bid();
		}

		System.out.println("The remaining quota is " + me.moduleQuota + ".");
		System.out.println(me instanceof Test);
		System.out.println(me instanceof Module);
	}
}