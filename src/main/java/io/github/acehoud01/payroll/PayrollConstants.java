package io.github.acehoud01.payroll;

public final class PayrollConstants {
    private PayrollConstants() {} // Prevent instantiation

    public static final double TAX_RATE = 0.20; // 15% Tax
    public static final double PROVIDENT_FUND_RATE = 0.062; // 6.2%
    public static final double MEDICAL_AID_RATE = 0.0145; // 1.45%
    public static final double OVERTIME_MULTIPLIER = 1.5;
    public static final int PAY_PERIODS_PER_YEAR = 26;
    public static final int WORK_HOURS_PER_YEAR = 2080;
    public static final int STANDARD_WORK_HOURS = 45;
}
