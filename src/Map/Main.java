package Map;

import java.util.ArrayList;

public class Main {
	private static double testMap(Map<String, Integer> map) {
		long startTime = System.nanoTime();

		System.out.println("Pride and Prejudice");

		ArrayList<String> words1 = new ArrayList<>();
		FileOperation.readFile("pride-and-prejudice.txt", words1);
		System.out.println("Total words: " + words1.size());

		for (String word : words1) {
			if (map.contains(word)) {
				map.set(word, map.get(word) + 1);
			} else {
				map.add(word, 1);
			}
		}

		System.out.println("Total different words: " + map.getSize());
		System.out.println("Frquency of PRIDE: " + map.get("pride"));
		System.out.println("Frquency of PREJUDICE: " + map.get("prejudice"));

		long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}

	public static void main(String[] args) {
		BSTMap<String, Integer> bstMap = new BSTMap<String, Integer>();
		System.out.println("BSTMap : " + testMap(bstMap) + " s");

		System.out.println();

		LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<String, Integer>();
		System.out.println("LinkedListMap : " + testMap(linkedListMap) + " s");
	}
}
