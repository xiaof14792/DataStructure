package SegmentTree;

/**
 * 区间固定，只考虑get,set 方法，初始化即生成线段树，**不考虑新增删除元素**
 * 
 * 有三个关键方法： buildSegment() query() set()-- 生成线段树 、查询操作、 更新操作
 * 
 * @author 14792
 *
 * @param <E>
 */
public class SegmentTree<E> {
	private E[] data; // 保存的原始数据
	private E[] tree; // 表示线段树的数组
	private Merger<E> merger;

	@SuppressWarnings("unchecked")
	public SegmentTree(E[] arr, Merger<E> merger) {
		data = (E[]) new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			this.data[i] = arr[i];
		}

		// 元素共有n个，线段树最少需要4n个空间「生成线段树是从上到下的，需要4n空间原因在此」
		tree = (E[]) new Object[4 * arr.length];

		this.merger = merger;
		buildSegmentTree(0, 0, data.length - 1);
	}

	// 生成线段树-- 递归算法，所有计算后的数据都保存到 tree[]数组中
	private void buildSegmentTree(int treeIndex, int l, int r) {
		if (l == r) {
			tree[treeIndex] = data[l];
			return;
		}

		int mid = l + (r - l) / 2;
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);

		buildSegmentTree(leftTreeIndex, l, mid);
		buildSegmentTree(rightTreeIndex, mid + 1, r);

		// 融合的操作都放在最后
		tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
	}

	// 查询某一区间计算后的值
	public E query(int queryL, int queryR) {
		if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
			throw new IllegalArgumentException("Index is illegal");
		}

		// 哪几个参数是在递归的过程中一直改变的 ，， 就放在方法参数里面
		return query(0, 0, data.length - 1, queryL, queryR);
	}

	// 查询 迭代算法
	private E query(int treeIndex, int l, int r, int queryL, int queryR) {
		// 左右两边下标均满足条件，直接返回tree中的值
		if (queryL == l && queryR == r) {
			return tree[treeIndex];
		}

		int mid = l + (r - l) / 2;
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);

		if (queryL > mid) {
			return query(rightTreeIndex, mid + 1, r, queryL, queryR);
		} else if (queryR <= mid) {
			return query(leftTreeIndex, l, mid, queryL, queryR);
		}

		// 左右两边均有分布
		E leftRes = query(leftTreeIndex, l, mid, queryL, mid);
		E rightRes = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

		// 融合放在最后面
		return merger.merge(leftRes, rightRes);
	}

	// 将index位置的值，更新为e
	public void set(int index, E e) {
		if (index < 0 || index >= data.length) {
			throw new IllegalArgumentException("Index is illegal.");
		}

		data[index] = e;
		set(0, 0, data.length - 1, index, e);
	}

	// 递归方法，更新节点的值后从叶子节点一直回溯到根节点更新值 *set方法更新的值保存在tree中，方法本身不需要返回*
	private void set(int treeIndex, int l, int r, int index, E e) {
		if (l == r) {
			tree[treeIndex] = e; // 已经是叶子节点了，更新叶子节点
			return;
		}

		int mid = l + (r - l) / 2;
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);

		if (index > mid) {
			set(rightTreeIndex, mid + 1, r, index, e);
		} else if (index <= mid) {
			set(leftTreeIndex, l, mid, index, e);
		}

		// 更新完叶子节点后，需从叶子节点回溯到父亲节点一直到根节点都进行更新
		tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
	}

	public int getSize() {
		return data.length;
	}

	public E get(int index) {
		if (index < 0 || index >= data.length) {
			throw new IllegalArgumentException("Index is out of bounds");
		}
		return data[index];
	}

	// 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子的节点的索引
	public int leftChild(int index) {
		return index * 2 + 1;
	}

	// 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子的节点的索引
	public int rightChild(int index) {
		return index * 2 + 2;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < tree.length; i++) {
			E e = tree[i];
			sb.append(e);
			if (i != tree.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
