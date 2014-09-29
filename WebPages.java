import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class WebPages {
	private ArrayList<Term> termsList;

	public WebPages() {
		termsList = new ArrayList<Term>();

	}

	public void addPage(String fileName) {
		P1 p = new P1();	

		termsList = p.readFile(fileName, fileName);

	}

	public void pruneStopWords(int n) {
		ArrayList<Term> temp = new ArrayList<Term>();
		temp = termsList; 
		mergeSort(temp);
		int count = 0;
		while(count<n){
			String name = temp.get(0).getName();
			int countName = 0;
			while(countName<termsList.size()){
				if(name.equals(termsList.get(countName).getName())){
					termsList.remove(countName);
					break;
				}
			}
			temp.remove(0);
			count++;
		}
	}

	public ArrayList<Term> mergeSort(ArrayList<Term> list) {
		if (list.size() > 1) {
			ArrayList<Term> leftList = new ArrayList<Term>(list.subList(0, list.size()/2));
			ArrayList<Term> rightList = new ArrayList<Term>(list.subList(list.size()/2 + 1, list.size()));
			mergeSort(leftList);
			mergeSort(rightList);
			list = merge(list,leftList,rightList);
		}
		return list;
	}

	public ArrayList<Term> merge(ArrayList<Term> a, ArrayList<Term> l, ArrayList<Term> r) {
		int i =0, j = 0;
		while(l != null && r != null){
			if(l.get(i).getTotalFrequency() > r.get(j).getTotalFrequency()){
				a.add(l.get(i++));
			}else{
				a.add(r.get(j++));
			}
		}
		while(l != null){
			a.add(l.get(i++));
		}
		while(r != null){
			a.add(r.get(j++));
		}

		return a;
	}

	public ArrayList<String> whichPages(String word) {
		ArrayList<String> temp = new ArrayList<String>();
		int countName = 0;
		while(countName<termsList.size()){
			if(word.equals(termsList.get(countName).getName())){
				for(int i = 0; i<(termsList.get(countName).getDocNames()).size(); i++){
					temp.add(termsList.get(countName).getDocNames().get(i).getDocName());
				}
			}
		}
		return temp;
	}
	
	public void readFirstFile(String fileName){
		ArrayList<String> searchWords = new ArrayList<String>();
		boolean pruneTriger = false;
		String word = null;	
		boolean eofsFlag = false;
		int stopWordNum = 0;
		
		ArrayList<String> termLocation = new ArrayList<String>();
		try {
			Scanner read = new Scanner(new File(fileName));
			
			while(read.hasNext()) {
				word = read.next();
				
				System.out.println(word);
								
				if(word.compareTo("*EOFs*")==0)
					eofsFlag = true;
				else {	
					//Checks for the integer for prune stop word amount
					if(isInteger(word) && pruneTriger == false) {							
						stopWordNum = Integer.parseInt(word);
						
						//issues here 
						pruneStopWords(stopWordNum);
						
						pruneTriger = true;
					//if scanner is before *EOFS* t
					}else if(eofsFlag == false) {
						addPage(word);
					//words to be searched added to an ArrayList to search later 	
					}else{
						
						//Issues here 
						termLocation = whichPages(word);		
						
						if(termLocation == null)
							System.out.println(word + " not found");
						else
							System.out.println(word + " in pages : " + termLocation);
						
						//searchWords.add(word);
					}
				}			
				
			}
			
			read.close();	
			
			
//			for(int i=0;i<searchWords.size();i++){
//				
//				termLocation = whichPages(searchWords.get(i));		
//				
//				if(termLocation == null)
//					System.out.println(searchWords.get(i) + " not found");
//				else
//					System.out.println(searchWords.get(i) + " in pages : " + termLocation);
//				
//			}
			
			
			
			
			
		} catch (FileNotFoundException e) {			
			System.err.println("Error: found in output!");
		}
		
	}


	public boolean isInteger(String s) {
		  boolean result = false;
		  try {
		    Integer.parseInt(s);
		    result = true;
		  } catch (NumberFormatException nfe) {
		    // no need to handle the exception
		  }
		  return result;
		}



}
