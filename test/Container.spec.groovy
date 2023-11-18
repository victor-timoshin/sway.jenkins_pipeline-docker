package sway.jenkins_pipeline.docker

import spock.lang.Specification
import sway.jenkins_pipeline.docker.model.OSType
import sway.jenkins_pipeline.docker.model.ArchitectureType
import sway.jenkins_pipeline.docker.entity.ContainerEntity
import sway.jenkins_pipeline.docker.model.TargetPlatform

class ContainerTest extends Specification {

  def "name returns sway"() {
    setup:
    def container = new ContainerEntity("sway")

    when:
    def name = container.name

    then:
    name == "sway"
  }

}
