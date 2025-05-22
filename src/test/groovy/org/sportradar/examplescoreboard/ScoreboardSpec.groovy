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

  def "Should display summary in correct order based on total score and start time"() {
    given: "A scoreboard with multiple matches"
    def scoreboard = new Scoreboard()

    and: "Several matches are started and scores updated"
    // a. Mexico - Canada: 0 – 5
    def matchMC = new Match(aTeam("Mexico"), aTeam("Canada"))
    scoreboard.startNew(matchMC)
    scoreboard.updateScore(matchMC.getId(), new Score(0, 5))

    // b. Spain - Brazil: 10 – 2
    def matchSB = new Match(aTeam("Spain"), aTeam("Brazil"))
    scoreboard.startNew(matchSB)
    scoreboard.updateScore(matchSB.getId(), new Score(10, 2))

    // c. Germany - France: 2 – 2
    def matchGF = new Match(aTeam("Germany"), aTeam("France"))
    scoreboard.startNew(matchGF)
    scoreboard.updateScore(matchGF.getId(), new Score(2, 2))

    // d. Uruguay - Italy: 6 – 6
    def matchUI = new Match(aTeam("Uruguay"), aTeam("Italy"))
    scoreboard.startNew(matchUI)
    scoreboard.updateScore(matchUI.getId(), new Score(6, 6))

    // e. Argentina - Australia: 3 - 1
    def matchAA = new Match(aTeam("Argentina"), aTeam("Australia"))
    scoreboard.startNew(matchAA)
    scoreboard.updateScore(matchAA.getId(), new Score(3, 1))

    when: "Getting the summary of the scoreboard"
    def summary = scoreboard.getSummary()

    then: "The summary should be ordered by total score and then by most recent addition"
    summary.size() == 5
    summary.collect {
      [
          it.home.name,
          it.away.name,
          it.score.home(),
          it.score.away(),
          it.totalScore()
      ]
    } == [
        ["Uruguay", "Italy", 6, 6, 12],
        ["Spain", "Brazil", 10, 2, 12],
        ["Mexico", "Canada", 0, 5, 5],
        ["Argentina", "Australia", 3, 1, 4],
        ["Germany", "France", 2, 2, 4]
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

  def "Should remove match from scoreboard when finished"() {
    given:
    def scoreboard = new Scoreboard()
    def match = new Match(aTeam("Poland"), aTeam("Germany"))
    scoreboard.startNew(match)

    when:
    scoreboard.finish(match.getId())

    then:
    scoreboard.getSummary().size() == 0
  }

  def "Should throw exception when finishing non-existing match"() {
    given:
    def scoreboard = new Scoreboard()
    def nonExistingId = UUID.randomUUID()

    when:
    scoreboard.finish(nonExistingId)

    then:
    thrown(NoSuchElementException)
  }

  Team aTeam(String name) {
    return new Team(name)
  }
}