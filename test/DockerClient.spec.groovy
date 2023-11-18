package sway.jenkins_pipeline.docker

import spock.lang.Specification
import spock.lang.Unroll
import sway.jenkins_pipeline.docker.model.OSType
import sway.jenkins_pipeline.docker.model.ArchitectureType
import sway.jenkins_pipeline.docker.strategy.ContainerInspector
import sway.jenkins_pipeline.docker.strategy.SingleContainerInspector
import sway.jenkins_pipeline.docker.strategy.MultipleContainerInspector

class DockerClientTest extends Specification {

  def "status returns null"() {
    setup:
    def client = new DockerClient("/Applications/Docker.app/Contents/Resources/bin")
    client.createContainer("sway-cntr")

    // def inspector = Stub(ContainerInspector)
    // inspector.inspect(_, _) >> ["status":null]

    def inspector = new MultipleContainerInspector()

    when:
    def response = client.inspect(inspector)

    then:
    response.status == null
  }

}
