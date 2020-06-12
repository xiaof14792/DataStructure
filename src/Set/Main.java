package Set;

import java.util.ArrayList;

public class Main {
	private static double testSet(Set<String> set, String fileName) {
		long startTime = System.nanoTime();

		System.out.println("Pride and Prejudice");

		ArrayList<String> words1 = new ArrayList<>();
		FileOperation.readFile("pride-and-prejudice.txt", words1);
		System.out.println("Total words: " + words1.size());

		for (String string : words1) {
			set.add(string);
		}
		System.out.println("Total different words: " + set.getSize());

		long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}

	public static void main(String[] args) {
		BSTSet<String> bstSet = new BSTSet<String>();
		System.out.println("BSTSet time:" + testSet(bstSet, "") + " s");

		System.out.println();

		LinkedListSet<String> linkedListSet = new LinkedListSet<String>();
		System.out.println("LinkedListSet time: " + testSet(linkedListSet, "") + " s");
	}
}