import java.io.*;
import java.util.*;

// --- Part B & C Classes ---
class Student implements Serializable {
    int id;
    String name;
    double marks;

    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

class Employee implements Serializable {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

// --- Main Program ---
public class Main {
    static final String EMP_FILE = "employees.dat";

    @SuppressWarnings("unchecked")
    public static ArrayList<Employee> readEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EMP_FILE))) {
            return (ArrayList<Employee>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void writeEmployees(ArrayList<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EMP_FILE))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("File Error: " + e);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Sum of Integers (Autoboxing/Unboxing)");
            System.out.println("2. Student Serialization/Deserialization");
            System.out.println("3. Employee Management System");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: { // Part A
                    System.out.print("Enter number of integers: ");
                    int n = sc.nextInt();
                    Integer sum = 0; // autoboxing
                    System.out.println("Enter " + n + " integers:");
                    for (int i = 0; i < n; i++) {
                        int num = sc.nextInt();
                        sum += num; // unboxing
                    }
                    System.out.println("Sum: " + sum);
                    break;
                }

                case 2: { // Part B
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    Student s = new Student(id, name, marks);

                    // Serialization
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
                        oos.writeObject(s);
                        System.out.println("Student serialized successfully.");
                    } catch (IOException e) {
                        System.out.println("Serialization error: " + e);
                    }

                    // Deserialization
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"))) {
                        Student s2 = (Student) ois.readObject();
                        System.out.println("Deserialized Student: " + s2);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Deserialization error: " + e);
                    }
                    break;
                }

                case 3: { // Part C
                    ArrayList<Employee> employees = readEmployees();
                    while (true) {
                        System.out.println("\n--- Employee Menu ---");
                        System.out.println("1. Add Employee");
                        System.out.println("2. Display All Employees");
                        System.out.println("3. Search Employee by ID");
                        System.out.println("4. Update Employee by ID");
                        System.out.println("5. Delete Employee by ID");
                        System.out.println("0. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int empChoice = sc.nextInt();
                        sc.nextLine();

                        switch (empChoice) {
                            case 1:
                                System.out.print("Enter ID: ");
                                int eid = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Name: ");
                                String ename = sc.nextLine();
                                System.out.print("Enter Salary: ");
                                double esalary = sc.nextDouble();
                                employees.add(new Employee(eid, ename, esalary));
                                writeEmployees(employees);
                                System.out.println("Employee added successfully.");
                                break;
                            case 2:
                                if (employees.isEmpty()) System.out.println("No employees.");
                                else employees.forEach(System.out::println);
                                break;
                            case 3:
                                System.out.print("Enter ID to search: ");
                                int sid = sc.nextInt();
                                boolean found = false;
                                for (Employee e : employees) {
                                    if (e.id == sid) {
                                        System.out.println("Employee found: " + e);
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) System.out.println("Employee not found.");
                                break;
                            case 4:
                                System.out.print("Enter ID to update: ");
                                int uid = sc.nextInt();
                                sc.nextLine();
                                boolean updated = false;
                                for (Employee e : employees) {
                                    if (e.id == uid) {
                                        System.out.print("Enter new name: ");
                                        e.name = sc.nextLine();
                                        System.out.print("Enter new salary: ");
                                        e.salary = sc.nextDouble();
                                        updated = true;
                                        writeEmployees(employees);
                                        System.out.println("Employee updated successfully.");
                                        break;
                                    }
                                }
                                if (!updated) System.out.println("Employee not found.");
                                break;
                            case 5:
                                System.out.print("Enter ID to delete: ");
                                int did = sc.nextInt();
                                boolean removed = employees.removeIf(e -> e.id == did);
                                if (removed) {
                                    writeEmployees(employees);
                                    System.out.println("Employee deleted successfully.");
                                } else System.out.println("Employee not found.");
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                        if (empChoice == 0) break;
                    }
                    break;
                }

                case 0:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
