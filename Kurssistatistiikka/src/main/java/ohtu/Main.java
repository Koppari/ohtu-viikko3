package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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
        /*String ohtuStats = "https://api.myjson.com/bins/8wzz2";
        String railsStats = "https://studies.cs.helsinki.fi/courses/rails2018/stats";

        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(ohtuStats).getAsJsonObject();*/
        String bodyText = Request.Get(studentUrl).execute().returnContent().asString();
        String courseBodyText = Request.Get(coursesUrl).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses = mapper.fromJson(courseBodyText, Course[].class);
        // CourseStats[] courseStats = mapper.fromJson(courseStatsParse,CourseStats[].class);

        StudentsCourses sc = new StudentsCourses(subs, courses);
        System.out.println("Opiskelijan " + studentNr + " kurssit:\n");
        sc.print();

    }
}