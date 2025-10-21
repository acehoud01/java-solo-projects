package io.github.acehoud01.payroll.employees;

import io.github.acehoud01.payroll.PayrollConstants;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Employee {
    protected final String employeeId;
    protected String name;
    protected String department;
    protected LocalDate hireDate;

    public Employee(String employeeId, String name, String department) {
        this.employeeId = Objects.requireNonNull(employeeId, "Employee ID cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.department = Objects.requireNonNull(department, "Department cannot be null");
        this.hireDate = LocalDate.now();
    }

    // Custom hire date for existing employees
    public Employee(String employeeId, String name, String department, LocalDate hireDate) {
        this(employeeId, name, department);
        this.hireDate = Objects.requireNonNull(hireDate, "Hire date cannot be null");
    }

    public abstract double calculateGrossPay();
    public abstract String getEmployeeType();

    public double calculateNetPay() {
        double grossPay = calculateGrossPay();
        double deductions = calculateDeductions(grossPay);
        return grossPay - deductions;
    }

    public double calculateDeductions(double grossPay) {
        double tax = grossPay * PayrollConstants.TAX_RATE;
        double providentFund = grossPay * PayrollConstants.PROVIDENT_FUND_RATE;
        double medicalAid = grossPay * PayrollConstants.MEDICAL_AID_RATE;
        return tax + providentFund + medicalAid;
    }

    // Getters
    public String getEmployeeId() {
        return employeeId;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() { return department; }
    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s) - %s",
                employeeId, name, getEmployeeType(), department);
    }
}
