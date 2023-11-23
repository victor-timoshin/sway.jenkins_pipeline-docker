package sway.jenkins_pipeline.docker

import spock.lang.Specification
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
import sway.jenkins_pipeline.docker.entity.MultiarchImageEntity
import sway.jenkins_pipeline.docker.command.Command
import sway.jenkins_pipeline.docker.command.CreateMultiarchImageCommand
import sway.jenkins_pipeline.docker.command.CreateMultiarchImageCommandHandler
import sway.jenkins_pipeline.docker.command.CommandResult

class CreateMultiarchImageCommandTest extends Specification {
  def "status returns succeed"() {
    setup:
    String IMAGE_NAMESPACE = "mynamespace"
    String IMAGE_NAME = "myname"
    String IMAGE_TAG = "mytag"
    List<ImageEntity> imageEntities = [
      new ImageEntity(IMAGE_NAMESPACE, IMAGE_NAME, IMAGE_TAG, new TargetPlatform(OSType.LINUX, ArchitectureType.AARCH64)),
      new ImageEntity(IMAGE_NAMESPACE, IMAGE_NAME, IMAGE_TAG, new TargetPlatform(OSType.LINUX, ArchitectureType.X64))
    ]

    MultiarchImageEntity multiarchImageEntity = new MultiarchImageEntity(IMAGE_NAMESPACE, IMAGE_NAME, IMAGE_TAG)
    CreateMultiarchImageCommand multiarchImageCommand = new CreateMultiarchImageCommand(multiarchImageEntity, imageEntities)

    Response response = Stub(Response)
    response.getCode() >> 0

    Executor executor = Stub(Executor) {
      execute(_ as ScriptBuilder) >> response

      getOutString() >> "out"
      getErrString() >> "err"
    }

    def multiarchImageCommandHandler = new CreateMultiarchImageCommandHandler(executor)

    when:
    def result = multiarchImageCommandHandler.handle(multiarchImageCommand)

    then:
    result.succeeded == true
  }
}