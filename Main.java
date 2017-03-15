package Function;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		ManagementFile mngFile = new ManagementFile();
		mngFile.listFilesForFolder("F:\\CNTT\\Luan_Van\\DataName\\");
		
		try {
			mngFile.processReadFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*String a = "Từ Giang Hải Đằng";
		PrintStream out = new PrintStream(System.out, true, "UTF-8");
		out.println(a);
		*/
	}

}
