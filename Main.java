package Function;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagementFile mngFile = new ManagementFile();
		mngFile.listFilesForFolder("F:\\CNTT\\Nam_4\\HK1\\ANMT\\Lab05\\132134\\src\\CryptoGUI\\");
		System.out.println(mngFile.getFile(0));
	}

}
