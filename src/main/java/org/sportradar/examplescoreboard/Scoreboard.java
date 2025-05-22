package org.sportradar.examplescoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * For simplicity, this implementation doesn't support concurrency
 */
public class Scoreboard {

  private final List<Match> matches = new ArrayList<>();

  public void startNew(Match match) {
    validateMatch(match);
    matches.add(match);
  }

  public List<Match> getSummary() {
    return matches.stream()
      .sorted(Comparator.comparing(Match::totalScore).reversed()
        .thenComparing(Match::getStartedAt, Comparator.reverseOrder()))
      .toList();
  }

  public void updateScore(UUID matchId, Score score) {
    matches.stream().filter(match -> match.getId().equals(matchId))
      .findAny().orElseThrow(NoSuchElementException::new).updateScore(score);
  }

  public void finish(Match match) {
   throw new UnsupportedOperationException("Not supported yet.");
  }

  private void validateMatch(Match match) {
    if (match == null) {
      throw new IllegalArgumentException("Match cannot be null");
    }

    boolean isDuplicateMatch = matches.stream()
      .anyMatch(existingMatch -> existingMatch.getId().equals(match.getId()));

    if (isDuplicateMatch) {
      throw new IllegalArgumentException("Match with ID " + match.getId() + " already exists");
    }
  }
}
