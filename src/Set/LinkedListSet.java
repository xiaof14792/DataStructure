package Set;
import java.util.ArrayList;

/**
 * 不需要元素是可比较的
 * 
 * @author 14792
 *
 * @param <E>
 */
public class LinkedListSet<E> implements Set<E> {
	private LinkedList<E> linkedList;

	public LinkedListSet() {
		linkedList = new LinkedList<E>();
	}

	@Override
	public void add(E e) {
		// 不添加重复的元素
		if (!linkedList.contains(e)) {
			linkedList.addFirst(e);
		}
	}

	@Override
	public void remove(E e) {
		linkedList.removeElement(e);
	}

	@Override
	public boolean contains(E e) {
		return linkedList.contains(e);
	}

	@Override
	public int getSize() {
		return linkedList.getSize();
	}

	@Override
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	// 测试
	public static void main(String[] args) {
		System.out.println("Pride and Prejudice");

		ArrayList<String> words1 = new ArrayList<>();
		FileOperation.readFile("pride-and-prejudice.txt", words1);
		System.out.println("Total words: " + words1.size());

		LinkedListSet<String> set2 = new LinkedListSet<String>();
		for (String string : words1) {
			set2.add(string);
		}
		System.out.println("Total different words: " + set2.getSize());
	}

}
