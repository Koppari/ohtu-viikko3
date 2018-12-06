package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
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

    }
}
