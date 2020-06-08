package Stacks_Queues.Queue;

// 用链表实现队列， 从尾部添加元素，从头部删除元素
// 添加操作和删除操作 都是O(1)的复杂度  注意：需要添加头部和尾部指针！
public class LinkedListQueue<E> implements Queue<E> {
	private class Node {
		E e;
		Node next;

		public Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}

		@Override
		public String toString() {
			return e.toString();
		}
	}

	// 都是实心点
	private Node front, tail; // 初始化时，front和tail都为null，也是作为判断队列为空的依据
	private int size;

	public LinkedListQueue() {
		front = null;
		tail = null;
		size = 0;
	}

	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		if (tail == null) {
			tail = new Node(e, null);
			front = tail;
		} else {
			tail.next = new Node(e, null);
			tail = tail.next;
		}
		size++;
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		if (front == null) {
			throw new IllegalArgumentException("Queue is empty, can not dequeue");
		}
		Node ret = front;
		front = front.next;
		ret.next = null;
		if (front == null) {
			tail = null;
		}

		size--;
		return ret.e;
	}

	@Override
	public E getFront() {
		// TODO Auto-generated method stub
		return front.e;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Queue: front ");
		Node cur = front;
		while (cur != null) {
			sb.append(cur.e + "->");
			cur = cur.next;
		}
		sb.append("NUll");
		sb.append(" tail");
		return sb.toString();
	}

	public static void main(String[] args) {
		LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();
		for (int i = 0; i < 10; i++) {
			linkedListQueue.enqueue(i);
			if (i % 3 == 2) {
				linkedListQueue.dequeue();
			}
			System.out.println(linkedListQueue);
		}
	}

}
