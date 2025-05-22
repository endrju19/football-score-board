package org.sportradar.examplescoreboard

import spock.lang.Specification

class TeamSpec extends Specification {

  def "Should throw exception when team name is null"() {
    when:
    new Team(null)

    then:
    thrown(IllegalArgumentException)
  }

  def "Should throw exception when team name is blank"() {
    when:
    new Team("   ")

    then:
    thrown(IllegalArgumentException)
  }
}
