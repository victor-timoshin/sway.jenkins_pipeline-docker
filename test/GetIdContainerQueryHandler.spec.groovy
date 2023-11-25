package sway.jenkins_pipeline.docker

import spock.lang.Specification
import java.util.Optional
import sway.jenkins_pipeline.docker.shell.Response
import sway.jenkins_pipeline.docker.shell.ExecuteResponse
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.ScriptExecutor
import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.entity.ContainerEntity
import sway.jenkins_pipeline.docker.query.Query
import sway.jenkins_pipeline.docker.query.GetIdContainerQuery
import sway.jenkins_pipeline.docker.query.GetIdContainerQueryHandler

class GetIdContainerQueryHandlerTest extends Specification {
  def "status returns succeed"() {
    setup:
    ContainerEntity container = new ContainerEntity("cntr")
    GetIdContainerQuery containerQry = new GetIdContainerQuery(container)

    Executor executor = new ScriptExecutor("/Applications/Docker.app/Contents/Resources/bin")
    GetIdContainerQueryHandler containerQryHandler = new GetIdContainerQueryHandler(executor)

    when:
    def result = containerQryHandler.handle(containerQry)

    then:
    result == null
  }
}
