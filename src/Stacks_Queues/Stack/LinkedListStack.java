package Stacks_Queues.Stack;

import Stacks_Queues.LinkedList.LinkedList;

//用链表实现栈，从头部添加元素，亦从头部删除元素
//复杂度均为O(1)
public class LinkedListStack<E> implements Stack<E> {
	private LinkedList<E> linkedList;

	public LinkedListStack() {
		linkedList = new LinkedList<E>();
	}

	@Override
	public void push(E e) {
		// TODO Auto-generated method stub
		linkedList.addFirst(e);
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		return linkedList.removeFirst();
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return linkedList.getFirst();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return linkedList.isEmpty();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return linkedList.getSize();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("LinkedListStack: size = %d\n", getSize()));
		sb.append(linkedList.toString());
		return sb.toString();
	}

	public static void main(String[] args) {
		LinkedListStack<Integer> linkedListStack = new LinkedListStack<Integer>();
		for (int i = 0; i < 5; i++) {
			linkedListStack.push(i);
			System.out.println(linkedListStack);
		}

		linkedListStack.pop();
		System.out.println(linkedListStack);
	}

}
