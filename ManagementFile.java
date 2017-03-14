package Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ManagementFile {
	private List <String> _listFile;
	public InputStream	  _inputStream;
	public OutputStream	  _outputStream;
	public Distribute 	  _dis;
	private boolean		  _flag;
	
	public ManagementFile() {
		_listFile 	  = null;
		_inputStream  = null;
		_outputStream = null;
		_dis 		  = null;
		_flag		  = false;
	}
	
	public String getFile(int seq) {
		return this._listFile.get(seq);
	}
	
	public String[] splitString(String strLine){
		String[] path = strLine.split(" /-,.?\\()[]");
		return path;
	}
	
	public void listFilesForFolder(String pathFolder) {
		
		File folder = new File(pathFolder);
		this._listFile = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry.getPath());
	        } else {
	            System.out.println(fileEntry.getName());
	            this._listFile.add(fileEntry.getPath());
	        }
	    }
	}
	
	public void processString(String strLine, int index){
		String[] path = this.splitString(strLine);
		int size = path.length;

		for(int i = 0; i < size; i++){
			//compare strings with array name vietnam
			/*
			 * for (vòng lặp duyệt array họ và tên)
			 * 	if (nó là họ việt nam)
			 * 		_numLetterOfFile[i]++;
			 * 		_numLetterOfData++;
			 */
		}
	}
	
	public void ReadFile() throws IOException {
		
		int size = this._listFile.size();
		String temp = null;
		InputStreamReader isr;
		BufferedReader br;
		
		// Process with the line is read
		for(int i = 0; i < size; i++) {
			this._inputStream = new FileInputStream(this._listFile.get(i));
		    isr = new InputStreamReader(this._inputStream, Charset.forName("UTF-8"));
		    br = new BufferedReader(isr);
		    while ((temp = br.readLine()) != null) {
		        // Deal with the line by class Distribute
		    	this.processString(temp, i);
		    	
		    	if(this._flag == true){
		    		this._dis.computeProbability(i);
		    	}
		    }
		    isr.close();
		    br.close();
			this._inputStream.close();
		}
	}
	
	public void processReadFile() throws IOException{
		this._dis.memoryAllocation(this._listFile.size());
		
		// process with false flag to count number letters in the database
		this.ReadFile();
		this._flag = true;
		
		// process with true flag to count number letters in the file
		this.ReadFile();
	}
}
