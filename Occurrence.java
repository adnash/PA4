

/*
 *  hold two elements: a docName which is a String and termFrequency which is an int. termFrequency is the number of times that a particular 
 *  term appears in the document with docName. 
 *  These objects will become associated with Term objects (described below). Its interface includes at least the following methods:
	public Occurrence(String name) which stores the docName and initializes the termFrequency to 1.
	 public void incFrequency() which increments termFrequency by 1.
 * 
 * @author Jeremy Aldrich 
 * 
 */
public class Occurrence {	
	
	private String docName;
	private int termFrequency = 0;
	
	
	
	public Occurrence(String docName) {
		setDocName(docName);
		incFrequency();
		
		
	}
	
	
	
	/**
	 * @param docName the docName to set
	 */
	private void setDocName(String docName) {
		this.docName = docName;
	}
	/**
	 * @param termFrequency the termFrequency to set
	 */
	private void setTermFrequency(int termFrequency) {
		this.termFrequency = termFrequency;
	}
	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}
	/**
	 * @return the termFrequency
	 */
	public int getTermFrequency() {
		return termFrequency;
	}
	
	
	/*
	 * Increments termFrequency by 1
	 */
	
	 public void incFrequency() {
		 int freq = getTermFrequency()+1;
		 
		 setTermFrequency(freq);
		 
		 
	 }
	
	
	 public String toString() {
		 String result = "Doc Name: "+getDocName()+"\n Term Frequency: "+getTermFrequency()+"\n";
		 
		return result; 
	 }
	
	
	

}
