public class HashTable {
	public int tableSize;
	private int numElements;
	public Term [] table = new Term[123];
	private class HashtableNode {
		private Object key;
		private Object data;

		public Object getKey() {
			return key;
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
	public void constructTable(){
		table = new Term[tableSize];
		//table = null;
	}
	public void setTableSize(int size){
		if(size == 0){
			size = 1;
		}
		this.tableSize = size;
	}
	private int hash(String key, int adder) {

		/* Start with a base, just so that it's not 0 for empty strings */
		int result = Math.abs(key.hashCode())%tableSize;
		int hash = (result+(adder*adder))%tableSize;
		return hash;
	}

	public Boolean isEmpty(int position){
		if(this.table[position] == null)
			return true;
		else 
			return false;
	}
	public void add(String filename, String newWord) {
		double check = .8*tableSize;
		if(numElements>=check){
			rehash();
		}
		int search = 0;
		newWord = newWord.toLowerCase();
		String key = newWord;
		Term dataT = new Term(newWord);
		dataT.incFrequency(filename);

		if(get(key,true)!=null){
			Term temp = get(key, true);
			temp.incFrequency(filename);
			return;
		}

		if (dataT == null || key == null) {
			System.err.println("ERROR: Either the key or the data are null");
			return;
		}

		/* Find out where in our array should the item go */
		int position = this.hash(key,search);
		
		/* If nothing exists in the position, create a new linked list there */
		boolean set = true;

		while(set){
			if(position <=tableSize-1){
				if (table[position] == null) {
					this.table[position] = dataT;
					set = false;			
				}
			}
			search++;
			position  = this.hash(key,search);

		}
		numElements++;
	}

	public void rehash(){
		tableSize = (tableSize*2) + 1;
		Term temp[] = table;
		constructTable();
		numElements = 0;
		for(int i=0; i<table.length; i++){
			table[i] = null;
		}
		for(int i = 0; i<temp.length; i++ ){
			if(temp[i] != null){
				for(int j = 0; j<temp[i].getDocNames().size();j++)
					add(temp[i].getDocNames().get(j).getDocName(), temp[i].getName());
			}
		}
	}
	public void remove(String key) {
		int search = 0;
		Term dataT = new Term("RESERVED");
		int hashVal = this.hash(key,search);
		while(hashVal<tableSize){
			if(this.table[hashVal] == null){
				return;
			}
			if(this.table[hashVal].getName().equals(key)){
				this.table[hashVal] = dataT;
				return;
			}
			search++;
			hashVal = this.hash(key,search);
		}

	}
	public int getNumElements() {
		return this.numElements;
	}

	public Term get(String word, Boolean printP){
		int search = 0;
		int hash = this.hash(word, search);
		while(hash<tableSize){
				if(table[hash] == null){
					return null;
				}
				if (table[hash].getName().equals(word)) {
					return this.table[hash];			
				}
			search++;
			hash = this.hash(word, search);


		}
		return null;
	}

	public boolean contains(String key) {
		int search = 0;
		int hash = this.hash(key,search);
		while(hash<tableSize){
			if(this.table[hash].equals(key)){
				return true;
			}
			search++;
			hash = this.hash(key,search);
		}
		return false;
	}

	public void printTable(){
		for(int i = 0; i<tableSize; i++){
			if(table[i] != null && !table[i].getName().equals("RESERVED"))
				System.out.println(table[i].getName());
		}
		System.out.println();
	}


}