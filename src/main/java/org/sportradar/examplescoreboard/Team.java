package org.sportradar.examplescoreboard;

public record Team(String name) {
  public Team {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Team name cannot be null or blank");
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
