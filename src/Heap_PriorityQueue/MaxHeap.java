package Heap_PriorityQueue;

/**
 * 支持增、删、查、改等操作，只对堆顶的元素有作用 -->主要通过上浮、下沉操作维护最大堆数据结构
 * 
 * @author 14792
 *
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {
	private Array<E> data;

	public MaxHeap(int size) {
		data = new Array<>(size);
	}

	public MaxHeap() {
		data = new Array<>();
	}

	// 使用Heapify方式初始化二叉堆, **快速初始化堆的一种方法，减少工作量提高性能**
	public MaxHeap(E[] arr) {
		data = new Array<>(arr);
		// 初始化最大堆 --> **从最后一个不是叶子节点的节点一直到根节点进行下沉操作**
		for (int i = (arr.length - 1) / 2; i >= 0; i--) {
			siftDown(i);
		}
	}

	// 返回堆中的元素个数
	public int getSize() {
		return data.getSize();
	}

	// 返回布尔值，表示堆中是否为空
	public boolean isEmpty() {
		return data.isEmpty();
	}

	// 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
	public int parent(int index) {
		return (index - 1) / 2;
	}

	// 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子的索引
	private int leftChild(int index) {
		return index * 2 + 1;
	}

	// 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子的索引
	private int rightChild(int index) {
		return index * 2 + 2;
	}

	// 向堆中添加元素
	public void add(E e) {
		data.addLast(e);
		siftUp(data.getSize() - 1);
	}

	// 上浮操作
	private void siftUp(int k) {
		while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
			data.swap(k, parent(k));
			k = parent(k);
		}
	}

	// 查找堆中的最大元素
	public E findMax() {
		if (data.getSize() == 0) {
			throw new IllegalArgumentException("can not find max when heap is empty.");
		}
		return data.getFirst();
	}

	// 删除堆中的最大元素
	public E extractMax() {
		E ret = findMax();

		data.swap(0, data.getSize() - 1);
		data.removeLast();
		siftDown(0);
		return ret;
	}

	// 下沉操作
	private void siftDown(int k) {
		// 三部曲： 首先看有没有孩子节点---》再看孩子节点中的最大的哪个节点
		// ---》如果孩子节点大于当前节点（满足条件），则交换元素，，，，后面循环
		while (leftChild(k) < data.getSize()) {
			int j = leftChild(k);

			if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
				j = rightChild(k);
			}
			// data.get(j) 是leftChild、rightChild中的最大值

			// 需要调换顺序
			if (data.get(k).compareTo(data.get(j)) < 0) {
				data.swap(k, j);
				k = j;
			} else
				// 如果不需要调换顺序，就要跳出while逻辑块，不然就会一直在while逻辑里面出不来
				break;

		}
	}

	/**
	 * 取出堆中的最大元素，并且替换成元素e
	 * 
	 * @param e
	 * @return
	 */
	public E replace(E e) {
		E max = findMax();

		data.set(0, e);
		siftDown(0);
		return max;
	}

}
