package Arrays;

public class Main {

	public static void main(String[] args) {
		// int[] arr = new int[20];
		// for (int i = 0; i < arr.length; i++) {
		// arr[i] = i;
		// }
		//
		// int[] scores = new int[] { 100, 99, 98 };
		// for (int score : scores) {
		// System.out.println(score);
		// }
		//
		// scores[1] = 90;
		// for (int i = 0; i < scores.length; i++) {
		// System.out.println(scores[i]);
		// }

		Array<Integer> array = new Array<>();
		for (int i = 0; i < 10; i++) {
			array.addLast(i);
		}
		System.out.println(array);

		array.add(1, 100);
		System.out.println(array);

		array.addFirst(-1);
		System.out.println(array);

		array.remove(2);
		System.out.println(array);

		array.removeElement(4);
		System.out.println(array);

		array.removeFirst();
		System.out.println(array);
	}

}