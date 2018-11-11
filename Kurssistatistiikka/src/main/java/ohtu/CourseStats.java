package ohtu;

public class CourseStats {
    private int students;
    private int hour_total;
    private int exercise_total;
    private int[] hours;
    private int[] exercises;

    @Override
    public String toString() {
        return "";
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public int getHour_total() {
        return hour_total;
    }

    public void setHour_total(int hour_total) {
        this.hour_total = hour_total;
    }

    public int getExercise_total() {
        return exercise_total;
    }

    public void setExercise_total(int exercise_total) {
        this.exercise_total = exercise_total;
    }

    public int[] getHours() {
        return hours;
    }

    public void setHours(int[] hours) {
        this.hours = hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

}