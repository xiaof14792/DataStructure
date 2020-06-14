package Map;

import java.util.ArrayList;

/**
 * 实现思路： 最好的是 增--》查--》删--》改
 * 
 * @author 14792
 *
 * @param <K>
 * @param <V>
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

	private class Node {
		public K key;
		public V value;
		public Node left, right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
	}

	private Node root;
	private int size;

	public BSTMap() {
		root = null;
		size = 0;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void add(K key, V value) {
		root = add(root, key, value);
	}

	private Node add(Node node, K key, V value) {
		if (node == null) {
			node = new Node(key, value);
			size++;
			return node;
		}

		if (key.compareTo(node.key) < 0) {
			node.left = add(node.left, key, value);
		} else if (key.compareTo(node.key) > 0) {
			node.right = add(node.right, key, value);
		}
		// 变量名字一定要规范，避免歧义，不然容易出错且很难排除
		return node;
	}

	@Override
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.value;
	}

	/**
	 * 这个方法用的很多哦
	 * 
	 * @param node
	 *            根节点
	 * @param key
	 *            键值
	 * @return
	 */
	private Node getNode(Node node, K key) {

		if (node == null)
			return null;

		if (key.equals(node.key))
			return node;
		else if (key.compareTo(node.key) < 0)
			return getNode(node.left, key);
		else // if(key.compareTo(node.key) > 0)
			return getNode(node.right, key);
	}

	@Override
	public boolean contains(K key) {
		return getNode(root, key) != null;
	}

	@Override
	public void set(K key, V value) {
		Node node = getNode(root, key);
		if (node == null) {
			throw new IllegalArgumentException("key is not exits.");
		}
		node.value = value;
	}

	// 返回以root 为根节点的二分搜索树的最小值的节点（删除操作需要的基础方法）
	private Node minimun(Node node) {
		if (node.left == null) {
			return node;
		}

		return minimun(node.left);
	}

	// 删除以 root为根节点的二分搜索树中的最小值（删除操作需要的基础方法）
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

	// 从二分搜索树中删除元素为e的节点
	public V remove(K key) {
		Node delNode = getNode(root, key);
		if (delNode == null) {
			return null;
		}
		root = remove(root, key);
		return delNode.value;
	}

	// 删除以node为根的二分搜索树中键为key的节点，递归算法
	// 返回删除节点后新的二分搜索树的值
	// 两次分三种情况
	private Node remove(Node node, K key) {
		if (node == null) {
			return null;
		}

		if (key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
		} else if (key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
		} else { // key.compareTo(node.key) == 0

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

			// 左右子树都不为空，（用到了前面的查找最小、删除最小两个基础方法）
			Node successor = minimun(node.right);

			successor.right = removeMin(node.right);
			successor.left = node.left;

			node.left = node.right = null;
			return successor;
		}

		return node;
	}

	public static void main(String[] args) {

		System.out.println("Pride and Prejudice");

		ArrayList<String> words = new ArrayList<>();
		if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
			System.out.println("Total words: " + words.size());

			BSTMap<String, Integer> map = new BSTMap<String, Integer>();
			for (String word : words) {
				if (map.contains(word))
					map.set(word, map.get(word) + 1);
				else
					map.add(word, 1);
			}

			System.out.println("Total different words: " + map.getSize());
			System.out.println("Frequency of PRIDE: " + map.get("pride"));
			System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
		}

		System.out.println();
	}

}