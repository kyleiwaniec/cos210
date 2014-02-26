public class HomeWork4j {
	private double[][] matrix1by1 = { { 0.0 } };
	private double[][] matrix1by3 = { { 0.0, 1.0, 2.0 } };
	private double[][] matrix1by4 = { { 0.0, 1.0, 2.0, 3.0 } };
	private double[][] matrix3by1 = { { 0.0 }, { 1.0 }, { 2.0 } };
	private double[][] matrix3by3 = { { 0.0, 1.0, 2.0 }, { 3.0, 4.0, 5.0 }, { 6.0, 7.0, 8.0 } };
	private double[][] matrix3by4 = { { 0.0, 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0, 7.0 }, { 8.0, 9.0, 10.0, 11.0 } };
	private double[][] matrix4by1 = { { 0.0 }, { 1.0 }, { 2.0 }, { 3.0 } };
	private double[][] matrix4by3 = { { 0.0, 1.0, 2.0 }, { 3.0, 4.0, 5.0 }, { 6.0, 7.0, 8.0 }, { 9.0, 10.0, 11.0 } };
	private double[][] matrix4by4 = { { 0.0, 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0, 7.0 }, { 8.0, 9.0, 10.0, 11.0 }, { 12.0, 13.0, 14.0, 15.0 } };
	private double[][] matrixRagged = new double[3][];
	{
		matrixRagged[0] = new double[3];
		matrixRagged[1] = new double[2];
		matrixRagged[2] = new double[3];
	}
	public static void main(String[] sa) {
		new HomeWork4j().doIt();
	}
	private void doIt() {
		MatrixOps mo;
		double[][] daa;
		double[] da;
		double d;
		int n;
// Test: public MatrixOps();
		mo = new MatrixOps();
		System.out.println("(0)" + "\n" + mo);
// Test: public MatrixOps(int numRows);
//  numRows = 1
		mo = new MatrixOps(1);
		System.out.println("(1)" + "\n" + mo);
//  numRows = 4
		mo = new MatrixOps(4);
		System.out.println("(2)" + "\n" + mo);
//  numRows = 0
		try {
			mo = new MatrixOps(0);
		} catch (IllegalArgumentException ex) {
			System.out.println("(3)" + "\n" + "ok");
		}
//  numRows = -1
		try {
			mo = new MatrixOps(-1);
		} catch (IllegalArgumentException ex) {
			System.out.println("(4)" + "\n" + "ok");
		}
// Test: public MatrixOps(int numRows, int numCols);
//  numRows = 1, numCols = 1
		mo = new MatrixOps(1, 1);
		System.out.println("(5)" + "\n" + mo);

//  numRows = 4, numCols = 3
		mo = new MatrixOps(4, 3);
		System.out.println("(6)" + "\n" + mo);
//  numRows = 0, numCols = 3
		try {
			mo = new MatrixOps(0, 3);
		} catch (IllegalArgumentException ex) {
			System.out.println("(7)" + "\n" + "ok");
		}
//  numRows = 4, numCols = 0
		try {
			mo = new MatrixOps(4, 0);
		} catch (IllegalArgumentException ex) {
			System.out.println("(8)" + "\n" + "ok");
		}
//  numRows = -1, numCols = -1;
		try {
			mo = new MatrixOps(-1, -1);
		} catch (IllegalArgumentException ex) {
			System.out.println("(9)" + "\n" + "ok");
		}
// Test: public MatrixOps(double[][] mat);
//  valid matrix
		mo = new MatrixOps(matrix1by4);
		System.out.println("(10)" + "\n" + mo);
//  null matrix
		try {
			mo = new MatrixOps(null);
		} catch (IllegalArgumentException ex) {
			System.out.println("(11)" + "\n" + "ok");
		}
//  ragged array
		try {
			mo = new MatrixOps(matrixRagged);
		} catch (IllegalArgumentException ex) {
			System.out.println("(12)" + "\n" + "ok");
		}
// Test: public void setMatrix(double[][] mat);
//  valid matrix
			mo = new MatrixOps();
			mo.setMatrix(matrix3by3);
//  null matrix
		try {
			mo = new MatrixOps();
			mo.setMatrix(null);
		} catch (IllegalArgumentException ex) {
			System.out.println("(13)" + "\n" + "ok");
		}
//  ragged array
		try {
			mo = new MatrixOps();
			mo.setMatrix(matrixRagged);
		} catch (IllegalArgumentException ex) {
			System.out.println("(14)" + "\n" + "ok");
		}
// Test: public double[][] getMatrix();
		mo = new MatrixOps(matrix3by3);
		daa = mo.getMatrix();
		System.out.println("(15)");
		for (int i = 0; i < mo.getNumRows(); i++) {
			System.out.print(java.util.Arrays.toString(daa[i]));
		}
		System.out.println();
// Test: public void setRow(int row, double[] rowVals);
//  valid
		da = new double[] { 3.0, 2.0, 1.0 };
		mo = new MatrixOps(2, 3);
		mo.setRow(1, da);
		System.out.println("(16)" + "\n" + mo);
//  row == 0
		da = new double[] { 3.0, 2.0, 1.0 };
		mo = new MatrixOps(2, 3);
		mo.setRow(0, da);
		System.out.println("17" + mo);
//  row == matrix height
		try {
			da = new double[] { 3.0, 2.0, 1.0 };
			mo = new MatrixOps(2, 3);
			mo.setRow(mo.getNumRows(), da);
		} catch (IllegalArgumentException ex) {
			System.out.println("(18)" + "\n" + "ok");
		}
//  rowVals == null
		try {
			mo = new MatrixOps(2, 3);
			mo.setRow(mo.getNumRows(), null);
		} catch (IllegalArgumentException ex) {
			System.out.println("(19)" + "\n" + "ok");
		}
//  rowVals length != matrix width
		try {
			da = new double[] { 2.0, 1.0 };
			mo = new MatrixOps(2, 3);
			mo.setRow(mo.getNumRows(), da);
		} catch (IllegalArgumentException ex) {
			System.out.println("(20)" + "\n" + "ok");
		}
// Test: public double[] getRow(int row);
//  valid
		mo = new MatrixOps(matrix4by4);
		da = mo.getRow(2);
		System.out.println("(21)" + "\n" + java.util.Arrays.toString(da));
//  row == 0
		mo = new MatrixOps(matrix4by4);
		da = mo.getRow(0);
		System.out.println("(22)" + "\n" + java.util.Arrays.toString(da));
//  row == matrix height
		try {
			mo = new MatrixOps(matrix4by4);
			da = mo.getRow(mo.getNumRows());
		} catch (IllegalArgumentException ex) {
			System.out.println("(23)" + "\n" + "ok");
		}
// Test: public void setElement(int row, int col, double value);
//  valid
		mo = new MatrixOps(matrix4by4);
		mo.setElement(2, 2, 10.0);
		System.out.println("(24)" + "\n" + mo);
//  row == 0
		mo = new MatrixOps(matrix4by4);
		mo.setElement(0, 2, 10.0);
		System.out.println("(25)" + "\n" + mo);
//  row < 0
		try {
			mo = new MatrixOps(matrix4by4);
			mo.setElement(-1, 2, 10.0);
		} catch (IllegalArgumentException ex) {
			System.out.println("(26)" + "\n" + "ok");
		}
//  row == matrix height
		try {
			mo = new MatrixOps(matrix4by4);
			mo.setElement(mo.getNumRows(), 2, 10.0);
		} catch (IllegalArgumentException ex) {
			System.out.println("(27)" + "\n" + "ok");
		}
//  col == 0
		mo = new MatrixOps(matrix4by4);
		mo.setElement(2, 0, 10.0);
		System.out.println("(28)" + "\n" + mo);
//  col < 0
		try {
			mo = new MatrixOps(matrix4by4);
			mo.setElement(2, -1, 10.0);
		} catch (IllegalArgumentException ex) {
			System.out.println("(29)" + "\n" + "ok");
		}
//  col == matrix width
		try {
			mo = new MatrixOps(matrix4by4);
			mo.setElement(2, mo.getNumCols(), 10.0);
		} catch (IllegalArgumentException ex) {
			System.out.println("(30)" + "\n" + "ok");
		}
// Test: public double getElement(int row, int col);
//  valid
		mo = new MatrixOps(matrix4by4);
		d = mo.getElement(2, 2);
		System.out.println("(31)" + "\n" + d);
//  row == 0
		mo = new MatrixOps(matrix4by4);
		d = mo.getElement(0, 2);
		System.out.println("(32)" + "\n" + d);
//  row < 0
		try {
			mo = new MatrixOps(matrix4by4);
			d = mo.getElement(-1, 2);
		} catch (Exception ex) {
			System.out.println("(33)" + "\n" + "ok");
		}
//  row == matrix height
		try {
			mo = new MatrixOps(matrix4by4);
			d = mo.getElement(mo.getNumRows(), 2);
		} catch (Exception ex) {
			System.out.println("(34)" + "\n" + "ok");
		}
//  col == 0
		mo = new MatrixOps(matrix4by4);
		d = mo.getElement(2, 0);
		System.out.println("(35)" + "\n" + d);
//  col < 0
		try {
			mo = new MatrixOps(matrix4by4);
			d = mo.getElement(2, -1);
		} catch (Exception ex) {
			System.out.println("(36)" + "\n" + "ok");
		}
//  col == matrix width
		try {
			mo = new MatrixOps(matrix4by4);
			d = mo.getElement(2, mo.getNumCols());
		} catch (Exception ex) {
			System.out.println("(37)" + "\n" + "ok");
		}
// Test: public double sum();
		mo = mo = new MatrixOps(matrix4by4);
		d = mo.sum();
		System.out.println("(38)" + "\n" + d);
// Test: public double findMax();
		mo = mo = new MatrixOps(matrix4by4);
		d = mo.findMax();
		System.out.println("(39)" + "\n" + d);
// Test: public double findMin();
		mo = mo = new MatrixOps(matrix4by4);
		d = mo.findMin();
		System.out.println("(40)" + "\n" + d);
// Test: public double[][] transpose();
//  4 by 3
		mo = new MatrixOps(matrix4by3);
		System.out.println("(41)" + "\n" + mo);
		daa = mo.transpose();
		System.out.println("(42)" + "\n" + new MatrixOps(daa));
//  3 by 4
		mo = new MatrixOps(matrix3by4);
		System.out.println("(43)" + "\n" + mo);
		daa = mo.transpose();
		System.out.println("(44)" + "\n" + new MatrixOps(daa));
//  3 by 3
		mo = new MatrixOps(matrix3by3);
		System.out.println("(45)" + "\n" + mo);
		daa = mo.transpose();
		System.out.println("(46)" + "\n" + new MatrixOps(daa));
//  1 by 3
		mo = new MatrixOps(matrix1by3);
		System.out.println("(47)" + "\n" + mo);
		daa = mo.transpose();
		System.out.println("(48)" + "\n" + new MatrixOps(daa));
//  4 by 1
		mo = new MatrixOps(matrix4by1);
		System.out.println("(49)" + "\n" + mo);
		daa = mo.transpose();
		System.out.println("(50)" + "\n" + new MatrixOps(daa));
//  1 by 1
		mo = new MatrixOps(matrix1by1);
		System.out.println("(51)" + "\n" + mo);
		daa = mo.transpose();
		System.out.println("(52)" + "\n" + new MatrixOps(daa));
// Test: public double[][] multiply(double[][] mat2);
//  4 by 3 X 3 by 4
		mo = new MatrixOps(matrix4by3);
		System.out.println("(53)" + "\n" + mo);
		daa = mo.multiply(matrix3by4);
		System.out.println("(54)" + "\n" + new MatrixOps(daa));
//  3 by 4 X 4 by 3
		mo = new MatrixOps(matrix3by4);
		System.out.println("(55)" + "\n" + mo);
		daa = mo.multiply(matrix4by3);
		System.out.println("(56)" + "\n" + new MatrixOps(daa));
//  3 by 3 X 3 by 3
		mo = new MatrixOps(matrix3by3);
		System.out.println("(57)" + "\n" + mo);
		daa = mo.multiply(matrix3by3);
		System.out.println("58" + new MatrixOps(daa));
//  1 by 3 X 3 by 1
		mo = new MatrixOps(matrix1by3);
		System.out.println("(59)" + "\n" + mo);
		daa = mo.multiply(matrix3by1);
		System.out.println("(60)" + "\n" + new MatrixOps(daa));
//  4 by 1 X 1 by 3
		mo = new MatrixOps(matrix4by1);
		System.out.println("(61)" + "\n" + mo);
		daa = mo.multiply(matrix1by3);
		System.out.println("(62)" + "\n" + new MatrixOps(daa));
//  1 by 1 X 1 by 1
		mo = new MatrixOps(matrix1by1);
		System.out.println("(63)" + "\n" + mo);
		daa = mo.multiply(matrix1by1);
		System.out.println("(64)" + "\n" + new MatrixOps(daa));
//  4 by 3 X 4 by 3
		try {
			mo = new MatrixOps(matrix4by3);
			daa = mo.multiply(matrix4by3);
		} catch (Exception ex) {
			System.out.println("(65)" + "\n" + "ok");
		}
//  mat2 == null
		try {
			mo = new MatrixOps(matrix4by3);
			daa = mo.multiply(null);
		} catch (Exception ex) {
			System.out.println("(66)" + "\n" + "ok");
		}
// ragged array
		try {
			mo = new MatrixOps(matrix4by3);
			daa = mo.multiply(matrixRagged);
		} catch (Exception ex) {
			System.out.println("(67)" + "\n" + "ok");
		}
// Test: public int getNumRows();
		mo = new MatrixOps(matrix3by4);
		n = mo.getNumRows();
		System.out.println("(68)" + "\n" + n);
// Test: public int getNumCols();
		mo = new MatrixOps(matrix3by4);
		n = mo.getNumCols();
		System.out.println("(69)" + "\n" + n);
// Test: public String toString();
		mo = new MatrixOps(matrix3by4);
		System.out.println("(70)" + "\n" + mo);
	}
}
