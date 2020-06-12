package Set;

public class LinkedList<E> {
	private class Node {
		E e;
		Node next;

		public Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}

		public Node(E e) {
			this(e, null);
		}

		public Node() {
			this(null);
		}

		@Override
		public String toString() {
			return e.toString();
		}
	}

	// 虚拟头节点， 大大减少了链表各种操作的复杂逻辑
	private Node dummyHead;
	private int size;

	public LinkedList() {
		dummyHead = new Node(null, null);
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return size;
	}

	// 在链表指定下标处插入元素
	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Add failed, index is wrong.");
		}

		Node pre = dummyHead;
		for (int i = 0; i < index; i++) {
			pre = pre.next;
		}
		Node node = new Node(e, pre.next);
		pre.next = node;
		size++;
	}

	// 在链表头部添加元素
	public void addFirst(E e) {
		add(0, e);
	}

	// 在链表末尾添加元素
	public void addLast(E e) {
		add(size, e);
	}

	// 获取指定index下标处的元素
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("index is out of bounds");
		}
		Node cur = dummyHead.next;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.e;
	}

	// 获取链表的头元素
	public E getFirst() {
		return get(0);
	}

	// 获取链表的尾元素
	public E getLast() {
		return get(size - 1);
	}

	// 修改index下标处的元素的值
	public void set(int index, E e) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Set failed, index is wrong.");
		}
		Node cur = dummyHead.next;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		cur.e = e;
	}

	// 链表中是否含有等于某个元素的值
	public boolean contains(E e) {
		Node cur = dummyHead.next;
		while (cur != null) {
			if (cur.e.equals(e)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	// 删除某个下标的元素，返回删除的元素
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Remove failed, index is wrong.");
		}
		Node pre = dummyHead;
		for (int i = 0; i < index; i++) {
			pre = pre.next;
		}
		Node delNode = pre.next;
		pre.next = delNode.next;
		delNode.next = null;

		// 总是把这一步搞忘了
		size--;
		return delNode.e;
	}

	// 删除链表中第一个元素，返回删除的元素
	public E removeFirst() {
		return remove(0);
	}

	// 删除链表中最后一个元素，返回删除的元素
	public E removeLast() {
		return remove(size - 1);
	}

	// 从链表中删除元素e
	public void removeElement(E e) {
		Node pre = dummyHead;
		while (pre.next != null) {
			if (pre.next.e.equals(e)) {
				// 删除元素操作
				Node delNode = pre.next;
				pre.next = delNode.next;
				delNode.next = null;
				size--;
				break;
			}
			pre = pre.next;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// Node cur = dummyHead.next;
		// while (cur != null) {
		// sb.append(cur + "->");
		// cur = cur.next;
		// }
		for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
			sb.append(cur + "->");
		}
		sb.append("NULL");
		return sb.toString();
	}

	public static void main(String[] args) {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			linkedList.add(i, i);
		}
		System.out.println(linkedList);

		System.out.println(linkedList.size);
		linkedList.add(5, 100);
		System.out.println(linkedList);

		// linkedList.addLast(10);
		// System.out.println(linkedList);
		//
		// linkedList.addFirst(500);
		// System.out.println(linkedList);
	}

}
