package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.model.TargetPlatform

class BuildImageCommand implements Command {

  public BuildImageCommandLine line = new BuildImageCommandLine()

  BuildImageCommand(String referenceName, TargetPlatform platform, String dockerfile, Map<String, String> defines) {
    this.line.addReferenceName(referenceName)
    this.line.addDockerfile(dockerfile)
    this.line.addParameter("no-cache", null)
    this.line.addParameter("pull", null)
    this.line.addParameter("rm", null)
    this.line.addParameter("progress", "plain")
    this.line.addTarget("module_x-release")
    this.line.addDefine("TARGET_PLATFORM_OS", platform.os.name)
    this.line.addDefine("TARGET_PLATFORM_ARCH", platform.arch.alias)
    this.line.addDefine("TARGET_PLATFORM", platform.os.name + "/" + platform.arch.alias)
    defines.each { key, value -> this.line.addDefine(key, value) }
  }

}
