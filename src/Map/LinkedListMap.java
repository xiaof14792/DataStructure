package Map;

public class LinkedListMap<K, V> implements Map<K, V> {

	private class Node {
		private K key;
		private V value;
		private Node next;

		public Node(K key, V value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node() {
			this(null, null, null);
		}
	}

	private Node dummyHead;
	private int size;

	/**
	 * 构造函数
	 */
	public LinkedListMap() {
		dummyHead = new Node();
		size = 0;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 根据key来获取相应的Node，在多个方法中需要用到
	 * 
	 * @param key
	 * @return
	 */
	public Node getNode(K key) {
		Node cur = dummyHead.next;
		while (cur != null) {
			if (cur.key.equals(key)) {
				return cur;
			}
			cur = cur.next;
		}
		return null;
	}

	@Override
	public V get(K key) {
		Node node = getNode(key);
		return node == null ? null : node.value;
	}

	@Override
	public boolean contains(K key) {
		return getNode(key) != null;
	}

	@Override
	public void set(K key, V value) {
		Node node = getNode(key);
		if (node != null) {
			node.value = value;
		} else {
			throw new IllegalArgumentException("key is not exits.");
		}
	}

	@Override
	public void add(K key, V value) {
		// 从链表头部添加元素
		dummyHead.next = new Node(key, value, dummyHead.next);
		size++;
	}

	@Override
	public V remove(K key) {
		Node pre = dummyHead;

		while (pre.next != null) {
			if (pre.next.key.equals(key)) {
				Node delNode = pre.next;
				pre.next = delNode.next;
				delNode.next = null;
				size--;
				return delNode.value;
			}
		}

		return null;
	}

}