package Heap_PriorityQueue;

/**
 * 基于堆实现的优先队列
 * 
 * @author 14792
 *
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
	private MaxHeap<E> maxHeap;

	public PriorityQueue() {
		maxHeap = new MaxHeap<>();
	}

	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		maxHeap.add(e);
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return maxHeap.extractMax();
	}

	@Override
	public E getFront() {
		// TODO Auto-generated method stub
		return maxHeap.findMax();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return maxHeap.getSize();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return maxHeap.isEmpty();
	}

}
