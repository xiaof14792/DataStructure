package BST;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	private static BST<Integer> binarySearchTree;

	public static void main(String[] args) {
		int[] arr = new int[] { 5, 3, 6, 8, 4, 2, 9 };

		binarySearchTree = new BST<Integer>();
		for (int i = 0; i < arr.length; i++) {
			binarySearchTree.add(arr[i]);
		}
		System.out.println("size: " + binarySearchTree.size());

		System.out.println("4 的 predecessor: " + binarySearchTree.predecessor(4));

		System.out.println("2 的 successor: " + binarySearchTree.successor(2));

		System.out.println("7 的 floor: " + binarySearchTree.floor(7));

		System.out.println("7 的 ceil: " + binarySearchTree.ceil(7));

		// test();

		// testPreInPostOrder(binarySearchTree);

		// testRemoveMax();
	}

	private static void test() {
		binarySearchTree.add(1);
		System.out.println(binarySearchTree);
		System.out.println("size: " + binarySearchTree.size());

		binarySearchTree.remove(3);
		System.out.println(binarySearchTree);
		System.out.println("size: " + binarySearchTree.size());

		binarySearchTree.removeMax();
		System.out.println(binarySearchTree);
	}

	private static void testOrders(BST<Integer> binarySearchTree) {
		binarySearchTree.preOrder();
		System.out.println();

		binarySearchTree.preOrderNTR();
		System.out.println();

		binarySearchTree.levelOrder();
		System.out.println();

		System.out.println(binarySearchTree);

		binarySearchTree.inOrder();
		System.out.println();

		binarySearchTree.postOrder();
		System.out.println();
	}

	private static void testRemoveMax() {
		BST<Integer> binarySearchTree = new BST<Integer>();

		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			binarySearchTree.add(random.nextInt(10000));
		}

		ArrayList<Integer> arrayList = new ArrayList<>();
		while (!binarySearchTree.isEmpty()) {
			arrayList.add(binarySearchTree.removeMax());
		}

		System.out.println(arrayList);

		for (int i = 0; i < arrayList.size() - 1; i++) {
			if (arrayList.get(i) < arrayList.get(i + 1)) {
				throw new IllegalArgumentException("remove max false.");
			}
		}

		System.out.println("Remove max test success!");

	}
}
