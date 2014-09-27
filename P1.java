import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Takes in a file and removes: HTML, Punctuation and special characters
 * 
 */

/**
 * @author Jeremy Aldrich
 * 
 *
 */
public class P1 {
	
	private boolean startFlag = false;	
	private ArrayList<String> fileText = new ArrayList<String>();
	private ArrayList<Integer> occurList = new ArrayList<Integer>();	
	private ArrayList<Term> terms = new ArrayList<Term>();
	
	
	
	public ArrayList<Term>getTermList() {
		
		return terms;
	}
	
	public ArrayList<String> readFile(String fileName) {
		String word = null;
		String wordPuncRemoved = null;
		String htmlRemoved = null;
		BinarySearch search = new BinarySearch();		
		
		
				
		try {
			Scanner read = new Scanner(new File(fileName));
			
			
			while(read.hasNext() ) {
				String temp = null;
				String tempTwo = null;
				word = read.next();
				boolean flag = false;
				
				//System.out.println(word);
				
				//System.out.println(word+" =");
				
				if(word.isEmpty() == false)
				{
					htmlRemoved = removeHTML(word).replaceAll("\\s+","");
					wordPuncRemoved = removePunctuation(htmlRemoved).toLowerCase();
					
					
						for(int i=0;i<wordPuncRemoved.length();i++) {
							
														
							if(wordPuncRemoved.charAt(i) == ' ' && i >= 1) {
								flag = true;								
								temp = wordPuncRemoved.substring(0, i);
								tempTwo = wordPuncRemoved.substring(i+1,wordPuncRemoved.length() );
								
								//System.out.println(temp+"...."+tempTwo);
																
								break;
								
							}
							
						}
					
					
					if(flag) {
						
						temp = temp.replaceAll("\\s+","");
						tempTwo = tempTwo.replaceAll("\\s+","");
						
						if(!temp.isEmpty())
							fileText = search.searchList(fileText, temp);
						if(!tempTwo.isEmpty())
							fileText = search.searchList(fileText, tempTwo);
						flag = false;
					}else {
						wordPuncRemoved = wordPuncRemoved.replaceAll("[\\s]*","");
						if(wordPuncRemoved.isEmpty() == false) {
							
							//System.out.println(wordPuncRemoved);
							fileText = search.searchList(fileText, wordPuncRemoved);
						
						}
						
					}
					
					
					
					
					
					//if(wordPuncRemoved.length() > 0) 
					//	tempWord.add(wordPuncRemoved);				
					//wordCount++;										
				}
				
				//text = removeWhiteSpace(wordPuncRemoved);				
				//System.out.println(wordPuncRemoved);
				
//				if(!wordPuncRemoved.isEmpty()) {
//					fileText.add(wordPuncRemoved);
//					//System.out.println(wordPuncRemoved);
//				}
					
	
				//System.out.println(fileText);				
				
				//System.out.println(removeWhiteSpace("Test"));
				//System.out.println(removeWhiteSpace("12    "));
				//System.out.println(removeWhiteSpace("Test"));
				//System.out.println(text);
				//check for punctuation
				//check for html
				//check if word already exists 
				// if exists ++ else add to list
				
				
								
			}			
			read.close();
			occurList = search.getOccurrList();
			
			System.out.println(occurList.size() );
			System.out.println(fileText.size()+"\n");
			
			for(int i=0;i<fileText.size();i++) {
				System.out.print(fileText.get(i)+"\n");
				
			}
			
//			for(int i=0;i<occurList.size();i++) {
//				System.out.print("Index: "+i+" "+occurList.get(i)+" = "+fileText.get(i)+"\n");				
//			}
			//System.out.println("");			
			//System.out.println("NUMBER OF WORDS: "+fileText.size() );
			
		} catch (FileNotFoundException e) {			
			System.err.println("Error: found in output!");
		}
		
		return null;
		
	}
	/*
	 * Removes special characters including Punctuation
	 * 
	 */
	private String removePunctuation(String word) {		
			//removes all punctuation and special characters
			// adds a space so we can deal with words such as pre-req
		return word.replaceAll("[\\\\/$\\-\\!\\+\\=|(){},.;:!?\\%]+", " ");		
		
	}	
	
	/*
	 * 
	 * Removes the HTML tags starting with < and ending with >
	 */
	private String removeHTML(String word) {
		String result = "";
		
		String temp = null;
		
		
		
		for(int i=0;i<word.length();i++) {
			char character = word.charAt(i);
			
			//if < is found we don't add that text 
			if(word.charAt(i) == '<') {
				startFlag = true;				
			}
				
			
			//if < is found we don't add that text 
			if(startFlag == true) {				
								
				//System.out.println(word.charAt(i)+" Removed");
				//end bracket > found so we can continue to add now 
				if(character == '>') {
					startFlag = false;
				}
					
			}else {
				
				if(i == 0) {				
					result = Character.toString(character);
				} else {
					result += character;
				}		
				
			}				
			
		}	
			temp = result;
			return temp;		
		
	}		
/*
 * Returns the the number of occurances each word has. 
 * Each element matches up with the word in the textFile List 	
 */	
public ArrayList<Integer> getOccurrList() {
		
		return occurList;
	}
	
	

	public static void main(String[] args) {
		
		if(args.length ==0) {
			System.err.println("Error: No args found!");
		}
		
		String fileName = args[0];
		
		P1 p = new P1();
		
		p.readFile(fileName);
		
		
		
		
		

	}
	
}
