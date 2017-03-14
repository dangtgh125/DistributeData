package Function;

public class Distribute {
	public double _numLetterOfFile[];
	public double _numLetterOfData;
	public double _distribution[];
	
	public Distribute() {
		_numLetterOfFile = null;
		_numLetterOfData = 0;
		_distribution    = null;
	}
	
	public void setNumLetterOfFile(int index, double numLetter){
		this._numLetterOfFile[index] = numLetter;
	}
	
	public void setNumLetterOfData(double numLetter){
		this._numLetterOfData = numLetter;
	}
	
	public void memoryAllocation(int numFile){
		this._numLetterOfFile = new double[numFile];
		this._distribution    = new double[numFile];
		
		for(int i = 0; i < numFile; i++){
			this._numLetterOfFile[i] = 0;
		}
	}
	
	public void computeProbability(int index){
		this._distribution[index] = this._numLetterOfFile[index] / this._numLetterOfData;
	}
}
