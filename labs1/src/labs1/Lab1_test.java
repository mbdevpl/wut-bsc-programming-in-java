package labs1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author mb
 */
public class Lab1_test {
	private static final int N = 10;
	private static Random r = new Random();

	private static class struktura {
		public int k = 0, x = 0, y = 0;

		public void print() {
			System.out.print("[" + k + " " + x + " " + y + "]");
		}

		public struktura(int n) {
			k = n;
			x = r.nextInt()%50+50;
			y = r.nextInt()%50+50;
		}
	}

	static class CompareByK implements Comparator<struktura> {
		public int compare (struktura s1, struktura s2) {
			if (s1.k > s2.k) return 1;
			if (s1.k < s2.k) return -1;
			return 0;
		}
	}

	static class CompareByX implements Comparator<struktura> {
		public int compare (struktura s1, struktura s2) {
			if (s1.x > s2.x) return 1;
			if (s1.x < s2.x) return -1;
			return 0;
		}
	}

	static class CompareByY implements Comparator<struktura> {
		public int compare (struktura s1, struktura s2) {
			if (s1.y > s2.y) return 1;
			if (s1.y < s2.y) return -1;
			return 0;
		}
	}

	private static void print_struktura(struktura[] s) {
		for (struktura x: s) {
			x.print();
			System.out.print(" ");
		}
		System.out.println();
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

		struktura[] t = new struktura[N];
		for(int i=0; i<N; i++)
			t[i] = new struktura(i);
		Arrays.sort(t, new CompareByX());
		print_struktura(t);
		Arrays.sort(t, new CompareByY());
		print_struktura(t);
		Arrays.sort(t, new CompareByK());
		print_struktura(t);
    }

	private Lab1_test () {
	}
}
