import java.util.*;
import java.util.stream.*;

class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + ", salary=" + salary + "}";
    }
}

class Student {
    private String name;
    private int age;
    private double marks;

    public Student(String name, int age, double marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getMarks() { return marks; }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", marks=" + marks + "}";
    }
}

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Product{name='" + name + "', category='" + category + "', price=" + price + "}";
    }
}

public class CombinedStreamOperations {

    public static void main(String[] args) {

        // ------------------- Part A: Employee Sorting -------------------
        System.out.println("=== Part A: Sorting Employee Objects Using Lambda ===");

        List<Employee> employees = Arrays.asList(
            new Employee("John", 30, 50000),
            new Employee("Alice", 25, 60000),
            new Employee("Bob", 35, 55000)
        );

        System.out.println("Sort by Name:");
        employees.stream()
                 .sorted(Comparator.comparing(Employee::getName))
                 .forEach(System.out::println);

        System.out.println("\nSort by Age:");
        employees.stream()
                 .sorted(Comparator.comparing(Employee::getAge))
                 .forEach(System.out::println);

        System.out.println("\nSort by Salary (Descending):");
        employees.stream()
                 .sorted(Comparator.comparing(Employee::getSalary).reversed())
                 .forEach(System.out::println);


        // ------------------- Part B: Student Filtering and Sorting -------------------
        System.out.println("\n=== Part B: Filtering and Sorting Students Using Streams ===");

        List<Student> students = Arrays.asList(
            new Student("Ravi", 20, 85),
            new Student("Sneha", 22, 75),
            new Student("Arjun", 21, 92),
            new Student("Priya", 23, 68)
        );

        System.out.println("Students with marks > 70 sorted by marks descending:");
        students.stream()
                .filter(s -> s.getMarks() > 70)
                .sorted(Comparator.comparing(Student::getMarks).reversed())
                .forEach(System.out::println);


        // ------------------- Part C: Stream Operations on Product Dataset -------------------
        System.out.println("\n=== Part C: Stream Operations on Product Dataset ===");

        List<Product> products = Arrays.asList(
            new Product("iPhone", "Electronics", 90000),
            new Product("MacBook", "Electronics", 120000),
            new Product("Shirt", "Clothing", 2000),
            new Product("Shoes", "Footwear", 4000),
            new Product("Headphones", "Electronics", 8000)
        );

        System.out.println("All Product Names:");
        products.stream()
                .map(Product::getName)
                .forEach(System.out::println);

        System.out.println("\nAverage Price of All Products:");
        double avgPrice = products.stream()
                                  .mapToDouble(Product::getPrice)
                                  .average()
                                  .orElse(0.0);
        System.out.println("Average Price: " + avgPrice);

        System.out.println("\nProducts in Electronics Category sorted by price:");
        products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Electronics"))
                .sorted(Comparator.comparing(Product::getPrice))
                .forEach(System.out::println);

        System.out.println("\nTotal Value of All Products:");
        double totalValue = products.stream()
                                    .mapToDouble(Product::getPrice)
                                    .sum();
        System.out.println("Total Value: " + totalValue);
    }
}
