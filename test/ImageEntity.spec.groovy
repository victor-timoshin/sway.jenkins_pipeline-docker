package sway.jenkins_pipeline.docker

import spock.lang.Specification
import sway.jenkins_pipeline.docker.model.OSType
import sway.jenkins_pipeline.docker.model.ArchitectureType
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.model.TargetPlatform

class ImageEntityTest extends Specification {
  def "name returns mynamespace/myname:mytag"() {
    setup:
    def img = new ImageEntity("mynamespace", "myname", "mytag", new TargetPlatform(OSType.LINUX, ArchitectureType.X64))

    when:
    def result = img.nameWithTag(true)

    then:
    result == "mynamespace/myname:mytag"
  }
}
