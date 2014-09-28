import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class WebPages {
	public static ArrayList<Term> termsList;

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

	public static ArrayList<Term> mergeSort(ArrayList<Term> list) {
		if (list.size() > 1) {
			ArrayList<Term> leftList = new ArrayList<Term>(list.subList(0, list.size()/2));
			ArrayList<Term> rightList = new ArrayList<Term>(list.subList(list.size()/2 + 1, list.size()));
			mergeSort(leftList);
			mergeSort(rightList);
			list = merge(list,leftList,rightList);
		}
		return list;
	}

	public static ArrayList<Term> merge(ArrayList<Term> a, ArrayList<Term> l, ArrayList<Term> r) {
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
		P1 p = new P1();
		String word = null;		
		ArrayList<String> temp = new ArrayList<String>();
		try {
			Scanner read = new Scanner(new File(fileName));
			word = read.next();
			while(!word.equals("*EOFs*")) {
				p.readFile(word, word);
				word = read.next();
				
			}
			while(read.hasNext()){
				if(read.hasNextInt()){
					int n = read.nextInt();
					pruneStopWords(n);
				}else{
					word = read.next();
					temp = whichPages(word);
					if(temp == null){
						System.out.println(word + " not found");
					}else{
						System.out.println(word + " in pages : " + temp);
					}
				}
			}
			read.close();	
		} catch (FileNotFoundException e) {			
			System.err.println("Error: found in output!");
		}
		
	}





}
