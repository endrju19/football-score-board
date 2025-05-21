package org.sportradar.examplescoreboard;

public class Main {
  public static void main(String[] args) {
    var scoreboard = new Scoreboard();
    var match = new Match(new Team("Polska"), new Team("Niemcy"));
    var match2 = new Match(new Team("Argentyna"), new Team("Brazylia"));
    scoreboard.startNew(match);
    scoreboard.startNew(match2);
//    scoreboard.updateScores(match, 1, 1);
    scoreboard.getSummary();
    scoreboard.finish(match);
    scoreboard.getSummary();
  }
}