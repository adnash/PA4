import java.util.ArrayList;


public class BinarySearch {			
		
			
	
public ArrayList<String> searchList (ArrayList<String> list,String word) {
		
		int first  = 0;
	    int last   = list.size() - 1;
	    int middle = (first + last)/2;
	    String search = word.toLowerCase();
	    boolean flag = false;
	    
	    
	    if(list.size() == 0)
	    	list.add(search);
	    
	    else {
	    	
	    
	 // if list > arg 2 return 1
	 // if list < arg 2 return -1
	 // if list == arg 2 return 0
	    //System.out.println(list);
	    while( first <= last )
	    {
	    	
	      if ( checkWord(list.get(middle),search.toLowerCase()) <0) {	    	  
	    	  first = middle + 1;	    	 
	    	  
	      }	         
	      else if (checkWord(list.get(middle),search.toLowerCase()) == 0 ) 
	      {
	        //System.out.println(search + " found at location " + (middle) + ".");
	        flag = true;
	        break;
	      }
	      else {    	  
	    	  last = middle - 1;    	  
	      }
	   
	     
	      middle = (first + last)/2;
	      
	   }	
	    if(flag == false) {
	    	
	    	//System.out.println(middle);
	    	//System.out.println(list.size());
	    	
	    	
	    	//checks if the search is list < search	 	    
	 	    if(checkWord(list.get(middle),search) < 0) {
	 	    	//if the location is at the end at there
	 	    	if(list.size() == (middle+1)) {
	 	    		list.add(word);
	 	    		//System.out.println("1-2 Perfect Spot: End ");
	 	    	}
	 	    	//if its located in the middle then add to that location 
	 	    	else {
	 	    		list.add(middle+1,word);
	 	    		//System.out.println("1 Perfect Spot: "+(middle+1));
	 	    	}
	 	    	//adds element to the start of the arraylist
	 	    }else {
	 	    	list.add(0,word);
	 	    	//System.out.println("3 Perfect Spot: Start");
	 	    	
	 	    }
	 	    	
	 	   //System.out.println(list.size());
	    }
	   
	    
	    
		
		
	    }
		
		
		
		
		return list;
		
		
	}
	
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
	
	private int isShorter(String first, String second) {
		
		if(first.length() > second.length())
			return second.length();
		else if(first.length() < second.length())
			return first.length();
		else
			return first.length();		
		
	}
	
			
	public static void main(String[] args) {		
		BinarySearch s = new BinarySearch();
		
		ArrayList <String> wordList = new ArrayList <String>();
		ArrayList <String> sortedList = new ArrayList <String>();
		
		
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
