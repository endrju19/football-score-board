package org.sportradar.examplescoreboard

import spock.lang.Specification

class MatchSpec extends Specification {

  def "Should throw exception when home team is null"() {
    when:
    new Match(null, new Team("away"))

    then:
    def exception = thrown(NullPointerException)
    exception.message == "Home team cannot be null"
  }

  def "Should throw exception when away team is null"() {
    when:
    new Match(new Team("home"), null)

    then:
    def exception = thrown(NullPointerException)
    exception.message == "Away team cannot be null"
  }

  def "Should throw exception when start time is null"() {
    when:
    new Match(new Team("home"), new Team("away"), null)

    then:
    def exception = thrown(NullPointerException)
    exception.message == "Start time cannot be null"
  }

  def "Should throw exception when trying to decrease score"() {
    given:
    def match = new Match(new Team("home"), new Team("away"))
    match.updateScore(new Score(2, 2))

    when:
    match.updateScore(new Score(1, 2))

    then:
    thrown(IllegalArgumentException)
  }

  def "Should update score successfully"() {
    given:
    def match = new Match(new Team("home"), new Team("away"))

    when:
    match.updateScore(new Score(2, 3))

    then:
    match.totalScore() == 5
  }
}
