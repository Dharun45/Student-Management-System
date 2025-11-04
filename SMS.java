import java.util.*;

// Student class to store student details
class Student {
    private int id;
    private String name;
    private int age;
    private double marks;

    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setMarks(double marks) { this.marks = marks; }

    public String getGrade() {
        if (marks >= 90) return "A";
        else if (marks >= 75) return "B";
        else if (marks >= 60) return "C";
        else return "D"; // changed: marks < 60 now grade D (no F)
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age +
               " | Marks: " + marks + " | Grade: " + getGrade();
    }
}

// Service class for all student-related operations
class StudentService {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        for (Student st : students) {
            if (st.getId() == s.getId()) {
                System.out.println("Student with this ID already exists!");
                return;
            }
        }
        students.add(s);
        System.out.println("Student added successfully!");
    }

    public void viewAll() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void updateStudent(int id, String name, int age, double marks) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(name);
                s.setAge(age);
                s.setMarks(marks);
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public void deleteStudent(int id) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }
}

// Main class — Entry point of the program
public class SMS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        int choice = -1;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println(" Invalid choice! Please enter a number between 1 and 5.");
                sc.nextLine(); // clear invalid input
                continue;
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter a valid integer ID: ");
                        sc.nextLine();
                    }
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter a valid integer Age: ");
                        sc.nextLine();
                    }
                    int age = sc.nextInt();
                    System.out.print("Enter Marks: ");
                    while (!sc.hasNextDouble()) {
                        System.out.print("Please enter valid numeric Marks: ");
                        sc.nextLine();
                    }
                    double marks = sc.nextDouble();
                    sc.nextLine();
                    service.addStudent(new Student(id, name, age, marks));
                    break;

                case 2:
                    service.viewAll();
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter a valid integer ID: ");
                        sc.nextLine();
                    }
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter New Age: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter a valid integer Age: ");
                        sc.nextLine();
                    }
                    int uage = sc.nextInt();
                    System.out.print("Enter New Marks: ");
                    while (!sc.hasNextDouble()) {
                        System.out.print("Please enter valid numeric Marks: ");
                        sc.nextLine();
                    }
                    double umarks = sc.nextDouble();
                    sc.nextLine();
                    service.updateStudent(uid, uname, uage, umarks);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter a valid integer ID: ");
                        sc.nextLine();
                    }
                    int did = sc.nextInt();
                    sc.nextLine();
                    service.deleteStudent(did);
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println(" Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 5);

        sc.close();
    }
}
