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

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ManagementFile {
	private List <String> _listFile;
	public InputStream	  _inputStream;
	public OutputStream	  _outputStream;
	public Distribute 	  _dis;
	
	public ManagementFile() {
		_listFile 	  = null;
		_inputStream  = null;
		_outputStream = null;
		_dis 		  = null;
	}
	
	public String getFile(int seq) {
		return this._listFile.get(seq);
	}
	
	public String[] splitString(String strLine){
		String[] path = strLine.split("[- ,.]");
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
		int numName = this._dis._listNameVN.length;

		for(int i = 0; i < size; i++){
			//compare strings with array name vietnam
			for(int j = 0; j < numName; j++) {
				if(path[i].equals(this._dis._listNameVN[j])){
					this._dis._numLetterOfFile[index]++;
					this._dis._numLetterOfData++;
				}
			}
		}
	}
	
	public void ReadFile() throws IOException {
		
		int size = this._listFile.size();
		String temp = null, path[];
		InputStreamReader isr;
		BufferedReader br;
		
		// Process with the line is read
		for(int i = 0; i < size; i++) {
			path = this.splitString(this._listFile.get(i));
			if(path[path.length - 1].equals("doc") || path[path.length - 1].equals("docx")){
				// process with word file
				POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(this._listFile.get(i)));
				HWPFDocument document = new HWPFDocument(fs);
				
			}
			else {
				// process with text file
				this._inputStream = new FileInputStream(this._listFile.get(i));
			    isr = new InputStreamReader(this._inputStream, Charset.forName("UTF-16"));
			    br = new BufferedReader(isr);
			    while ((temp = br.readLine()) != null) {
			        // Deal with the line by class Distribute
			    	this.processString(temp, i);
			    }
			    isr.close();
			    br.close();
				this._inputStream.close();
			}
		}
	}
	
	public void processReadFile() throws IOException{
		int size = this._listFile.size();
		System.out.println(size);
		this._dis = new Distribute();
		this._dis.memoryAllocation(size);
		this.ReadFile();
		
		for(int i = 0; i < size; i++){
			this._dis.computeProbability(i);
			System.out.println(this._dis._numLetterOfFile[i] + "\n");
			System.out.println(this._dis._distribution[i] + "\n");
			System.out.printf("%s: %.2f\n", this._listFile.get(i), this._dis._distribution[i]);
		}
	}
}
