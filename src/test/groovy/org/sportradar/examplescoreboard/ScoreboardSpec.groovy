package org.sportradar.examplescoreboard

import spock.lang.Specification

import java.time.Instant

class ScoreboardSpec extends Specification {

  def "Should start with empty scoreboard"() {
    given:
    def scoreboard = new Scoreboard()

    when:
    def summary = scoreboard.getSummary()

    then:
    summary.size() == 0
  }

  def "Should display summary in proper order for new matches"() {
    given:
    def scoreboard = new Scoreboard()
    def now = Instant.now()
    def match1 = new Match(aTeam("Real Madrid"), aTeam("Barcelona"), now)
    def match2 = new Match(aTeam("Poland"), aTeam("Germany"), now + 1)

    scoreboard.startNew match1
    scoreboard.startNew match2

    when:
    def summary = scoreboard.getSummary()

    then:
    summary.size() == 2
    summary.collect {
      [
          it.home.name,
          it.away.name,
          it.score.home(),
          it.score.away()
      ]
    } == [
        ["Poland", "Germany", 0, 0],
        ["Real Madrid", "Barcelona", 0, 0]
    ]
  }

  def "Should display summary in proper order for new matches with different total scores"() {
    given:
    def scoreboard = new Scoreboard()
    def now = Instant.now()
    def match1 = new Match(aTeam("Real Madrid"), aTeam("Barcelona"), now)
    def match2 = new Match(aTeam("Poland"), aTeam("Germany"), now + 1)
    def match3 = new Match(aTeam("Italy"), aTeam("France"), now + 2)

    scoreboard.startNew match1
    scoreboard.startNew match2
    scoreboard.startNew match3

    and:
    scoreboard.updateScore(match1.getId(), new Score(2, 1))
    scoreboard.updateScore(match2.getId(), new Score(1, 2))
    scoreboard.updateScore(match3.getId(), new Score(0, 2))

    when:
    def summary = scoreboard.getSummary()

    then:
    summary.size() == 3
    summary.collect {
      [
          it.home.name,
          it.away.name,
          it.score.home(),
          it.score.away()
      ]
    } == [
        ["Poland", "Germany", 1, 2],
        ["Real Madrid", "Barcelona", 2, 1],
        ["Italy", "France", 0, 2]
    ]
  }

  def "Should throw exception when adding match with existing ID"() {
    given:
    def scoreboard = new Scoreboard()
    def match = new Match(aTeam("Poland"), aTeam("Germany"))
    scoreboard.startNew(match)

    when:
    scoreboard.startNew(match)

    then:
    thrown(IllegalArgumentException)
  }

  def "Should throw exception when updating score for non-existing match"() {
    given:
    def scoreboard = new Scoreboard()
    def nonExistingId = UUID.randomUUID()

    when:
    scoreboard.updateScore(nonExistingId, new Score(1, 1))

    then:
    thrown(NoSuchElementException)
  }

  def "Should throw exception when updating score with negative values"() {
    given:
    def scoreboard = new Scoreboard()
    def match = new Match(aTeam("Poland"), aTeam("Germany"))
    scoreboard.startNew(match)

    when:
    scoreboard.updateScore(match.getId(), new Score(-1, 0))

    then:
    thrown(IllegalArgumentException)
  }

  Team aTeam(String name) {
    return new Team(name)
  }
}