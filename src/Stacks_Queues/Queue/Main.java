package Stacks_Queues.Queue;

import java.util.Random;

public class Main {
	private static final int opCount = 100000;

	public static double testQueue(Queue<Integer> queue, int opCount) {
		double time1 = System.nanoTime();

		Random random = new Random();
		for (int i = 0; i < opCount; i++) {
			queue.enqueue(random.nextInt(Integer.MAX_VALUE));
		}
		for (int i = 0; i < opCount; i++) {
			queue.dequeue();
		}
		double time2 = System.nanoTime();

		return (time2 - time1) / 1000000000.0;
	}

	public static void main(String[] args) {
		ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
		System.out.println("ArrayQueue: " + testQueue(arrayQueue, opCount) + "s");

		LoopQueue<Integer> loopQueue = new LoopQueue<Integer>();
		System.out.println("LoopQueue: " + testQueue(loopQueue, opCount) + "s");

		LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();
		System.out.println("LinkedListQueue: " + testQueue(linkedListQueue, opCount));
	}
}
