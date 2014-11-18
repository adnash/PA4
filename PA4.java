
public class PA4 {
	public static void main(String[] args){

		if(args.length ==0) {
			System.err.println("Error: No args found!");
		}

		String fileName = args[0];		
		WebPages wP = new WebPages();		
		wP.readFirstFile(fileName);
		//wP.printTerms();

	}
}
