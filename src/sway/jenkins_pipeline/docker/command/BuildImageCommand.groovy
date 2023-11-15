package sway.jenkins_pipeline.docker.command

import java.util.Optional
import java.lang.reflect.Field
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.model.TargetPlatform
import sway.jenkins_pipeline.docker.entity.ImageEntity

class BuildImageCommand implements Command {

  @CommandLineOption(name = "tag")
  public String reference

  @CommandLineOption(excluded = true)
  public String dockerWorkspace

  @CommandLineOption(name = "file", workspaceDir = true)
  public String dockerFile

  @CommandLineOption(name = "env-file", workspaceDir = true)
  public String dockerEnvFile

  @CommandLineOption(name = "env")
  public Map<String, String> environments

  @CommandLineOption(name = "build-arg")
  public Map<String, String> arguments

  @CommandLineOption(name = "target")
  public Object target

  BuildImageCommand(ImageEntity entity, String dockerWorkspace, String dockerFile, String dockerEnvFile, 
    Map<String, String> environments, Map<String, String> arguments, String target) {
    // this.line.addReferenceName(entity.nameWithTag() + "-" + entity.platform.arch.alias.replace("/", ""))
    // this.line.addDockerfile(dockerFile)
    // this.line.addParameter("no-cache", null)
    // this.line.addParameter("pull", null)
    // this.line.addParameter("rm", null)
    // this.line.addParameter("progress", "plain")
    // this.line.addTarget("module_x-release")
    // arguments.each { key, value -> this.line.addDefine(key, value) }

    this.reference = entity.nameWithTag() + "-" + entity.platform.arch.alias.replace("/", "")
    this.dockerWorkspace = dockerWorkspace
    this.dockerFile = dockerFile
    this.dockerEnvFile = dockerEnvFile
    this.environments = environments
    this.arguments = arguments
    this.target = target
  }

}
