import java.util.ArrayList;


public class MergeSortName {
	public int count = 0;
	
	public void mergesort(ArrayList<Term> theArray){
		ArrayList<Term> tempArray = new ArrayList<Term>();
		mergesort(theArray, tempArray, 0, theArray.size()-1);
	}
	
	private void merge(ArrayList<Term> theArray, ArrayList<Term> tempArray, int first, int mid, int last){
		WebPages wp = new WebPages();
		int first1 = first;
		int last1 = mid;
		int first2 = mid + 1;
		int last2 = last;
		
		int index = first1;
		
		while((first1<=last1) && (first2 <= last2)){
			if((theArray.get(first1).getName()).compareTo(theArray.get(first2).getName())<=0){
				count++;
				tempArray.add(index, theArray.get(first1));
				first1++;
			}else{
				tempArray.add(index, theArray.get(first2));
				first2++;
			}
			index++;
		}
		
		while(first1 <= last1){
			tempArray.add(index, theArray.get(first1));
			first1++;
			index++;
		}
		
		while(first2<=last2){
			tempArray.add(index, theArray.get(first2));
			first2++;
			index++;
		}
		for(index = first; index<=last; index++){
			theArray.set(index,tempArray.get(index));
		}
	}
	
	public void mergesort(ArrayList<Term> theArray, ArrayList<Term> tempArray, int first, int last){
		if(first<last){
			int mid = (first+last)/2;
			mergesort(theArray, tempArray, first, mid);
			mergesort(theArray, tempArray, mid+1, last);
			
			merge(theArray, tempArray, first, mid, last);
			
		}
		
	}

}
