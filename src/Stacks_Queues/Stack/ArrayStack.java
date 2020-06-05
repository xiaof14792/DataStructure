package Stacks_Queues.Stack;


//动态数组作为底层实现栈，从尾部添加元素和删除元素
//复杂度均为O(1)
public class ArrayStack<E> implements Stack<E> {
	private Array<E> array;

	public ArrayStack(int capacity) {
		array = new Array<>(capacity);
	}

	public ArrayStack() {
		array = new Array<>();
	}

	@Override
	public void push(E e) {
		array.addLast(e);
	}

	@Override
	public E pop() {
		return array.removeLast();
	}

	@Override
	public E peek() {
		return array.getLast();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	@Override
	public int getSize() {
		return array.getSize();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Stack: ");
		sb.append('[');
		for (int i = 0; i < getSize(); i++) {
			sb.append(array.get(i));
			if (i != getSize() - 1) {
				sb.append(',');
			}
		}
		sb.append("] top");
		return sb.toString();
	}

	public static void main(String[] args) {
		ArrayStack<Integer> arrayStack = new ArrayStack<>();

		for (int i = 0; i < 5; i++) {
			arrayStack.push(i);
			System.out.println(arrayStack);
		}

		arrayStack.pop();
		System.out.println(arrayStack);
	}

}