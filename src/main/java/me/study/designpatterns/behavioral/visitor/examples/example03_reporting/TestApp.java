package me.study.designpatterns.behavioral.visitor.examples.example03_reporting;

import java.text.NumberFormat;
import java.util.Locale;

public class TestApp {
    public static void main(String[] args) {
        Department mobileDev = new Department("Mobile Development", new Employee[] {
                new Employee("Albert Falmore", "designer", 100000),
                new Employee("Ali Halabay", "programmer", 100000),
                new Employee("Sarah Konor", "programmer", 90000),
                new Employee("Monica Ronaldino", "QA engineer", 31000),
                new Employee("James Smith", "QA engineer", 30000),
        });
        Department techSupport = new Department("Tech Support", new Employee[] {
                new Employee("Larry Ulbrecht", "supervisor", 70000),
                new Employee("Elton Pale", "operator", 30000),
                new Employee("Rajeet Kumar", "operator", 30000),
                new Employee("John Burnovsky", "operator", 34000),
                new Employee("Sergey Korolev", "operator", 35000),
        });
        Company company = new Company("SuperStarDevelopment", new Department[] { mobileDev, techSupport });
        SalaryReport report = new SalaryReport();

        System.out.println("Client: I can print a report for a whole company:");
        System.out.println(company.accept(report));

        System.out.println(
                "Client: ...or for different entities such as an employee, a department, or the whole company:");
        Employee someEmployee = new Employee("Some employee", "operator", 35000);
        Entity[] differentEntities = new Entity[] { someEmployee, techSupport, company };
        for (Entity entity : differentEntities) {
            System.out.println(entity.accept(report));
        }
    }
}

/**
 * The Component interface declares a method of accepting visitor objects.
 *
 * In this method, a Concrete Component must call a specific Visitor's method
 * that has the same parameter type as that component.
 */
interface Entity {
    String accept(Visitor visitor);
}

/**
 * The Company Concrete Component.
 */
class Company implements Entity {
    private String name;
    private Department[] departments;

    public Company(String name, Department[] departments) {
        this.name = name;
        this.departments = departments;
    }

    public String getName() {
        return this.name;
    }

    public Department[] getDepartments() {
        return this.departments;
    }

    // See, the Company component must call the visitCompany method. The
    // same principle applies to all components.
    public String accept(Visitor visitor) {
        return visitor.visitCompany(this);
    }
}

/**
 * The Department Concrete Component.
 */
class Department implements Entity {
    private String name;
    private Employee[] employees;

    public Department(String name, Employee[] employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return this.name;
    }

    public Employee[] getEmployees() {
        return this.employees;
    }

    public int getCost() {
        int cost = 0;
        for (Employee employee : this.employees) {
            cost += employee.getSalary();
        }
        return cost;
    }

    public String accept(Visitor visitor) {
        return visitor.visitDepartment(this);
    }
}

/**
 * The Employee Concrete Component.
 */
class Employee implements Entity {
    private String name;
    private String position;
    private int salary;

    public Employee(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return this.name;
    }

    public String getPosition() {
        return this.position;
    }

    public int getSalary() {
        return this.salary;
    }

    public String accept(Visitor visitor) {
        return visitor.visitEmployee(this);
    }
}

/**
 * The Visitor interface declares a set of visiting methods for each of the
 * Concrete Component classes.
 */
interface Visitor {
    String visitCompany(Company company);

    String visitDepartment(Department department);

    String visitEmployee(Employee employee);
}

/**
 * The Concrete Visitor must provide implementations for every single class of
 * the Concrete Components.
 */
class SalaryReport implements Visitor {
    public String visitCompany(Company company) {
        StringBuilder output = new StringBuilder();
        int total = 0;


        for (Department department : company.getDepartments()) {
            total += department.getCost();
            output.append("\n--").append(this.visitDepartment(department));
        }

        output.insert(0, company.getName() + " (" + formatCurrency(total) + ")\n");

        return output.toString();
    }

    public String visitDepartment(Department department) {
        StringBuilder output = new StringBuilder();

        for (Employee employee : department.getEmployees()) {
            output.append("   ").append(this.visitEmployee(employee));
        }

        output.insert(0, department.getName() + " (" +formatCurrency(department.getCost()) + ")\n");

        return output.toString();
    }

    public String visitEmployee(Employee employee) {
        return formatCurrency(employee.getSalary()) + " " + employee.getName() + " (" + employee.getPosition() + ")\n";
    }

    private String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(amount);
    }
}