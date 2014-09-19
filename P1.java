import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
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
	private ArrayList<Integer> freq = new ArrayList<Integer>();
	private ArrayList<String> tempWord = new ArrayList<String>();	
	
	
	public ArrayList<String> readFile(String fileName) {
		String word = null;
		String wordPuncRemoved = null;
		String htmlRemoved = null;
		BinarySearch search = new BinarySearch();
		int wordCount =0;
		
				
		try {
			Scanner read = new Scanner(new File(fileName));
			
			while(read.hasNext() ) {
				word = read.next();
				//System.out.println(word);
				
				//System.out.println(word+" =");
				
				if(word.isEmpty() == false)
				{
					htmlRemoved = removeHTML(word).replaceAll("\\s+","");
					wordPuncRemoved = removePunctuation(htmlRemoved).replaceAll("\\s+","").toLowerCase();
					
					if(wordPuncRemoved.isEmpty() == false) {
						fileText = search.searchList(fileText, wordPuncRemoved);
					
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
			System.out.println("WORDS");
			for(int i=0;i<fileText.size();i++) {
				System.out.println(fileText.get(i));
			}
			System.out.println("");			
			System.out.println("NUMBER OF WORDS: "+fileText.size() );
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: found in output!");
		}
		
		
		
		
		
		return null;
		
	}
	
	private String removePunctuation(String word) {		
				
		return word.replaceAll("[\\\\/$\\-\\!\\+\\=|(){},.;!?\\%]+", "");		
		
	}	
	
	
	private String removeHTML(String word) {
		String result = "";
		boolean removed = false;
		String temp = null;
		
		
		
		for(int i=0;i<word.length();i++) {
			char character = word.charAt(i);
			
			
			if(word.charAt(i) == '<') {
				startFlag = true;				
			}
				
			
			//Removes text with the start bracket < 
			if(startFlag == true) {				
				removed = true;				
				//System.out.println(word.charAt(i)+" Removed");
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
			temp = result.replaceAll("\\p{Z}","");
			return temp;		
		
	}
	
	private void checkWords() {		
		int occurance =1;		
		
				
			for(int i=0;i<tempWord.size();i++) {
				occurance =0;
				for(int m=0;m<tempWord.size();m++) 
				{
					
					if(tempWord.get(i).equals(tempWord.get(m))) {
							occurance++;
					}
				}
				
				if(ifExists(tempWord.get(i))) {
						fileText.add(tempWord.get(i));
						freq.add(occurance);
				}
						
			}
	}
	
	private boolean ifExists(String word) {
		
		for(int i=0;i<fileText.size();i++) {			
			if(fileText.get(i).equals(word))
				return false;
		}
			
		
		
		return true;
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
