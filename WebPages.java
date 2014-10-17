import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class WebPages {
	private ArrayList<Term> termsList;
	public BinaryTree<Term> termsTree;
	public int countMerge;

	public WebPages() {
		termsList = new ArrayList<Term>();

	}

	public void addPage(String fileName) {
		P1 p = new P1();
		
		termsList = p.readFile(fileName, fileName,termsList);

		//termsList.addAll(p.readFile(fileName, fileName));
		//System.out.println(termsList);		

	}

	public void pruneStopWords(int n) {
		ArrayList<Term> temp = new ArrayList<Term>();
		MergeSortName msn = new MergeSortName();
		MergeSortFreq msf = new MergeSortFreq();
		temp = termsList; 
		System.out.println();
		//temp = mergeSortFreq(temp);
		msf.mergesort(temp);
		
		System.out.println("Copies: " + msf.count);
		while(n > 0){
			temp.remove(temp.size()-1);
			n--;
		}
		msn.mergesort(temp);
		termsList = temp;
		System.out.println("Copies: " + msn.count + "\n");
	}
	
	public ArrayList<Term> mergeSortFreq(ArrayList<Term> list) {
		if (list.size() > 1) {
			ArrayList<Term> leftList = new ArrayList<Term>(list.subList(0, list.size()/2));
			ArrayList<Term> rightList = new ArrayList<Term>(list.subList(list.size()/2, list.size()));
			leftList = mergeSortFreq(leftList);
			rightList = mergeSortFreq(rightList);
			list = mergeFreq(leftList,rightList);
		}
		return list;
	}

	public ArrayList<Term> mergeFreq(ArrayList<Term> l, ArrayList<Term> r) {
		int i =0, j = 0;
		ArrayList<Term> a = new ArrayList<Term>();
		while(i<l.size() && j<r.size()){
			if(l.get(i).getTotalFrequency() >= r.get(j).getTotalFrequency()){
				countMerge++;
				a.add(l.get(i));
				i++;
			}else{
				a.add(r.get(j));
				j++;
			}
		}
		while(i<l.size()){
			a.add(l.get(i));
			i++;
		}
		while(j<r.size()){
			a.add(r.get(j));
			j++;
		}

		return a;
	}
	
	public ArrayList<Term> mergeSortName(ArrayList<Term> list) {
		if (list.size() > 1) {
			ArrayList<Term> leftList = new ArrayList<Term>(list.subList(0, list.size()/2));
			ArrayList<Term> rightList = new ArrayList<Term>(list.subList(list.size()/2, list.size()));
			leftList = mergeSortName(leftList);
			rightList = mergeSortName(rightList);
			list = mergeName(leftList,rightList);
		}
		return list;
	}

	public ArrayList<Term> mergeName(ArrayList<Term> l, ArrayList<Term> r) {
		int i =0, j = 0;
		ArrayList<Term> a = new ArrayList<Term>();
		while(i<l.size() && j<r.size()){
			if(l.get(i).getName().compareTo(r.get(j).getName())<0){
				countMerge++;
				a.add(l.get(i));
				i++;
			}else{
				a.add(r.get(j));
				j++;
			}
		}
		while(i<l.size()){
			a.add(l.get(i));
			i++;
		}
		while(j<r.size()){
			a.add(r.get(j));
			j++;
		}

		return a;
	}

	public ArrayList<String> whichPages(String word) {
		word = word.toLowerCase();
		ArrayList<String> temp = new ArrayList<String>();
		int countName = 0;
		while(countName<termsList.size()){
			if(word.equals(termsList.get(countName).getName())){
				for(int i = 0; i<(termsList.get(countName).getDocNames()).size(); i++){
					temp.add(termsList.get(countName).getDocNames().get(i).getDocName());
				}
				break;
			}
			countName++;
		}
		return temp;
	}
	
	public void readFirstFile(String fileName){
		//ArrayList<String> searchWords = new ArrayList<String>();
		boolean pruneTriger = false;
		String word = null;	
		boolean eofsFlag = false;
		int stopWordNum = 0;
		
		ArrayList<String> termLocation = new ArrayList<String>();
		try {
			Scanner read = new Scanner(new File(fileName));
			
			while(read.hasNext()) {
				word = read.next();
				
				//System.out.println(word);
								
				if(word.compareTo("*EOFs*")==0)
					eofsFlag = true;
				else {	
					//Checks for the integer for prune stop word amount
					if(isInteger(word) && pruneTriger == false) {							
						stopWordNum = Integer.parseInt(word);
						
						//System.out.println("WORDS");
						printTerms();
						
						//issues here 
						pruneStopWords(stopWordNum);
						
						
						printTerms();
						System.out.print("\n");
						
						pruneTriger = true;
					//if scanner is before *EOFS* t
					}else if(eofsFlag == false) {
						addPage(word);
					//words to be searched added to an ArrayList to search later 	
					}else{
						
						//Issues here 
						termLocation = whichPages(word);		
						
						
						if(termLocation.isEmpty())
							System.out.println(word + " not found");
						else{
							System.out.print(word + " in pages: ");
							for(int i = 0; i<termLocation.size()-1; i++){
								System.out.print(termLocation.get(i) +", ");								
							}
							System.out.println(termLocation.get(termLocation.size()-1));
						}
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
	
	public void printTerms()  {
		System.out.println("WORDS");
		for(int i=0;i<termsList.size();i++) {
			System.out.println(termsList.get(i).getName());
		}
		
		
	}



}