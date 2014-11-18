import java.util.Hashtable;
public class HashTable {
	private int tableSize;
	private int numElements;
	private Term [] table;
	private class HashtableNode {
		private Object key;
		private Object data;

		public HashtableNode() {
			this.key = null;
			this.data = null;
		}

		public HashtableNode(Object inKey, Object inData) {
			this.key = inKey;
			this.data = inData;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Object getKey() {
			return key;
		}
		public void setKey(Object key) {
			this.key = key;
		}

		/* Equality can be based on key alone because there can't be
		 * 2 nodes with the same key in the table */
		public boolean equals(Object obj) {
			if (obj instanceof HashtableNode) {
				HashtableNode node = (HashtableNode)obj;
				return this.key.equals(node.getKey());
			}
			else {
				return false;
			}
		}

		public String toString() {
			return "Key: ["+this.key+"] data: ["+this.data+"]";
		}
	}
	public void setTableSize(int size){
		this.tableSize = size;
	}
	private int hash(String key) {

		/* Start with a base, just so that it's not 0 for empty strings */
		int result = key.hashCode();

		return (result % this.tableSize);
	}
	public void add(String filename, String newWord) {
		newWord = newWord.toLowerCase();
		String key = newWord;
		Term dataT = new Term(newWord);
		dataT.incFrequency(filename);

		if (dataT == null || key == null) {
			System.err.println("ERROR: Either the key or the data are null");
			return;
		}

		/* Don't add duplicate keys */
		if (this.contains(key)) {
			return;
		}

		/* Find out where in our array should the item go */
		int position = this.hash(key);

		/* If nothing exists in the position, create a new linked list there */
		boolean set = true;
		while(set){
			if (this.table[position] == null) {
				this.table[position] = dataT;
				set = false;			
			}

		}
		this.numElements++;
	}
	public void remove(String key) {
		Term dataT = new Term("RESERVED");
		int hashVal = this.hash(key);
		while(hashVal<tableSize){
			if(this.table[hashVal].equals(key)){
				this.table[hashVal] = dataT;
			}
			hashVal++;
		}

	}
	public int getNumElements() {
		return this.numElements;
	}

	public Term get(String word, Boolean printP){
		int hash = this.hash(word);
		while(hash<tableSize){
			if(this.table[hash].equals(word)){
				return this.table[hash];
			}
			hash++;
		}
		return null;
	}

	public boolean contains(String key) {
		int hash = this.hash(key);
		while(hash<tableSize){
			if(this.table[hash].equals(key)){
				return true;
			}
			hash++;
		}
		return false;
	}


}