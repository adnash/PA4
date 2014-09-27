import java.util.ArrayList;


public class WebPages {
	
	public WebPages() {
		ArrayList<Term> termsList = new ArrayList<Term>();
		
	}
	
	public void addPage(String fileName) {
		P1 p = new P1();		
		
		p.readFile(fileName);
		
	}
	
	public void pruneStopWords(int n) {
		
		
	}
	
	public ArrayList<String> whichPages(String word) {
		
		return null;
	}
	
	

}
