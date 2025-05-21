package org.sportradar.examplescoreboard

import spock.lang.Specification

class SimpleSpockTest extends Specification {

  def "ensure basic arithmetic works"() {
    given: "two numbers"
    int a = 5
    int b = 10

    when: "they are added"
    int sum = a + b

    then: "the result is the correct sum"
    sum == 15
  }

  def "ensure true is true"() {
    expect:
    true == true
  }
}