#!/usr/bin/env groovy

import sway.jenkins_pipeline.docker.entity.ImageEntity

def call(body) {
  echo "Call image"

  def img = new ImageEntity("default", "latest")
  script:this.echo(img.name)

  return this
}
