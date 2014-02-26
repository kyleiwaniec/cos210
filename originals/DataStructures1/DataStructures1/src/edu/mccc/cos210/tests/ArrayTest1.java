package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.Utility;
public class ArrayTest1 {
	public static void main(String[] sa) throws Exception {
		new ArrayTest1().doIt();
	}
	private void doIt() throws Exception {
		Array<String> as = Utility.getArray(10);
		System.out.println(as);
		Array<A> aa = Utility.getArray(3);
		System.out.println(aa);
		Array<B> ab = Utility.getArray(3);
		System.out.println(ab);
		Array<C> ac = Utility.getArray(3);
		System.out.println(ac);
		aa.set(0, new A());
		aa.set(1, new B());
		aa.set(2, new C());
		ab.set(1, new B());
		ab.set(2, new C());
		ac.set(2, new C());
		System.out.println(aa);
		System.out.println(ab);
		System.out.println(ac);
		Array<Integer> ai = Utility.getArray(3);
		System.out.println(ai);
		Array<Array<Integer>> aia = Utility.getArray(3);
		System.out.println(aia);
		aia.set(2, Utility.<Integer>getArray(10));
	}
	private class A {
		@Override
		public String toString() {
			return "A";
		}
	}
	private class B extends A {
		@Override
		public String toString() {
			return "B";
		}
	}
	private class C extends B {
		@Override
		public String toString() {
			return "C";
		}
	}
}
