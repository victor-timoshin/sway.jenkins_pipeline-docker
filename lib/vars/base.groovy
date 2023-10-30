#!/usr/bin/env groovy

import sway.jenkins_pipeline.docker

def booleanToCMakeStr(Boolean val) {
  return (val) ? "ON" : "OFF"
}

Image createImage(String dockerPath, String name, String tag) {
  new Image(dockerPath, name, tag)
}

return this
