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
import sway.jenkins_pipeline.docker.command.Command
import sway.jenkins_pipeline.docker.command.BuildImageCommand
import sway.jenkins_pipeline.docker.command.BuildImageCommandHandler
import sway.jenkins_pipeline.docker.command.CommandResult

class BuildImageCommandTest extends Specification {
  def "status returns succeed"() {
    setup:
    String dockerFile = "/Dockerfile"
    Map<String, String> envs = [:]
    Map<String, String> args = ["tests":"false", "coverage":"false"]
    ImageEntity imageEntity = new ImageEntity("myname", "mytag", new TargetPlatform(OSType.LINUX, ArchitectureType.X64))
    BuildImageCommand imageCommand = new BuildImageCommand(imageEntity, ".", dockerFile, envs, args, "module_x-release")

    Response response = Stub(Response)
    response.getCode() >> 0

    // Executor executor = new ScriptExecutor("/Applications/Docker.app/Contents/Resources/bin")
    Executor executor = Stub(Executor) {
      execute(_ as ScriptBuilder) >> response

      getOutString() >> "out"
      getErrString() >> "err"
    }

    def imageCommandHandler = new BuildImageCommandHandler(executor)

    when:
    def result = imageCommandHandler.handle(imageCommand)

    then:
    result.succeeded == true
  }
}
