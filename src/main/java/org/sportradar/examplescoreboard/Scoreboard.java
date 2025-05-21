package org.sportradar.examplescoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * For simplicity, this implementation doesn't support concurrency
 */
public class Scoreboard {

  private final List<Match> matches = new ArrayList<>();

  public void startNew(Match match) {
    matches.add(match);
  }

  public List<Match> getSummary() {
    return matches.stream()
      .sorted(Comparator.comparing(Match::totalScore).reversed()
        .thenComparing(Match::getStartedAt).reversed()).toList();
  }

  public void updateScores(UUID matchId, int i, int i1) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void finish(Match match) {
   throw new UnsupportedOperationException("Not supported yet.");
  }
}
