package io.github.acehoud01.payroll.employees;

import io.github.acehoud01.payroll.PayrollConstants;

public class FullTimeEmployee extends Employee {
    private double annualSalary;
    private double overtimeHours;

    public FullTimeEmployee(String employeeId, String name, String department, double annualSalary) {
        super(employeeId, name, department);
        setAnnualSalary(annualSalary);
        this.overtimeHours = 0;
    }

    @Override
    public double calculateGrossPay() {
        double biweeklyPay = annualSalary / PayrollConstants.PAY_PERIODS_PER_YEAR; // 26 pay periods per year
        double hourlyRate = annualSalary / PayrollConstants.WORK_HOURS_PER_YEAR; // 2080 hours per year
        double overtimePay = overtimeHours * hourlyRate * PayrollConstants.OVERTIME_MULTIPLIER;
        return biweeklyPay + overtimePay;
    }

    @Override
    public String getEmployeeType() {
        return "Full-Time";
    }

    // Setters with validation
    public void setAnnualSalary(double annualSalary) {
        if (annualSalary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.annualSalary = annualSalary;
    }

    public void setOvertimeHours(double hours) {
        this.overtimeHours = Math.max(0, hours);
    }

    public double getAnnualSalary() { return annualSalary; }
    public double getOvertimeHours() { return overtimeHours; }
}
