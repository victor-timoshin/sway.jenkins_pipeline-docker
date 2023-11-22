package sway.jenkins_pipeline.docker

import spock.lang.Specification
import java.util.Optional
import sway.jenkins_pipeline.docker.shell.Response
import sway.jenkins_pipeline.docker.shell.ExecuteResponse
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.ScriptExecutor
import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.model.OSType
import sway.jenkins_pipeline.docker.model.ArchitectureType
import sway.jenkins_pipeline.docker.model.TargetPlatform
import sway.jenkins_pipeline.docker.entity.Entity
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.query.Query
import sway.jenkins_pipeline.docker.query.ImageInspectQuery
import sway.jenkins_pipeline.docker.query.ImageInspectQueryHandler

class ImageInspectQueryHandlerTest extends Specification {
  def "status returns succeed"() {
    setup:
    ImageEntity imgEntity = new ImageEntity("mynamespace", "myname", "mytag-arm64", new TargetPlatform(OSType.LINUX, ArchitectureType.AARCH64))
    ImageInspectQuery imgQuery = new ImageInspectQuery(imgEntity)

    Executor executor = new ScriptExecutor("/Applications/Docker.app/Contents/Resources/bin")
    ImageInspectQueryHandler imgQueryHandler = new ImageInspectQueryHandler(executor)

    when:
    Map<String, String> result = imgQueryHandler.handle(imgQuery)
    imgEntity.setId(result.id)

    then:
    // result.repo == "sway/module_core"
    result.repo == null
  }
}
