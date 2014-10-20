import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class WebPages {
	private ArrayList<Term> termsList;
	private TreeNode<Term> termsTree;
	private BST bst = new BST();
	public int countMerge;
	private boolean startFlag = false;
	private TreeNode<Term> node;
	private boolean printFlag = true;
	private float totalDocs = 0;



	public WebPages() {
		termsList = new ArrayList<Term>();

	}

	public void addPage(String fileName) {

		totalDocs++;
		readFile(fileName, fileName);


		//printTerms();
		//termsList.addAll(p.readFile(fileName, fileName));
		//System.out.println(termsList);		

		//		BSTIterator<Term> iter = new BSTIterator<Term>(bst.getNode());
		//		
		//		while(iter.hasNext()) {
		//			
		//			System.out.println(iter.next().getName());
		//		}
		//		


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


	public Term whichPages(String word) {
		String temp = word;
		word = word.toLowerCase();
		Term term;

		String docs = null;

		if(printFlag) {
			printTerms(); 
			printFlag  = false;
			System.out.println();
		}
		term = bst.get(word, true);

		float TF = 0;// occurrences of the term in the document
		float wordtermDocs = 0;
		if(term!=null)
			wordtermDocs = term.getDocNames().size();//number of docs the term is in
		double TFIDF = 0;// equation is TFIDF(d,t) = TF(d,t) * log (D / DF(t))

		if(term == null)
			System.out.println(temp+" not found");
		else {

			for(int i=0;i<term.getDocNames().size();i++){
				if(i==0){
					TF = term.getDocNames().get(i).getTermFrequency();
					TFIDF = TF * (Math.log(totalDocs/wordtermDocs));
					String num = String.format("%.2f", (double)TFIDF);
					docs = " "+term.getDocNames().get(i).getDocName() + ": " + num;
				}
				else{
					TF = term.getDocNames().get(i).getTermFrequency();
					TFIDF = TF * (Math.log(totalDocs/wordtermDocs));
					String num = String.format("%.2f", (double)TFIDF);
					docs +=  ", "+term.getDocNames().get(i).getDocName()+": " + num; // ADD TFIDF HERE
				}
			}


			System.out.println(word+" in pages:"+docs);
		}


		//		ArrayList<String> temp = new ArrayList<String>();
		//		int countName = 0;
		//		while(countName<termsList.size()){
		//			if(word.equals(termsList.get(countName).getName())){
		//				for(int i = 0; i<(termsList.get(countName).getDocNames()).size(); i++){
		//					temp.add(termsList.get(countName).getDocNames().get(i).getDocName());
		//				}
		//				break;
		//			}
		//			countName++;
		//		}

		//System.out.println("Found Term: "+term.getName()+"in"+term.getDocNames());



		return term;
	}

	public void readFirstFile(String fileName){
		//ArrayList<String> searchWords = new ArrayList<String>();
		boolean pruneTriger = false;
		String word = null;	
		boolean eofsFlag = false;
		int stopWordNum = 0;
		Term searchedTerm;

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
						//printTerms();

						//issues here 
						//pruneStopWords(stopWordNum);



						//System.out.print("\n");

						pruneTriger = true;
						//if scanner is before *EOFS* t
					}else if(eofsFlag == false) {
						addPage(word);
						//words to be searched added to an ArrayList to search later 	
					}else{


						searchedTerm = whichPages(word);		


						//						if(searchedTerm == null)
						//							System.out.println(word + " not found");
						//						else{
						//							System.out.print(word + " in pages: ");
						//							for(int i = 0; i<termLocation.size()-1; i++){
						//								System.out.print(termLocation.get(i) +", ");								
						//							}
						//							//System.out.println(termLocation.get(termLocation.size()-1));
						//						}
						//						//searchWords.add(word);
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
		BSTIterator<Term> iter = new BSTIterator<Term>(termsTree);		
		while(iter.hasNext()) {
			System.out.println(iter.next().getName());
		}


		//		System.out.println("WORDS");
		//		for(int i=0;i<termsList.size();i++) {
		//			System.out.println(termsList.get(i).getName());
		//		}
		//		

	}
	public void readFile(String fileName,String docName) {
		String word = null;
		String wordPuncRemoved = null;
		String htmlRemoved = null;
		//BinarySearch search = new BinarySearch();	
		BST bst = new BST();



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



						}

					}


					if(flag) {



						temp = temp.replaceAll("\\s+","");
						tempTwo = tempTwo.replaceAll("\\s+","");

						if(!temp.isEmpty())
							bst.add(docName,temp);
						//termIndex = search.searchList(termIndex, temp,docName);
						if(!tempTwo.isEmpty())
							bst.add(docName,tempTwo);
						//termIndex = search.searchList(termIndex, tempTwo,docName);
						flag = false;
					}else {
						wordPuncRemoved = wordPuncRemoved.replaceAll("[\\s]*","");
						if(wordPuncRemoved.isEmpty() == false) {
							bst.add(docName,wordPuncRemoved);
							//System.out.println(wordPuncRemoved);
							//termIndex = search.searchList(termIndex, wordPuncRemoved,fileName);

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
			//occurList = search.getOccurrList();

			//System.out.println(occurList.size() );
			//System.out.println(termIndex.size()+"\n");

			//			for(int i=0;i<fileText.size();i++) {
			//				System.out.print(fileText.get(i)+"\n");
			//				
			//			}

			//			for(int i=0;i<occurList.size();i++) {
			//				System.out.print("Index: "+i+" "+occurList.get(i)+" = "+fileText.get(i)+"\n");				
			//			}
			//System.out.println("");			
			//System.out.println("NUMBER OF WORDS: "+fileText.size() );

		} catch (FileNotFoundException e) {			
			System.err.println("Error: found in output!");
		}

		this.termsTree = bst.getNode();


	}
	/*
	 * Removes special characters including Punctuation
	 * 
	 */
	private String removePunctuation(String word) {		
		//removes all punctuation and special characters
		// adds a space so we can deal with words such as pre-req
		return word.replaceAll("[&@#$%^*()\\\"\\\\/$\\-\\!\\+\\=|(){},.;:!?\\%]+", " ");		

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




}