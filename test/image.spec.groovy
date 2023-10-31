package sway.jenkins_pipeline.docker

import spock.lang.Specification

class ImageTest extends Specification {
    def "name returns default:latest"() {
        setup:
        def img = new MyImage("./", "default", "latest")

        when:
        def result = img.name()

        then:
        result == "default:latest"
    }
}
