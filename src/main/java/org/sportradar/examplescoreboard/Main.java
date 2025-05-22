package org.sportradar.examplescoreboard;

public class Main {
  public static void main(String[] args) {
    Scoreboard scoreboard = new Scoreboard();

    // a. Mexico - Canada: 0 – 5
    Match matchMC = new Match(new Team("Mexico"), new Team("Canada"));
    scoreboard.startNew(matchMC);
    scoreboard.updateScore(matchMC.getId(), new Score(0, 5));

    // b. Spain - Brazil: 10 – 2
    Match matchSB = new Match(new Team("Spain"), new Team("Brazil"));
    scoreboard.startNew(matchSB);
    scoreboard.updateScore(matchSB.getId(), new Score(10, 2));

    // c. Germany - France: 2 – 2
    Match matchGF = new Match(new Team("Germany"), new Team("France"));
    scoreboard.startNew(matchGF);
    scoreboard.updateScore(matchGF.getId(), new Score(2, 2));

    // d. Uruguay - Italy: 6 – 6
    Match matchUI = new Match(new Team("Uruguay"), new Team("Italy"));
    scoreboard.startNew(matchUI);
    scoreboard.updateScore(matchUI.getId(), new Score(6, 6));

    // e. Argentina - Australia: 3 - 1
    Match matchAA = new Match(new Team("Argentina"), new Team("Australia"));
    scoreboard.startNew(matchAA);
    scoreboard.updateScore(matchAA.getId(), new Score(3, 1));

    scoreboard.getSummary().forEach(System.out::println);
  }
}