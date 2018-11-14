package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String studentUrl = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String coursesUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String bodyText = Request.Get(studentUrl).execute().returnContent().asString();
        String courseBodyText = Request.Get(coursesUrl).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses = mapper.fromJson(courseBodyText, Course[].class);

        StudentsCourses sc = new StudentsCourses(subs, courses);
        System.out.println("Opiskelijan " + studentNr + " kurssit:\n");
        sc.courseCheck();
        sc.print();
    }
}