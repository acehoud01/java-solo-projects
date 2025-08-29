package io.github.acehoud01.gradecalculator;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Double> scores;

    public Student(String name) {
        this.name = name;
        this.scores = new ArrayList<>();
    }

    public void addScore(double score) {
        if (score >= 0 && score <= 100) {
            scores.add(score);
        } else {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
    }

    public double getAverage() {
        if (scores.isEmpty()) return 0;
        double sum = 0;

        for (double score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }

    public String getLetterGrade() {
        double avg = getAverage();

        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else if (avg >= 60) return "D";
        else return "F";
    }

    public String getName() { return name; }
    public List<Double> getScores() { return new ArrayList<>(); }
    public int getScoreCount() { return scores.size(); }
}
