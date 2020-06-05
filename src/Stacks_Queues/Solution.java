package Stacks_Queues;

class Solution {

	class Array<E> {

		private E[] data;
		private int size;

		// 构造函数，传入数组的容量capacity构造Array
		@SuppressWarnings("unchecked")
		public Array(int capacity) {
			data = ((E[]) new Object[capacity]);
			size = 0;
		}

		// 无参数的构造函数，默认capacity = 10
		public Array() {
			this(10);
		}

		// 获取数组中元素个数
		public int getSize() {
			return size;
		}

		// 获取数组的容量
		public int getCapacity() {
			return data.length;
		}

		// 判断数组是否为空
		public boolean isEmpty() {
			return size == 0;
		}

		// 向所有元素后添加一个新元素
		public void addLast(E e) {
			// if (size == data.length)
			// throw new IllegalArgumentException("AddLast is failed.Array is full.");
			// data[size] = e;
			// size++;

			add(size, e);
		}

		// 向所有元素前添加一个新元素
		public void addFirst(E e) {
			add(0, e);
		}

		// 在第index处插入一个新元素
		public void add(int index, E e) {
			if (index < 0 || index > size)
				throw new IllegalArgumentException("Add failed. Required index: " + "index >= 0 and index <= size");
			if (size == data.length)
				// 扩容
				resize(2 * data.length);

			for (int i = size - 1; i >= index; i--) {
				data[i + 1] = data[i];
			}

			data[index] = e;
			size++;
		}

		// 更改数组的容量
		private void resize(int newCapacity) {
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) new Object[newCapacity];
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}

		// 获取index位置的元素
		public E get(int index) {
			if (index < 0 || index > size)
				throw new IllegalArgumentException("Required index: " + "index >= 0 and index <= size");
			return data[index];
		}

		// 获取数组中最后一个元素
		public E getLast() {
			return get(size - 1);
		}

		// 获取数组中第一个元素
		public E getFirst() {
			return get(0);
		}

		// 修改index位置的元素
		public void set(int index, E e) {
			if (index < 0 || index >= size)
				throw new IllegalArgumentException("Required index: " + "index >= 0 and index <= size - 1");
			data[index] = e;
		}

		// 查找数组中是否有元素e
		public boolean contains(int e) {
			for (int i = 0; i < size; i++) {
				if (data[i].equals(e)) {
					return true;
				}
			}
			return false;
		}

		// 查找数组中元素e所在的索引，如果不存在e，则返回-1
		public int find(E e) {
			for (int i = 0; i < size; i++) {
				if (data[i].equals(e)) {
					return i;
				}
			}
			return -1;
		}

		// 从数组中删除index处的元素，返回删除的元素
		public E remove(int index) {
			if (index < 0 || index > size) {
				throw new IllegalArgumentException("Required index: index >= 0 and index <= size.");
			}
			E ret = data[index];
			for (int i = index + 1; i < size; i++) {
				data[i - 1] = data[i];
			}
			size--; // 总是搞忘了更新size变量
			data[size] = null;
			// 缩容， lazy模式，减少复杂度震荡
			if (size == data.length / 4 && data.length / 2 != 0) {
				resize(data.length / 2);
			}
			return ret;
		}

		// 删除数组第一个元素， 返回删除的元素
		public E removeFirst() {
			return remove(0);
		}

		// 删除数组最后一个元素，返回删除的元素
		public E removeLast() {
			return remove(size - 1);
		}

		// 从数组中删除元素e
		public void removeElement(E e) {
			int i = find(e);
			if (i != -1) {
				remove(i);
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("Array:capacity = %d, size = %d\n", data.length, size));
			sb.append('[');
			for (int i = 0; i < size; i++) {
				sb.append(data[i]);
				if (i != size - 1) {
					sb.append(',');
				}
			}
			sb.append(']');
			return sb.toString();
		}

	}

	class ArrayStack<E> implements Stack<E> {
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

		public int getCapacity() {
			return array.getCapacity();
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

	}

	interface Stack<E> {
		void push(E e);

		E pop();

		E peek();

		boolean isEmpty();

		int getSize();
	}

	/*
	 * 
	 */
	public boolean isValid(String s) {
		ArrayStack<Character> stack = new ArrayStack<>();

		int length = s.length();

		for (int i = 0; i < length; i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else {
				if (stack.getSize() == 0) {
					return false;
				}
				if (c == ')' && stack.pop() != '(') {
					return false;
				}
				if (c == ']' && stack.pop() != '[') {
					return false;
				}
				if (c == '}' && stack.pop() != '{') {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}

	public static void main(String[] args) {
		System.out.println(new Solution().isValid("{{[]()}[]}"));
		System.out.println(new Solution().isValid("{{[]()}[]"));
	}
}