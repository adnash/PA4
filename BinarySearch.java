import java.util.ArrayList;

/*
 * 
 * 
 * @author Jeremy Aldrich
 */
		
		
public class BinarySearch {			
		
	//private ArrayList<Integer> occurrList = new ArrayList<Integer>();
	
	private ArrayList<Term> termIndex = new ArrayList<Term>();
			
	
public ArrayList<Term> searchList (ArrayList<Term> termIndex,String word,String docName) {
		int occur = 1;
		int first  = 0;
	    int last  = termIndex.size() - 1;
	    int middle = (first + last)/2;
	    String search = word.toLowerCase();
	    boolean flag = false;
	    Term term = new Term(word);	    	
	    int index = -1;
	    
	    //if first word it automatically adds it and also adds 1 to the corresponding element in occurrList
	    if(termIndex.size() == 0) {
	    	termIndex.add(term);
	    	termIndex.get(0).incFrequency(docName);
	    	
	    	System.out.println(termIndex.get(0).toString() );
	    	
	    	//occurrList.add(1);
	    
	    }else {
	    	
	    
		 // if list > arg 2 return 1
		 // if list < arg 2 return -1
		 // if list == arg 2 return 0
		    //System.out.println(list);
		    while( first <= last )
		    {
		    	//Input word (search) is greater than the current middle
		      if ( checkWord(termIndex.get(middle).getName(),search.toLowerCase()) <0) {	    	  
		    	  first = middle + 1;	    	 
		    	  
		      }//Input word (search) is equal to the current middle 	         
		      else if (checkWord(termIndex.get(middle).getName(),search.toLowerCase()) == 0 ) 
		      {
		    	//int value = occurrList.get(middle)+1;
		    	//occurrList.set(middle, value);
		    	  index = middle;
		    	  termIndex.get(index).incFrequency(docName);
		    	  System.out.println(termIndex.get(middle).toString() );
		    	  
		        flag = true;
		        break;
		      }//Input word (search) is less than the current middle
		      else {    	  
		    	  last = middle - 1;    	  
		      }
		   
		     
		      middle = (first + last)/2;
		      
		   }	
		    if(flag == false) {
		    	
		    	//System.out.println(middle);
		    	//System.out.println(list.size());
		    	
		    	
		    	//checks if the search is list < search	 	    
		 	    if(checkWord(termIndex.get(middle).getName(),search) < 0) {
		 	    	//if the location is at the end at there
		 	    	if(termIndex.size() == (middle+1)) {		 	    		
		 	    		termIndex.add(term);
		 	    		index = termIndex.size();		 	    		
		 	    		termIndex.get(index).incFrequency(docName);
		 	    		//System.out.println(termIndex.get(index).toString() );
		 	    		
		 	    		//occurrList.add(occur);
		 	    		//System.out.println("1-2 Perfect Spot: End ");
		 	    	}
		 	    	//if its located in the middle then add to that location 
		 	    	else {
		 	    		termIndex.add(middle+1,term);
		 	    		index = middle+1;
		 	    		termIndex.get(index).incFrequency(docName);
		 	    		//System.out.println(termIndex.get(index).toString() );
		 	    		//occurrList.add(middle+1,occur);
		 	    		//System.out.println("1 Perfect Spot: "+(middle+1));
		 	    	}
		 	    	//adds element to the start of the arraylist
		 	    }else {
		 	    	termIndex.add(0,term);
		 	    	index = 0;
		 	    	
		 	    	termIndex.get(index).incFrequency(docName);
		 	    	
		 	    	//occurrList.add(0,occur);
		 	    	//System.out.println("3 Perfect Spot: Start");
		 	    	
		 	    }
		 	    	
		 	   //System.out.println(list.size());
		 	    
		 	 
		    }
		   
	    
	    
		
		
	    }
		
		
		
	 
	    
		return termIndex;
		
		
	}

	/*
	 * Checks two words to see which one goes before the other in alphabetical order. 
	 * To do this it checks each character, if one is shorter than the other it
	 * takes that into account.
	 */
	
	private int checkWord(String firstWord, String secondWord) {
		int minLetters = isShorter(firstWord,secondWord);
		int flag = 0;
		String first = firstWord.toLowerCase();
		String second = secondWord.toLowerCase();
		

		// if arg 1 > arg 2 return 1
		// if arg 1 < arg 2 return -1
		// if arg 1 == arg 2 return 0
		//System.out.println(first+" VS "+second);
		for(int i=0;i<minLetters;i++) {
			String firstS = Character.toString(first.charAt(i));
			String secondS = Character.toString(second.charAt(i));
			
			//System.out.println(first.charAt(i)+" vs "+second.charAt(i));
				if(firstS.compareTo(secondS) <0) {
					return -1;
					
				}
					
				else if(firstS.compareTo(secondS) >0)
					return 1;
							
				
							
		}
			if(flag == 0) {
				if(first.length() > second.length()) 
					return 1;
				else if(first.length() < second.length())
					return -1;
			}
				
		
		return flag;
	}
	
	/*
	 * Checks two words to see which one is longer 
	 * returns the length of the longest
	 */
	private int isShorter(String first, String second) {
		
		if(first.length() > second.length())
			return second.length();
		else if(first.length() < second.length())
			return first.length();
		else
			return first.length();		
		
	}
//	public ArrayList<Integer> getOccurrList() {
//		
//		return occurrList;
//	}
	
	public String toString() {
		
		
		return termIndex.toString();
		
	}
	
			
	public static void main(String[] args) {		
		BinarySearch s = new BinarySearch();
		
		ArrayList <String> wordList = new ArrayList <String>();
		ArrayList <String> sortedList = new ArrayList <String>();
		ArrayList <Term> termIndex = new ArrayList <Term>();
		
		
		termIndex = s.searchList(termIndex, "jeremy","DocName");
		termIndex = s.searchList(termIndex, "jeremy","Doc");
		//termIndex = s.searchList(termIndex, "jeremy","Alan");
		
		   System.out.println(s.toString() );
		
		//System.out.println(s.toString());
		
		
		//wordList.add("c");
		//wordList.add("z");
		//wordList.add("q");
		//wordList.add("a");		
		//wordList.add("x");
		//wordList.add("b");
		
		
		
//		sortedList = s.searchList(wordList, "jeremy");
//		sortedList = s.searchList(sortedList, "aldrich");
//		sortedList = s.searchList(sortedList, "albrich");
//		sortedList = s.searchList(sortedList, "farg");
//		sortedList = s.searchList(sortedList, "a");
//		sortedList = s.searchList(sortedList, "be");
		
//		sortedList = s.searchList(wordList, "z");		
//		sortedList = s.searchList(sortedList, "c");
//		sortedList = s.searchList(sortedList, "a");
//		sortedList = s.searchList(sortedList, "c");
//		sortedList = s.searchList(sortedList, "g");
//		sortedList = s.searchList(sortedList, "h");
//		sortedList = s.searchList(sortedList, "e");
//		sortedList = s.searchList(sortedList, "z");
			  

		// if arg 1 > arg 2 return 1
		// if arg 1 < arg 2 return -1
		// if arg 1 == arg 2 return 0
		//System.out.println(s.checkWord("abe", "ade"));
		//System.out.println(second.compareTo(first));
		
	
		//System.out.println(first.compareTo(second));	  
		//System.out.println(wordList);		
		//System.out.println(first.compareTo(second) );
		
		//System.out.println(sortedList);
		
		
		
		
	}
}
