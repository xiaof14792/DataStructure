package LinkedList;

/**
 * 自己实现一个双链表
 * 
 * @author 14792
 *
 * @param <E>
 */
public class DoubleLinkedList<E> {
	private class Node {
		E e;
		Node pre;
		Node next;

		public Node(E e, Node pre, Node next) {
			this.e = e;
			this.pre = pre;
			this.next = next;
		}

		public Node(E e, Node next) {
			this(e, null, next);
		}

		public Node(E e) {
			this(e, null, null);
		}

		@Override
		public String toString() {
			return e.toString();
		}
	}

	private Node dummyHead;
	private Node dummyTail;
	private int size;

	public DoubleLinkedList() {
		dummyHead = new Node(null);
		dummyTail = new Node(null);
		dummyHead.next = dummyTail;
		dummyTail.pre = dummyHead;
		size = 0;
	}

	public void addFirst(E e) {
		add(0, e);
	}

	public void addLast(E e) {
		add(size, e);
	}

	public void add(int index, E e) {
		if (index < 0 || index > size)
			throw new IllegalArgumentException("index is wrong.");

		Node pre = dummyHead;
		for (int i = 0; i < index; i++)
			pre = pre.next;
		Node next = pre.next;

		Node insertNode = new Node(e, pre, next);
		pre.next = insertNode;
		next.pre = insertNode;

		size++;
	}

	public int getSize() {
		return size;
	}

	public void remove(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("remove failed, index is wrong.");

		Node pre = dummyHead;
		for (int i = 0; i < index; i++)
			pre = pre.next;
		Node next = pre.next.next;

		pre.next = next;
		next.pre = pre;
		size--;
	}

	public void removeFirst() {
		remove(0);
	}

	public void removeLast() {
		remove(size - 1);
	}

	public Node getNode(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("index is wrong.");

		Node pre = dummyHead;
		for (int i = 0; i < index; i++) {
			pre = pre.next;
		}

		return pre.next;
	}

	public E get(int index) {
		return getNode(index).e;
	}

	public E getFirst() {
		return get(0);
	}

	public E getLast() {
		return get(size - 1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		Node cur = dummyHead;
		while (cur.next.e != null) {
			sb.append(cur.next.toString());
			sb.append("->");

			cur = cur.next;
		}
		sb.append("NULL");

		return sb.toString();
	}

	public static void main(String[] args) {
		DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<Integer>();
		doubleLinkedList.addFirst(1);
		doubleLinkedList.addFirst(2);
		doubleLinkedList.addFirst(3);

		doubleLinkedList.addLast(4);

		System.out.println(doubleLinkedList.toString());
		System.out.println(doubleLinkedList.getSize());

		//
		doubleLinkedList.removeFirst();
		System.out.println(doubleLinkedList.toString());
		System.out.println(doubleLinkedList.getSize());

		doubleLinkedList.remove(1);
		System.out.println(doubleLinkedList.toString());
		System.out.println(doubleLinkedList.getSize());

		//
		System.out.println(doubleLinkedList.toString());
		System.out.println(doubleLinkedList.get(1));
	}
}
