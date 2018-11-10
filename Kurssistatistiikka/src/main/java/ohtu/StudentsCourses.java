package ohtu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;

public class StudentsCourses {
    private Submission[] subs;
    private Course[] courses;
    private HashSet<Course> studentsCourses;

    public StudentsCourses(Submission[] subs, Course[] courses) {
        this.subs = subs;
        this.courses = courses;
        studentsCourses = new HashSet<Course>();
    }

    public void courseCheck() {
        for (Course course : courses) {
            for (Submission sub : subs) {
                if (sub.getCourse().equals(course.getName())) {
                    studentsCourses.add(course);
                }
            }
        }
    }

    public void print() {
        courseCheck();
        int tehdyt = 0;
        int total = 0;
        int tunnit = 0;
        for (Course course : studentsCourses) {
            System.out.println(course);
            for (Submission sub : subs) {
                if (sub.getCourse().equals(course.getName())) {
                    System.out.println("Viikko " + sub.getWeek());
                    System.out.print(" Aikaa kaytetty " + sub.getHours() + " tuntia.\n Tehtavia tehty "
                            + sub.getExercises().length + "/" + course.exercises[sub.getWeek()]
                            + ".\n Tehdyt tehtavat: " + Arrays.toString(sub.getExercises()) + "\n");
                    tehdyt = tehdyt + sub.getExercises().length;
                    tunnit = tunnit + sub.getHours();
                }
            }
            total = IntStream.of(course.exercises).sum();
            System.out.print("Yhteensa: " + tehdyt + "/" + total + " tehtavaa. Tunnit: " + tunnit + ".\n\n");
            tehdyt = total = tunnit = 0;
        }
    }

}