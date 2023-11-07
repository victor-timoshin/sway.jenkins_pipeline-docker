package sway.jenkins_pipeline.docker.model

class TargetPlatform {

  public OSType os

  public ArchitectureType arch

  TargetPlatform(OSType os, ArchitectureType arch) {
    this.os = os
    this.arch = arch
  }

}
