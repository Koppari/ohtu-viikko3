package statistics.matcher;

import statistics.matcher.Matcher;

public class QueryBuilder {
    Matcher m;

    public QueryBuilder() {
        m = new All();
    }

    public Matcher build() {
        Matcher helper = m;
        m = new All();
        return helper;
    }

    public QueryBuilder playsIn(String team) {
        m = new And(m, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        m = new And(m, new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        m = new And(m, new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher firstMatcher, Matcher secondMatcher) {
        m = new Or(firstMatcher, secondMatcher);
        return this;
    }

}