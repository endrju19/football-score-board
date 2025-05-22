package org.sportradar.examplescoreboard;

public record Score(int home, int away) {

  public Score {
    if (home < 0 || away < 0) {
      throw new IllegalArgumentException("Score cannot be negative");
    }
  }

  public int total() {
    return home + away;
  }
}
