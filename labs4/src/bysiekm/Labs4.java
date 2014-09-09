package bysiekm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Labs4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InputStream[] is =
				{new FileInputStream("input1.txt"), new FileInputStream("input2.txt")};
			MergingStream r = new MergingStream( is );
	        int c;
	        while( (c = r.read()) != -1)
	            System.out.print((char)c);
		} catch (FileNotFoundException e) {
			System.out.println("File(s) are not present.");
		} catch (IOException e) {
			System.out.println("There was an error while reading.");
		}
		System.out.println("\n\nExtra task:\n");
		
		try {
			InputStream[] is =
				{new FileInputStream("input1.txt"), new FileInputStream("input2.txt")};
			MergeSortingStream r = new MergeSortingStream( is );
	        int c;
	        while( (c = r.read()) != -1)
	            System.out.print((char)c);
		} catch (FileNotFoundException e) {
			System.out.println("File(s) are not present.");
		} catch (IOException e) {
			System.out.println("There was an error while reading.");
		}
	}

}
