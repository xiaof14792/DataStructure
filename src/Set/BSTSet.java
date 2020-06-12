package Set;

import java.util.ArrayList;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
	private BST<E> bst;

	public BSTSet() {
		bst = new BST<E>();
	}

	@Override
	public void add(E e) {
		// 二分搜索树本身就不支持添加重复元素
		bst.add(e);
	}

	@Override
	public void remove(E e) {
		// TODO Auto-generated method stub
		bst.remove(e);
	}

	@Override
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		return bst.contains(e);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return bst.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return bst.isEmpty();
	}

	public static void main(String[] args) {
		System.out.println("Pride and Prejudice");

		ArrayList<String> words1 = new ArrayList<>();
		FileOperation.readFile("pride-and-prejudice.txt", words1);
		System.out.println("Total words: " + words1.size());

		BSTSet<String> set1 = new BSTSet<String>();
		for (String string : words1) {
			set1.add(string);
		}
		System.out.println("Total different words: " + set1.getSize());
	}

}
