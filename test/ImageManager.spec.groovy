package sway.jenkins_pipeline.docker

import spock.lang.Specification
import sway.jenkins_pipeline.docker.model.OSType
import sway.jenkins_pipeline.docker.model.ArchitectureType
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.model.TargetPlatform

class ImageTest extends Specification {
  def "name returns default:latest"() {
    setup:
    def img = new ImageEntity("default", "latest", new TargetPlatform(OSType.LINUX, ArchitectureType.X64))

    when:
    def result = img.nameWithTag()

    then:
    result == "default:latest"
  }
}
