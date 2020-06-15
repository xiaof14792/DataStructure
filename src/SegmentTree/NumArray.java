package SegmentTree;

class NumArray {
	public interface Merger<E> {
		E merge(E a, E b);
	}

	public class SegmentTree<E> {
		private E[] data;
		private E[] tree;
		private Merger<E> merger;

		@SuppressWarnings("unchecked")
		public SegmentTree(E[] arr, Merger<E> merger) {
			data = (E[]) new Object[arr.length];
			for (int i = 0; i < arr.length; i++) {
				this.data[i] = arr[i];
			}

			// 元素共有n个，线段树最少需要4n个空间
			tree = (E[]) new Object[4 * arr.length];

			this.merger = merger;
			buildSegmentTree(0, 0, data.length - 1);
		}

		// 生成线段树
		private void buildSegmentTree(int treeIndex, int l, int r) {
			if (l == r) {
				tree[treeIndex] = data[l];
				return;
			}

			int leftTreeIndex = leftChild(treeIndex);
			int rightTreeIndex = rightChild(treeIndex);

			int mid = l + (r - l) / 2;

			buildSegmentTree(leftTreeIndex, l, mid);
			buildSegmentTree(rightTreeIndex, mid + 1, r);

			tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
		}

		public E query(int queryL, int queryR) {
			if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
				throw new IllegalArgumentException("Index is illegal");
			}

			return query(0, 0, data.length - 1, queryL, queryR);
		}

		private E query(int treeIndex, int l, int r, int queryL, int queryR) {
			if (queryL == l && queryR == r) {
				return tree[treeIndex]; // 叶节点了
			}

			int mid = l + (r - l) / 2;
			int leftTreeIndex = leftChild(treeIndex);
			int rightTreeIndex = rightChild(treeIndex);

			if (queryL >= mid + 1) {
				return query(rightTreeIndex, mid + 1, r, queryL, queryR);
			} else if (queryR <= mid) {
				return query(leftTreeIndex, l, mid, queryL, queryR);
			}

			E leftRes = query(leftTreeIndex, l, mid, queryL, mid);
			E rightRes = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

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

		// 递归方法，更新节点的值后更新树节点的值
		private void set(int treeIndex, int l, int r, int index, E e) {
			if (l == r) {
				tree[treeIndex] = e; // 已经是叶子节点了，就是data[]中的值
				return;
			}

			int mid = l + (r - l) / 2;

			int leftTreeIndex = leftChild(treeIndex);
			int rightTreeIndex = rightChild(treeIndex);

			if (index >= mid + 1) {
				set(rightTreeIndex, mid + 1, r, index, e);
			} else if (index <= mid) {
				set(leftTreeIndex, l, mid, index, e);
			}

			// 这一步搞忘了啊啊啊啊啊啊啊啊啊啊
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
			sb.append("]");
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

	private SegmentTree<Integer> segmentTree;

	public NumArray(int[] nums) {
		if (nums.length != 0) {
			Integer[] data = new Integer[nums.length];
			for (int i = 0; i < nums.length; i++) {
				data[i] = nums[i];
			}

			segmentTree = new SegmentTree<>(data, new Merger<Integer>() {

				@Override
				public Integer merge(Integer a, Integer b) {
					return a + b;
				}
			});
		}
	}

	public void update(int i, int val) {
		if (segmentTree == null) {
			throw new IllegalArgumentException("Error");
		}
		segmentTree.set(i, val);
	}

	public int sumRange(int i, int j) {
		if (segmentTree == null) {
			throw new IllegalArgumentException("Error");
		}
		return segmentTree.query(i, j);
	}
}