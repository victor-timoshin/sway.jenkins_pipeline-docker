#!/usr/bin/env groovy

import sway.jenkins_pipeline.docker.model.OSType
import sway.jenkins_pipeline.docker.model.ArchitectureType
import sway.jenkins_pipeline.docker.model.TargetPlatform
import sway.jenkins_pipeline.docker.entity.ImageEntity

def call(body) {
  echo "Call image"

  def img = new ImageEntity("default", "latest", new TargetPlatform(OSType.LINUX, ArchitectureType.AARCH64))
  script:this.echo(img.name)

  return this
}
