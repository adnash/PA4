import java.util.Hashtable;
public class HashTable {
	private int Table_Size = 0;
	private int Items = 0;
	Term[] ht;
	HashTable(int size){
		Table_Size = size;
		ht = new Term[size];
	}

	public void add(String filename, String newWord){
		newWord = newWord.toLowerCase();
		Term term = new Term(newWord);
		term.incFrequency(filename);
		int hash_value = newWord.toLowerCase().hashCode();
		int index = hash_value%Table_Size;
		while(ht[index] != null){
			index++;
		}
		ht[index] = term;
		Items++;
	}

	public int size(){
		return Table_Size;
	}

	public void delete(String word){
		word = word.toLowerCase();
		int hash_value = word.hashCode();
		int index = hash_value%Table_Size;
		while(index<ht.length){
			if((ht[index].getName()).equals(word)){
				Term term = new Term("RESERVED");
				ht[index] = term;
				break;
			}
			index++;
		}
	}
	
	public Term get(String word, Boolean printP){
		word = word.toLowerCase();
		int hash_value = word.hashCode();
		int index = hash_value%Table_Size;
		while(index<ht.length){
			if((ht[index].getName()).equals(word)){
				return ht[index];
			}
			index++;
		}
		return null;
	}
}
