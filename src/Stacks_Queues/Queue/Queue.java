package Stacks_Queues.Queue;

/**
 * 先进先出
 * @param <E>
 */
public interface Queue<E> {
	void enqueue(E e);

	E dequeue();

	E getFront();

	int getSize();

	boolean isEmpty();

}