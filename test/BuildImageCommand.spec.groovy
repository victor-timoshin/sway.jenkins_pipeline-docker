package sway.jenkins_pipeline.docker

import spock.lang.Specification
import sway.jenkins_pipeline.docker.model.OSType
import sway.jenkins_pipeline.docker.model.ArchitectureType
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.model.TargetPlatform
import sway.jenkins_pipeline.docker.command.BuildImageCommand
import sway.jenkins_pipeline.docker.command.BuildImageCommandHandler
import sway.jenkins_pipeline.docker.command.CommandResult

class BuildImageCommandTest extends Specification {
  def "status returns succeed"() {
    setup:
    def dockerfile = "/Users/apriori85/Documents/Projects/sway.jenkins_pipeline-docker/Dockerfile"
    def buildArgs = ["tests":"false", "coverage":"false"]
    def image = new ImageEntity("myname", "mytag", new TargetPlatform(OSType.LINUX, ArchitectureType.X64))
    def imageCommand = new BuildImageCommand(image.nameWithTag(), image.platform, dockerfile, buildArgs)
    def imageCommandExecutablePath = "/Applications/Docker.app/Contents/Resources/bin"
    def imageCommandHandler = new BuildImageCommandHandler(imageCommandExecutablePath)

    when:
    def result = imageCommandHandler.execute(imageCommand)

    then:
    result.succeeded == true
  }
}
