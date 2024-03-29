class CustomMap<K, V> {

	private Entry<K, V>[] table; // Array of Entry.
	private int capacity = 4; // Initial capacity of HashMap

	static class Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;

		public Entry(K key, V value, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	@SuppressWarnings("unchecked")
	public CustomMap() {
		table = new Entry[capacity];
	}

	/**
	 * Method allows you put key-value pair in HashMapCustom. If the map already
	 * contains a mapping for the key, the old value is replaced. Note: method
	 * does not allows you to put null key though it allows null values.
	 * Implementation allows you to put custom objects as a key as well. Key
	 * Features: implementation provides you with following features:- >provide
	 * complete functionality how to override equals method. >provide complete
	 * functionality how to override hashCode method.
	 * 
	 * @param newKey
	 * @param data
	 */
	public void put(K newKey, V data) {
		if (newKey == null)
			return; // does not allow to store null.

		// calculate hash of key.
		int hash = hash(newKey);
		// create new entry.
		Entry<K, V> newEntry = new Entry<K, V>(newKey, data, null);

		// if table location does not contain any entry, store entry there.
		if (table[hash] == null) {
			table[hash] = newEntry;
		} else {
			Entry<K, V> previous = null;
			Entry<K, V> current = table[hash];

			while (current != null) { // we have reached last entry of bucket.
				if (current.key.equals(newKey)) {
					if (previous == null) { // node has to be insert on first of
											// bucket.
						newEntry.next = current.next;
						table[hash] = newEntry;
						return;
					} else {
						newEntry.next = current.next;
						previous.next = newEntry;
						return;
					}
				}
				previous = current;
				current = current.next;
			}
			previous.next = newEntry;
		}
	}

	/**
	 * Method returns value corresponding to key.
	 * 
	 * @param key
	 */
	public V get(K key) {
		int hash = hash(key);
		if (table[hash] == null) {
			return null;
		} else {
			Entry<K, V> temp = table[hash];
			while (temp != null) {
				if (temp.key.equals(key))
					return temp.value;
				temp = temp.next; // return value corresponding to key.
			}
			return null; // returns null if key is not found.
		}
	}

	/**
	 * Method implements hashing functionality, which helps in finding the
	 * appropriate bucket location to store our data. This is very important
	 * method, as performance of HashMapCustom is very much dependent on this
	 * method's implementation.
	 * 
	 * @param key
	 */
	private int hash(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}

}
