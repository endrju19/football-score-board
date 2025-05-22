package org.sportradar.examplescoreboard

import spock.lang.Specification

class ScoreSpec extends Specification {

  def "Should throw exception when creating score with negative values"() {
    when:
    new Score(home, away)

    then:
    thrown(IllegalArgumentException)

    where:
    home | away
    -1   | 0
    0    | -1
    -1   | -1
  }

}
