import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;


public class WebPages {
	public int countMerge;
	private boolean startFlag = false;
	private boolean printFlag = true;
	private double totalDocs = 0;
	HashTable ht = new HashTable();
	String[] docs = new String[(int)totalDocs];
	double[] common = new double[(int)totalDocs];
	double[] docSpecific = new double[(int)totalDocs];
	String[] docNames = new String[10];
	double queryWeights;
	Graph g = new Graph();
	String fileGraphName;

	public void addPage(String fileName) {
		g.add(fileName);
		addLinks(fileName);
		docNames[(int)totalDocs] = fileName;
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

	public void pruneStopWords(String n) {
		ht.remove(n); 
	}


	public Term whichPages(String word) {
		String temp = word;
		word = word.toLowerCase();
		Term term;

		String docs = null;

		if(printFlag) {
			//printTerms(); 
			printFlag  = false;
			System.out.println();
		}
		term = ht.get(word, true);

		double TF = 0;// occurrences of the term in the document
		double wordtermDocs = 0;
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

	public void SIM(Term term, String[] query){
		double wiq = 0;
		double half = 0.5000000000000000000;
		double full = 1.0;
		for(int i = 0; i<query.length; i++){
			if(term.getName().equals(query[i])){
				if(term != null)
					wiq = ((double)half*((double)full+(double)Math.log((double)totalDocs/(double)(term.getDocNames().size()))));
				queryWeights += (double)Math.pow(wiq,2);
			}
		}

		int docSpot = 0;
		if(term != null){
			double wordtermDocs = term.getDocNames().size();//number of docs the term is in
			for(int j=0; j<totalDocs; j++){
				if(docSpot>=term.getDocNames().size()){
					break;
				}
				if(docNames[j].equals(term.getDocNames().get(docSpot).getDocName())){
					double TF = 0;// occurrences of the term in the document
					double TFIDF = 0;
					TF = (double)term.getDocNames().get(docSpot).getTermFrequency();
					TFIDF = (double)TF * (double)(Math.log((double)totalDocs/(double)wordtermDocs));
					docSpecific[j] += (double)Math.pow(TFIDF,2);
					for(int i = 0; i<query.length; i++){
						if(term.getName().equals(query[i])){
							if(term != null)
								common[j] += (double)TFIDF * (double)wiq;
						}
					}
					docSpot++;

				}
			}
		}



	}

	public SimObject bestPages(String word){
		word = word.toLowerCase();
		String words[] = word.split(" ");
		queryWeights = 0;
		docs = new String[(int)totalDocs];
		common = new double[(int)totalDocs];
		docSpecific = new double[(int)totalDocs];

		for(int i = 0; i<ht.tableSize;i++){
			if(ht.table[i] != null)
				SIM(ht.table[i], words);
		}

		double total = 0;
		double temp = 0;
		String docFinal = null;
		for(int i = 0; i<totalDocs; i++){
			temp = (double)common[i]/((double)Math.sqrt(docSpecific[i])*(double)Math.sqrt(queryWeights));
			temp = temp * g.inDegree(docNames[i]);
			if(temp>=total){
				total = temp;
				docFinal = docNames[i];
			}
		}
		SimObject sim = new SimObject(docFinal, (float)total);
		if(docFinal == null){
			System.out.print("[");
			for(int i = 0; i<words.length;i++){
				System.out.print(words[i]+ " ");
			}
			System.out.println("] not found");
		}else{
			total = Math.floor(total * 100) / 100;
			DecimalFormat format = new DecimalFormat("0.00"); 
			Arrays.sort(words);
			System.out.print("[");
			for(int i = 0; i<words.length;i++){
				System.out.print(words[i]+ " ");
			}
			System.out.println("] in " + docFinal+": " +format.format(total));
		}
		return sim;
	}

	public void readFirstFile(String fileName){
		//ArrayList<String> searchWords = new ArrayList<String>();
		String word = null;	
		boolean eofsFlag = false;
		boolean stopsFlag = false;
		boolean printedFlag = false;
		try {
			Scanner read = new Scanner(new File(fileName));
			fileGraphName = read.next(); 
			if(read.hasNextInt()){
				word = read.next();
				int size = Integer.parseInt(word);
				ht.setTableSize(size);
			}
			ht.constructTable();
			while(!eofsFlag || !stopsFlag || !printedFlag) {
				word = read.next();

				//System.out.println(word);

				if(word.compareTo("*EOFs*")==0)
					eofsFlag = true;
				else if(word.compareTo("*STOPs*") == 0){
					stopsFlag = true;
					//printTerms();
					printedFlag = true;
				}
				else {	
					//Checks for the integer for prune stop word amount
					if(stopsFlag == false && eofsFlag == true) {							
						pruneStopWords(word);
						//System.out.println("WORDS");
						//printTerms();

						//issues here 
						//pruneStopWords(stopWordNum);



						//System.out.print("\n");

						//if scanner is before *EOFS* t
					}else if(eofsFlag == false) {
						addPage(word);
						//words to be searched added to an ArrayList to search later 	
					}		


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
			word = read.nextLine();
			while(read.hasNextLine()){
				word = read.nextLine();
				if(!word.equals(null) || !word.equals("")){
					bestPages(word);
				}


			}
			try {
				g.writeDotFile(fileGraphName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		ht.printTable();

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



		try {
			Scanner read = new Scanner(new File(fileName));


			while(read.hasNext() ) {
				word = read.next();

				//System.out.println(word);

				//System.out.println(word+" =");

				if(word.isEmpty() == false)
				{
					htmlRemoved = removeHTML(word).replaceAll("\\s+","");
					wordPuncRemoved = removePunctuation(htmlRemoved).toLowerCase();

					String words[] = wordPuncRemoved.split(" ");
					for(int i=0;i<words.length;i++) { 
						ht.add(docName,words[i]);
					}

					/*}else {
						wordPuncRemoved = wordPuncRemoved.replaceAll("[\\s]*","");
						if(wordPuncRemoved.isEmpty() == false) {
							ht.add(docName,wordPuncRemoved);
							//System.out.println(wordPuncRemoved);
							//termIndex = search.searchList(termIndex, wordPuncRemoved,fileName);

						}

					}*/





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

	}
	/*
	 * Removes special characters including Punctuation
	 * 
	 */
	private String removePunctuation(String word) {		
		//removes all punctuation and special characters
		// adds a space so we can deal with words such as pre-req
		return word.replaceAll("[&@#$%^*()\\\"\\\\/$\\-\\!\\+\\=|(){},.;:!?\\%_~`]+", " ");		

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
			//if(word.charAt(i) == '<' && word.charAt(i+2) == '>'){
			//	result += " ";
			//	i+=2;
			//}

			//}
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

	//Adds every link in the file with the path given by fileName to the graph.
	//Links must have the following form:
	//     <a href="http://FILENAME">
	//Where:
	//	FILENAME is the name of the file being linked to
	//	Any (nonzero) amount of whitespace can come between "<a" and "href= ... >"
	public void addLinks(String fileName){
		try {
			File file = new File(fileName);
			Scanner fReader = new Scanner(file);

			String word = null;

			while(fReader.hasNext()){
				word = fReader.next();
				if(word.contains("<a") && fReader.hasNext()){
					word = fReader.next();
					if(word.startsWith("href=\"http://")){
						word = word.substring(13);						//The first character of FILENAME has index 13.
						if(word.contains("\"")){
							word = word.substring(0, word.indexOf("\">"));
							if(!word.equals("")){
								g.add(fileName, word);
							}
						}
					}
				}
			}

			fReader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}