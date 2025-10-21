package io.github.acehoud01.payroll.employees;

import io.github.acehoud01.payroll.PayrollConstants;

import java.time.LocalDate;

public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private double hoursWorked;

    public PartTimeEmployee(String employeeId, String name, String department, double hourlyRate) {
        super(employeeId, name, department);
        setHourlyRate(hourlyRate);
        this.hoursWorked = 0;
    }

    public PartTimeEmployee(String employeeId, String name, String department, LocalDate hireDate, double hourlyRate) {
        super(employeeId, name, department, hireDate);
        setHourlyRate(hourlyRate);
        this.hoursWorked = 0;
    }

    @Override
    public double calculateGrossPay() {
        if (hoursWorked <= PayrollConstants.STANDARD_WORK_HOURS) {
            return hoursWorked * hourlyRate;
        } else {
            double regularPay = PayrollConstants.STANDARD_WORK_HOURS * hourlyRate;
            double overtimeHours = hoursWorked - PayrollConstants.STANDARD_WORK_HOURS;
            double overtimePay = overtimeHours * hourlyRate * PayrollConstants.OVERTIME_MULTIPLIER;
            return regularPay + overtimePay;
        }
    }

    @Override
    public String getEmployeeType() {
        return "Part-Time";
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative");
        }
        this.hourlyRate = hourlyRate;
    }

    public void setHoursWorked(double hours) {
        this.hoursWorked = Math.max(0, hours);
    }

    public double getHourlyRate() { return hourlyRate; }
    public double getHoursWorked() { return hoursWorked; }
}
