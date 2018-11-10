package ohtu;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private String course;

    public int[] getExercises() {
        return exercises;
    }

    public int getHours() {
        return hours;
    }

    public String getCourse() {
        return course;
    }

    public int getWeek() {
        return week;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return getCourse() + ", viikko " + getWeek();
    }

}