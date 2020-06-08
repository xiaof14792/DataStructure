package Stacks_Queues.Queue;

import Stacks_Queues.Stack.Array;

// 动态数组作为底层实现队列   从尾部添加元素  从头部删除元素
// 添加元素  O(1)  删除元素  O(n)   
// 可以使用LoopQueue进行优化队列的实现，复杂度均为O(1)
public class ArrayQueue<E> implements Queue<E> {
	private Array<E> array;

	public ArrayQueue(int capacity) {
		array = new Array<>(capacity);
	}

	public ArrayQueue() {
		array = new Array<>();
	}

	@Override
	public void enqueue(E e) {
		array.addLast(e);
	}

	@Override
	public E dequeue() {
		return array.removeFirst();
	}

	@Override
	public E getFront() {
		return array.getFirst();
	}

	@Override
	public int getSize() {
		return array.getSize();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	public int getcapacity() {
		return array.getCapacity();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Queue: ");
		sb.append("front [");
		for (int i = 0; i < getSize(); i++) {
			sb.append(array.get(i));
			if (i != getSize() - 1) {
				sb.append(',');
			}
		}
		sb.append("] tail");
		return sb.toString();
	}

	public static void main(String[] args) {
		ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();

		for (int i = 1; i < 10; i++) {
			arrayQueue.enqueue(i);
			if (i % 3 == 0) {
				arrayQueue.dequeue();
			}
			System.out.println(arrayQueue);
		}
	}

}
