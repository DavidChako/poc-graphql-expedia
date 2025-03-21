package com.icosahedron.graphql.test

import spock.lang.Specification

class HelloSpec extends Specification {
    def "hello"() {
        expect:
        println 'Hello'
    }
}
