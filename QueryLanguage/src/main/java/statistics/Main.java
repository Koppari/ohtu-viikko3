package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
 
        QueryBuilder query = new QueryBuilder();
     
        Matcher mAnd = query.playsIn("NYR")
        .hasAtLeast(10, "goals")
        .hasFewerThan(25, "goals").build();
     
        for (Player player : stats.matches(mAnd)) {
            System.out.println(player);
        }

        System.out.println();

        Matcher m1 = query.playsIn("PHI")
                  .hasAtLeast(10, "goals")
                  .hasFewerThan(20, "assists").build();
 
        Matcher m2 = query.playsIn("EDM")
                  .hasAtLeast(60, "points").build();
 
        Matcher mOr = query.oneOf(m1, m2).build();

        for (Player player : stats.matches(mOr)) {
            System.out.println(player);
        }
            
        /*
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

        Matcher mAndMatcher = new And(new HasAtLeast(10, "goals"), new HasAtLeast(10, "assists"), new PlaysIn("PHI"));

        for (Player player : stats.matches(mAndMatcher)) {
            System.out.println(player);
        }

        System.out.println();

        Matcher mNotMatcher = new Not(new HasAtLeast(1, "goals"));

        for (Player player : stats.matches(mNotMatcher)) {
            System.out.println(player);
        }

        System.out.println();

        Matcher mOrMatcher = new Or(new HasAtLeast(40, "goals"), new HasAtLeast(60, "assists"),
                new HasAtLeast(85, "points"));

        for (Player player : stats.matches(mOrMatcher)) {
            System.out.println(player);
        }

        System.out.println();

        Matcher mAllMatcher = new All();

        for (Player player : stats.matches(mAllMatcher)) {
            System.out.println(player);
        }
        */

    }
}
