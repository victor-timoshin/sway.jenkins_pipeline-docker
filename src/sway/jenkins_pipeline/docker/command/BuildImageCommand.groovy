package sway.jenkins_pipeline.docker.command

import java.util.Optional
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.model.TargetPlatform
import sway.jenkins_pipeline.docker.entity.ImageEntity

class BuildImageCommand implements Command {

  @CommandLineOption(name = "tag", required = true)
  public String imgReferenceName

  @CommandLineOption(name = "no-cache", skipped = true)
  public boolean noCache

  @CommandLineOption(name = "pull", skipped = true)
  public boolean pull

  @CommandLineOption(name = "rm", skipped = true)
  public boolean rm

  @CommandLineOption(name = "progress")
  public String progress

  @CommandLineOption(name = "network")
  public String network

  @CommandLineOption(skipped = true)
  public String dockerWorkspace

  @CommandLineOption(name = "file", workspaceDir = true)
  public String dockerFile

  @CommandLineOption(name = "env")
  public Map<String, String> environments

  @CommandLineOption(name = "build-arg")
  public Map<String, String> arguments

  @CommandLineOption(name = "target")
  public Object target

  BuildImageCommand(ImageEntity image, String dockerWorkspace, String dockerFile, 
    Map<String, String> environments, Map<String, String> arguments, String target) {

    this.imgReferenceName = image.nameWithArchTag(true)
    this.noCache = true
    this.pull = true
    this.rm = true
    this.progress = "plain"
    this.network = "host"
    this.dockerWorkspace = dockerWorkspace
    this.dockerFile = dockerFile
    this.environments = environments
    this.arguments = arguments
    this.arguments.put("TARGET_PLATFORM_ARCH", image.platform.arch.alias)
    this.arguments.put("TARGET_PLATFORM_OS", image.platform.os.name)
    this.arguments.put("TARGET_PLATFORM", image.platform.os.name + "/" + image.platform.arch.alias)
    this.target = target
  }

}
