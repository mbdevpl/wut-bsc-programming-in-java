package labs1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author Mateusz Bysiek &copy; 2010, mb@mbdev.pl, http://mbdev.pl/
 */
public class Task1 {
	private static final int N = 10;
	private static final int SIMPLE = 0;
	private static final int ADVANCED = 1;
	
	static class RowCompare implements Comparator<int[]> {
		int index = -1;
		public int compare (int i1[], int i2[]) {
			return (new Integer(i1[index])).compareTo(i2[index]);
		}
		public RowCompare(int byWhichIndex) {
			index = byWhichIndex;
		}
	}

	static int[][] arrInit(int size) {
		int[][] arr = new int[size][size];
		Random r = new Random();
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				arr[i][j] = r.nextInt(10);
		return arr;
	}

	static void arrPrint(int[][] arr) {
		for(int[] row: arr) {
			for(int i: row)
				System.out.print(i + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	static void arrSortRows(int[][] arr) {
		for(int i = 0; i < arr.length; i++)
			Arrays.sort(arr[i]);
	}

	static void arrSort(int[][] arr, int mode) {
		if (mode == SIMPLE) {
			Arrays.sort(arr, new RowCompare(0));
		} else {
			for (int i = arr.length-1; i >= 0; i--) {
				//System.out.println("i=" + i);
				Arrays.sort(arr, new RowCompare(i));
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Main task:");
		int[][] arr = arrInit(N);
		arrPrint(arr);
		arrSortRows(arr);
		arrPrint(arr);
		arrSort(arr,SIMPLE);
		arrPrint(arr);
		
		System.out.println("Extra task:");
		int[][] arr2 = arrInit(N);
		arrPrint(arr2);
		arrSortRows(arr2);
		arrPrint(arr2);
		//System.out.println("Sorting:");
		arrSort(arr2,ADVANCED);
		arrPrint(arr2);
	}

	private Task1 () {
	}
}
