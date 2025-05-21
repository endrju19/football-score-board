package org.sportradar.examplescoreboard;

import java.time.Instant;
import java.util.UUID;

public class Match {
  private final UUID id;
  private final Score score;
  private final Team home, away;
  private final Instant startedAt;

  public Match(Team home, Team away) {
    this(home, away, Instant.now());
  }

  public Match(Team home, Team away, Instant startedAt) {
    this.home = home;
    this.away = away;
    this.score = new Score(0, 0);
    this.startedAt = startedAt;
    this.id = UUID.randomUUID();
  }

  public int totalScore() {
    return score.total();
  }

  public Instant getStartedAt() {
    return startedAt;
  }

  public UUID getId() {
    return id;
  }

}
