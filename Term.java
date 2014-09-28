import java.util.LinkedList;

/*
 * Term will record the word in lowercase (name), the count of the number of documents in which the word appears (docFrequency), 
 * a total count of frequency in which the term appears in all documents (totalFrequency) 
 * and a Linked List (either your own or the built-in class in Java) of Occurrences (one for each document in which the term appears). 
 * 
 * 
 * @author Jeremy Aldrich
 * 
 */


public class Term {
	
	private String name;
	private int totalFrequency = 0;
	private LinkedList<Occurrence> terms = new LinkedList<Occurrence>();
	
	public Term(String name) {
		setName(name);
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the totalFrequency
	 */
	public int getTotalFrequency() {
		return totalFrequency;
	}

	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * @param docFrequency the totalFrequency to set
	 */
	private void setTotalFrequency(int docFrequency) {
		this.totalFrequency = docFrequency;
	}
	
	/*
	 * If new DocName is found it adds an object for said document
	 * If document exists, increases the frequency within that object
	 */	
	 public void incFrequency(String docName) {
		 docName = docName.toLowerCase();
		 //increments the total frequency;
		 setTotalFrequency(getTotalFrequency()+1);
		 // Searches if document of that name exists -1 if no match index of the document is if exists 
		 int index = findIndex(docName);
		 
		//if document doesn't exist might as well add it.
		 if(index == -1) {			 
			 Occurrence occurrence = new  Occurrence(docName);				 
			 terms.add(occurrence);	
		//if document exists increase the frequency counter within the object 	 
		 }else {
			 
			 terms.get(index).incFrequency();
			 
		 }
		 
	 }
	 public String toString() {		 
		
		return name+" with: "+terms.toString()+" of size: "+getTotalFrequency(); 
		
	 }
	 
	 public int getOccurrSize() {
		 
		 return terms.size();
	 }
	 
	 /*
	  * Searches the linked list terms for a matching document name
	  * If it find a match it returns a 0
	  * If no match is found it returns a -1
	  * @returns index or -1 if no match is found
	  */
	 private int findIndex(String docName) {
		 
			for(int i =0; i< terms.size();i++)
			{
				if(terms.get(i).getDocName().compareTo(docName) == 0) {
					return i;
				}
			}			 
			 return -1;
		 }
	  
	 public static void main(String[] args) {
		 
		 Term t = new Term("Test");
		 
		 t.incFrequency("Alan");
		 t.incFrequency("Jeremy");
		 t.incFrequency("Jeremy");
		 t.incFrequency("Jeremy");
		 //t.incFrequency("Alan");
		 //t.incFrequency("Jer");
		 System.out.println(t);
		 
		 
	 }
	 
	
	 
	

}
