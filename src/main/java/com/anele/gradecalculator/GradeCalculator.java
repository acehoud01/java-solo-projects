package com.anele.gradecalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeCalculator {
    private List<Student> students;
    private Scanner scanner;

    public GradeCalculator() {
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.println("Enter Student name: ");
        String name = scanner.nextLine();

        Student student = new Student(name);

        System.out.println("How many test scores to enter? ");
        int numScores = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < numScores; i++) {
            try {
                System.out.println("Enter score " + (i + 1) + ": ");
                double score = scanner.nextDouble();
                student.addScore(score);
            } catch (Exception e) {
                System.out.println("Invalid score. Please enter a number between 0-100");
                i--; // retry this score
                scanner.nextLine(); // clear invalid input
            }
        }
        students.add(student);
        System.out.println("Student added successfully!\n");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.\n");
            return;
        }

        System.out.println("\n=== STUDENT GRADE REPORT ===");
        System.out.printf("%-15s %-8s %-8s %-5s%n", "Name", "Average", "Letter", "GPA");
        System.out.println("----------------------------------------");

        for (Student student : students) {
            String letter = student.getLetterGrade();
            double gpa = Grade.calculateGPA(letter);
            System.out.printf("%-15s %-8.2f %-8s %-5.1f%n",
                    student.getName(), student.getAverage(), letter, gpa);
        }
        System.out.println();
    }

    public void displayClassStatistics() {
        if (students.isEmpty()) {
            System.out.println("No students to analyze.\n");
            return;
        }

        double sum = 0, highest = 0, lowest = 100;
        for (Student student : students) {
            double avg = student.getAverage();
            sum += avg;
            if (avg > highest) highest = avg;
            if (avg < lowest) lowest = avg;
        }

        double classAverage = sum / students.size();

        System.out.println("\n=== CLASS STATISTICS ===");
        System.out.printf("Class Average: %.2f (%s)%n", classAverage, Grade.calculateLetterGrade(classAverage));
        System.out.printf("Highest Score: %.2f%n", highest);
        System.out.printf("Lowest Score: %.2f%n", lowest);
        System.out.printf("Total Students: %d%n", students.size());
        System.out.println();
    }

    public void showMenu() {
        while (true) {
            System.out.println("=== GRADE CALCULATOR MENU ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Class Statistics");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: addStudent(); break;
                case 2: displayAllStudents(); break;
                case 3: displayClassStatistics(); break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }
}
