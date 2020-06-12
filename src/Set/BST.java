package Set;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

	private class Node {
		E e;
		Node left, right;

		public Node(E e) {
			// TODO Auto-generated constructor stub
			this.e = e;
			left = null;
			right = null;
		}
	}

	private Node root;
	private int size;

	public BST() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	// 向二分搜索树中添加元素
	public void add(E e) {
		// 注意 注意: 在调用函数传入值时/会修改参数的内部值属性如属性和集合中的值，但参数的地址不会发生改变，一些简单的类型更不会被修改值，
		root = add(root, e);
	}

	// 向以node为根节点的二分搜索树中插入元素e，递归算法
	// 返回插入新节点后的子二分搜索树的根节点 （返回节点对简化递归算法逻辑很有效果）
	private Node add(Node node, E e) {
		if (node == null) {
			node = new Node(e);
			// 总是搞忘记 size++;
			size++;
			return node;
		}

		if (e.compareTo(node.e) < 0) {
			node.left = add(node.left, e);
		} else if (e.compareTo(node.e) > 0) {
			node.right = add(node.right, e);
		}

		return node;
	}

	/**
	 * 二分搜索树的添加（非递归实现）
	 * 
	 * @param e
	 */
	public void addNTR(E e) {
		if (root == null) {
			root = new Node(e);
		}

	}

	// 看二分搜索树中是否包含元素e
	public boolean contains(E e) {
		return contains(root, e);
	}

	// 看二分搜索树中是否包含元素e，递归算法
	public boolean contains(Node node, E e) {
		if (node == null) {
			return false;
		}

		if (e.compareTo(node.e) == 0) {
			return true;
		} else if (e.compareTo(node.e) < 0) {
			return contains(node.left, e);
		} else {
			// 正确的代码应该是这样的，最后一个条件是else，没有if，因为只有这最后一个条件了
			return contains(node.right, e);
		}
	}

	// 二分搜索树的前序遍历
	public void preOrder() {
		preOrder(root);
	}

	// 前序遍历以root为根的二分搜索树，递归算法
	private void preOrder(Node root) {
		if (root == null) {
			return;
		}

		System.out.print(root.e + ", ");
		preOrder(root.left);
		preOrder(root.right);
	}

	// 对二分搜索树进行前序遍历（不采用递归算法）
	public void preOrderNTR() {
		if (root == null) {
			return;
		}

		Stack<Node> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			Node node = stack.pop();
			System.out.print(root.e + ", ");

			if (node.right != null) {
				stack.push(node.right);
			}

			if (node.left != null) {
				stack.push(node.left);
			}

		}
	}

	// 二分搜索树的中序遍历
	public void inOrder() {
		inOrder(root);
	}

	// 中序遍历以root为根的二分搜索树，递归算法
	private void inOrder(Node root) {
		if (root == null) {
			return;
		}

		inOrder(root.left);
		System.out.print(root.e + ", ");
		inOrder(root.right);
	}

	// 二分搜索树的后序遍历
	public void postOrder() {
		postOrder(root);
	}

	// 后序遍历以root为根的二分搜索树，递归算法
	private void postOrder(Node root) {
		if (root == null) {
			return;
		}

		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.e + ", ");
	}

	// 二分搜索树的层序遍历
	public void levelOrder() {
		levelOrder(root);
	}

	// 对二分搜索树进行层序遍历
	public void levelOrder(Node root) {
		if (root == null) {
			return;
		}

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			Node node = queue.remove();
			System.out.print(node.e + ", ");

			if (node.left != null) {
				queue.add(node.left);
			}

			if (node.right != null) {
				queue.add(node.right);
			}
		}
	}

	// 寻找二分搜索树的最小元素
	private E minimum() {
		if (size == 0) {
			throw new IllegalArgumentException("BST is empty.");
		}

		return minimun(root).e;
	}

	// 返回以root 为根节点的二分搜索树的最小值的节点
	private Node minimun(Node root) {
		if (root.left == null) {
			return root;
		}

		return minimun(root.left);
	}

	// 寻找二分搜索树的最大元素
	private E maximum() {
		if (size == 0) {
			throw new IllegalArgumentException("BST is empty.");
		}

		return maximum(root).e;
	}

	// 返回以root为根节点的二分搜索树的最大值的节点
	private Node maximum(Node root) {
		if (root.right == null) {
			return root;
		}

		return maximum(root.right);
	}

	// 删除二分搜索树中的最小节点的值
	public E removeMin() {
		E ret = minimum();
		// 要更新root节点啊，方法不能改变原来传入的参数
		root = removeMin(root);
		return ret;
	}

	// 删除以 root为根节点的二分搜索树中的最小值，也是递归调用方法
	private Node removeMin(Node node) {
		if (node.left == null) {
			Node right = node.right;
			// 囊括了 右节点为空和不为空的两种情况
			node.right = null;
			size--;
			return right;
		}

		node.left = removeMin(node.left);
		return node;
	}

	// 删除二分搜索树中的最大节点的值
	public E removeMax() {
		E ret = maximum();
		root = removeMax(root);
		return ret;
	}

	// 删除以root为根节点的二分搜索树中的最大值
	private Node removeMax(Node node) {
		if (node.right == null) {
			Node left = node.left;
			node.left = null;
			size--;
			return left;
		}

		node.right = removeMax(node.right);
		return node;
	}

	// 从二分搜索树中删除元素为e的节点
	public void remove(E e) {
		root = remove(root, e);
	}

	// 删除以node为根的二分搜索树中元素为e的节点，递归算法
	// 返回删除节点后新的二分搜索树的根
	private Node remove(Node node, E e) {
		if (node == null) {
			return null;
		}

		if (e.compareTo(node.e) < 0) {
			node.left = remove(node.left, e);
		} else if (e.compareTo(node.e) > 0) {
			node.right = remove(node.right, e);
		} else { // e.compareTo(node.e) == 0

			// 待删除节点左子树为空（包括了左、右子树都为空的情况）
			if (node.left == null) {
				Node right = node.right;
				size--;
				node.right = null;
				return right;
			}

			// 待删除节点右子树为空
			if (node.right == null) {
				Node left = node.left;
				size--;
				node.left = null;
				return left;
			}

			// 左右子树都不为空
			Node successor = minimun(node.right);

			successor.right = removeMin(node.right);
			successor.left = node.left;

			node.left = node.right = null;
			return successor;
		}

		return node;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		generateBSTString(root, 0, sb);
		return sb.toString();
	}

	private void generateBSTString(Node root, int depth, StringBuilder sb) {
		if (root == null) {
			sb.append(generateDepthString(depth) + "null\n");
			return;
		}

		sb.append(generateDepthString(depth) + root.e + "\n");
		generateBSTString(root.left, depth + 1, sb);
		generateBSTString(root.right, depth + 1, sb);

	}

	private String generateDepthString(int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append("--");
		}
		return sb.toString();
	}

}
