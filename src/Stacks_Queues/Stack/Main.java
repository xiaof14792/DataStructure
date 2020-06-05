package Stacks_Queues.Stack;

import java.util.Random;

public class Main {
	private static final int opCount = 1000000;

	public static double testStack(Stack<Integer> stack, int opCount) {
		double time1 = System.nanoTime();

		Random random = new Random();
		for (int i = 0; i < opCount; i++) {
			stack.push(random.nextInt(Integer.MAX_VALUE));
		}
		for (int i = 0; i < opCount; i++) {
			stack.pop();
		}
		double time2 = System.nanoTime();

		return (time2 - time1) / 1000000000.0;
	}

	public static void main(String[] args) {
		// 数量较大时ArrayStack性能更优，数量(小于10000时) LinkedListStack性能更优
		ArrayStack<Integer> arrayStack = new ArrayStack<Integer>();
		System.out.println("ArrayStack: " + testStack(arrayStack, opCount));

		LinkedListStack<Integer> linkedListStack = new LinkedListStack<Integer>();
		System.out.println("LinkedListStack: " + testStack(linkedListStack, opCount));
	}
}