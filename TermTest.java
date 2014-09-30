
public class TermTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Term term = new Term("Jeremy");		
		term.incFrequency("doc1");
		term.incFrequency("doc2");
		
		
		System.out.println(term);

	}

}
