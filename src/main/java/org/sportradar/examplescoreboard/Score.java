package org.sportradar.examplescoreboard;

public record Score(int home, int away) {

  public int total() {
    return home + away;
  }
}
