package Stacks_Queues.Queue;

/**
 * 底层实现还是用的数组（动态扩展和缩容） ，** 使用了头指针 、尾指针 头指针指向第一个元素， 尾指针指向第一个为null的位置**
 * 入队操作和出队操作都是O(1)的复杂度
 * 
 * @author 14792
 *
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {
	private E[] data;
	private int front, tail;
	private int size;

	public LoopQueue(int capacity) {
		data = (E[]) new Object[capacity + 1];
	}

	public LoopQueue() {
		this(10);
	}

	@Override
	public void enqueue(E e) {
		if (size == getCapacity()) {
			resize(getCapacity() * 2);
		}

		data[tail] = e;
		tail = (tail + 1) % data.length;
		size++;
	}

	private void resize(int newCapacity) {
		E[] newData = (E[]) new Object[newCapacity + 1];

		for (int i = 0; i < size; i++) {
			newData[i] = data[(front + i) % data.length];
		}
		data = newData;
		front = 0;
		tail = size;
	}

	@Override
	public E dequeue() {
		if (size == 0) {
			throw new IllegalArgumentException("LoopQueue is empty, can not dequeue.");
		}

		E ret = data[front];
		data[front] = null;
		// 这里的错误改正了
		front = (front + 1) % data.length;
		size--;

		if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
			resize(getCapacity() / 2);
		}
		return ret;
	}

	@Override
	public E getFront() {
		if (isEmpty()) {
			throw new IllegalArgumentException("LoopQueue is empty.");
		}
		return data[front];
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	public int getCapacity() {
		// 循环队列的容量必须用数组长度减1
		return data.length - 1;
	}

	@Override
	public boolean isEmpty() {
		// 判断队列是否为空： 头下标 等于 尾下标
		return front == tail;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("LoopQueue: capacity = %d, size = %d \n", getCapacity(), getSize()));
		sb.append("front [");
		for (int i = front; i != tail; i = (i + 1) % data.length) {
			sb.append(data[i]);
			if ((i + 1) % data.length != tail) {
				sb.append(',');
			}
		}
		sb.append("] tail");
		return sb.toString();
	}

	public static void main(String[] args) {
		LoopQueue<Integer> loopQueue = new LoopQueue<Integer>();

		for (int i = 1; i < 10; i++) {
			loopQueue.enqueue(i);
			if (i % 3 == 0) {
				loopQueue.dequeue();
			}
			System.out.println(loopQueue);
		}
	}

}
