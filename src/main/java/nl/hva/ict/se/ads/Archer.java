package nl.hva.ict.se.ads;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Holds the name, archer-id and the points scored for 30 arrows.
 *
 * Archers MUST be created by using one of the generator methods. That is way the constructor is private and should stay
 * private. You are also not allowed to add any constructor with an access modifier other then private unless it is for
 * testing purposes in which case the reason why you need that constructor must be contained in a very clear manner
 * in your report.
 */
public class Archer implements Comparable<Archer> {
    private static final AtomicInteger count = new AtomicInteger(135788);
    public static int MAX_ARROWS = 3;
    public static int MAX_ROUNDS = 10;
    private static Random randomizer = new Random();
    private final int id; // Once assigned a value is not allowed to change.
    private String firstName;
    private String lastName;
    private int totalScore;
    private int weightedScore;
    private List<int[]> scorePerRound;


    /**
     * Constructs a new instance of bowman and assigns a unique ID to the instance. The ID is not allowed to ever
     * change during the lifetime of the instance! For this you need to use the correct Java keyword.Each new instance
     * is a assigned a number that is 1 higher than the last one assigned. The first instance created should have
     * ID 135788;
     *
     * @param firstName the archers first name.
     * @param lastName the archers surname.
     */
    private Archer(String firstName, String lastName) {
        id = count.getAndIncrement();
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalScore = 0;
        this.weightedScore = 0;
        scorePerRound = new ArrayList<>();
    }

    /**
     * Registers the point for each of the three arrows that have been shot during a round. The <code>points</code>
     * parameter should hold the three points, one per arrow.
     *
     * @param round the round for which to register the points.
     * @param points the points shot during the round.
     */
    public void registerScoreForRound(int round, int[] points) {
        if (round >= scorePerRound.size()) {
            scorePerRound.add(round, points);
            for (int score : points) {
                totalScore += score;
                weightedScore += weighted(score);
            }
        } else {
            scorePerRound.set(round, points);
            recalculateScores();
        }
    }

    private void recalculateScores() {
        totalScore = 0;
        weightedScore = 0;
        for (int[] scores : scorePerRound) {
            for (int points : scores) {
                totalScore += points;
                weightedScore += weighted(points);
            }
        }
    }

    public void clearScores() {
        scorePerRound = new ArrayList<>();
        totalScore = 0;
        weightedScore = 0;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public int getWeightedScore() {
        return this.weightedScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setWeightedScore(int weightedScore) {
        this.weightedScore = weightedScore;
    }

    public int weighted(int score) {
        if (score == 0)
            return -7;
        return score + 1;
    }

    @Override
    public String toString() {
        return id + " (" + getTotalScore() + "/" + getWeightedScore() + ") " + firstName + " " + lastName;
    }

    /**
     * This methods creates a List of archers.
     *
     * @param nrOfArchers the number of archers in the list.
     * @return
     */
    public static List<Archer> generateArchers(int nrOfArchers) {
        List<Archer> archers = new ArrayList<>(nrOfArchers);
        for (int i = 0; i < nrOfArchers; i++) {
            Archer archer = new Archer(Names.nextFirstName(), Names.nextSurname());
            letArcherShoot(archer, nrOfArchers % 100 == 0);
            archers.add(archer);
        }
        return archers;

    }

    /**
     * This methods creates a Iterator that can be used to generate all the required archers. If you implement this
     * method it is only allowed to create an instance of Archer inside the next() method!
     *
     * <b>THIS METHODS IS OPTIONAL</b>
     *
     * @param nrOfArchers the number of archers the Iterator will create.
     * @return
     */
    public static Iterator<Archer> generateArchers(long nrOfArchers) {
        return new ArcherIterator(nrOfArchers);
    }

    public int getId() {
        return id;
    }

    private static void letArcherShoot(Archer archer, boolean isBeginner) {
        for (int round = 0; round < MAX_ROUNDS; round++) {
            archer.registerScoreForRound(round, shootArrows(isBeginner ? 0 : 1));
        }
    }

    private static int[] shootArrows(int min) {
        int[] points = new int[MAX_ARROWS];
        for (int arrow = 0; arrow < MAX_ARROWS; arrow++) {
            points[arrow] = shoot(min);
        }
        return points;
    }

    private static int shoot(int min) {
        return Math.max(min, randomizer.nextInt(11));
    }

    @Override
    public int compareTo(Archer o) {
        return new ArcherTotalScoreComparator().compare(this, o);
    }

}

class ArcherIterator implements Iterator<Archer> {
    private int pos;
    private int max;

    public ArcherIterator(long max) {
        pos = 0;
        this.max = (int) max;
    }

    @Override
    public boolean hasNext() {
        return pos < max;
    }

    @Override
    public Archer next() {
        pos++;
        return Archer.generateArchers(1).get(0);
    }
}

class ArcherTotalScoreComparator implements Comparator<Archer> {
    public int compare(Archer a1, Archer a2) {
        if (a1.getTotalScore() - a2.getTotalScore() == 0) {
            if (a1.getWeightedScore() - a2.getWeightedScore() == 0)
                return a1.getId() - a2.getId();

            return a1.getWeightedScore() - a2.getWeightedScore();
        }
        return a1.getTotalScore() - a2.getTotalScore();
    }
}
