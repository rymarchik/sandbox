package core.exercise.LTIMindTree;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

/**
 * Count of employees earning the second-highest salary
 */
@Getter
class Employee {

    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(new Employee("alex", 10),
            new Employee("alex2", 25),
            new Employee("alex3", 25),
            new Employee("alex33", 22),
            new Employee("alex333", 22),
            new Employee("alex3333", 22),
            new Employee("alex4", 5),
            new Employee("alex5", 5));

        Long count = 0L;

        // 1st option - O(n*log n)
        count = employees.stream()
            .map(Employee::getSalary)
            .distinct()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .findFirst()
            .map(secondHighest -> employees.stream()
                .filter(e -> e.getSalary() == secondHighest)
                .count()
            )
            .orElse(0L);

        // 2nd option - O(n*log n)
        Map<Integer, Long> salaryCounts = employees.stream()
            .collect(Collectors.groupingBy(Employee::getSalary, Collectors.counting()));
        count = salaryCounts.keySet().stream()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .findFirst()
            .map(salaryCounts::get)
            .orElse(0L);

        // 3rd option without skip()  - O(n) because of no sort
        int maxSalary = employees.stream()
            .mapToInt(Employee::getSalary)
            .max()
            .orElse(Integer.MIN_VALUE);
        int secondHighestSalary = employees.stream()
            .mapToInt(Employee::getSalary)
            .filter(salary -> salary < maxSalary)
            .max()
            .orElse(Integer.MIN_VALUE);
        count = employees.stream()
            .filter(e -> e.getSalary() == secondHighestSalary)
            .count();

        System.out.println(count);
    }


}



