package Map;

public interface Map<K, V> {
	void add(K k, V v);

	V remove(K k);

	V get(K k);

	int getSize();

	boolean isEmpty();

	boolean contains(K k);

	void set(K k, V v);
}
