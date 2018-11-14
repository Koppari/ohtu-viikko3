package ohtu;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

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

    /*public String courseTotals(Course c) throws IOException {
        String url = "";
        url = "https://studies.cs.helsinki.fi/courses/" + (c.getName()) + "/stats";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        JsonObject jsonObject = new JsonParser().parse(bodyText).getAsJsonObject();
        return "Kurssila yhteensa " + " palautusta, palautettuja tehtavia " + " kpl, aikaa kaytetty yhteensa "
                + " tuntia.";
    }*/

    public void print() throws IOException {
        int tehdyt = 0;
        int total = 0;
        int tunnit = 0;
        String url = "";
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
            //System.out.println(courseTotals(course));
            tehdyt = total = tunnit = 0;
        }
    }

}