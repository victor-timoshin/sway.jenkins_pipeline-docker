package sway.jenkins_pipeline.docker.strategy

import groovy.json.JsonSlurper
import sway.jenkins_pipeline.docker.entity.ContainerEntity
import sway.jenkins_pipeline.docker.model.ContainerResponse

class SingleContainerInspector implements ContainerInspector {

  public ContainerResponse inspect(String dockerPath, ContainerEntity container) {
    def outStream = new StringBuilder()
    def errStream = new StringBuilder()

    def outFormat = "{\"id\":\"{{.Id}}\",\"status\":\"{{.State.Status}}\"}"

    def proc = "${dockerPath}/docker inspect --format \"${outFormat}\" ${container.descriptor().name}".execute()
    proc.waitForProcessOutput(outStream, errStream)

    def response
    if (errStream.toString().isEmpty()) {
      def json = new JsonSlurper()
      def data = outStream.substring(1, outStream.length() - 1)
      response = new ContainerResponse(json.parseText(data))
    } else {
      response = new ContainerResponse([:])
    }

    return response
  }

}
