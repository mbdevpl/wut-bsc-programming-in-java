package bysiekm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Labs4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintWriter[] pw = { new PrintWriter(System.out), new PrintWriter(System.err) };
		Multistream stream = new Multistream(pw);
	        try {
				stream.addStream(new FileOutputStream("test.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			stream.write(3, "errorSTR");
			stream.write(0, "normal output");
	        //stream.write( "Hello world!" );
	        //stream.write("\n");
        stream.close();
	}

}
