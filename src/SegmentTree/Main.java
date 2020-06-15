package SegmentTree;

public class Main {
	public static void main(String[] args) {
		Integer[] nums = { -2, 0, 3, -5, 2, -1 };

		SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, new Merger<Integer>() {

			@Override
			public Integer merge(Integer a, Integer b) {
				return a + b;
			}
		});

		System.out.println(segmentTree);

		System.out.println("query [0 1]: " + segmentTree.query(0, 1)); // 1
		System.out.println("query [2 4]: " + segmentTree.query(2, 4)); // -1
		System.out.println("query [0 5]: " + segmentTree.query(0, 5)); // -3
	}
}
