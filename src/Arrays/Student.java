package Arrays;

public class Student {
	private String name;
	private int score;

	public Student(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return String.format("student(name = %s, score = %d)", name, score);
	}

	public static void main(String[] args) {
		Array<Student> students = new Array<>();

		students.addLast(new Student("Alice", 100));
		students.addLast(new Student("Bob", 99));
		students.addLast(new Student("Charlie", 98));

		System.out.println(students);

		students.remove(1);
		System.out.println(students);
	}

}
