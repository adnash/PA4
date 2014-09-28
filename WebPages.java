import java.util.ArrayList;


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

	static ArrayList<Term> mergeSort(ArrayList<Term> list) {
		if (list.size() > 1) {
			ArrayList<Term> leftList = new ArrayList<Term>(list.subList(0, list.size()/2));
			ArrayList<Term> rightList = new ArrayList<Term>(list.subList(list.size()/2 + 1, list.size()));
			mergeSort(leftList);
			mergeSort(rightList);
			list = merge(list,leftList,rightList);
		}
		return list;
	}

	static ArrayList<Term> merge(ArrayList<Term> a, ArrayList<Term> l, ArrayList<Term> r) {
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
		return null;
	}





}
