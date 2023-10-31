#!/usr/bin/env groovy

import sway.jenkins_pipeline.docker.*

def booleanToCMakeStr(Boolean val) {
  return (val) ? "ON" : "OFF"
}

MyImage createImage(String dockerPath, String name, String tag) {
  new MyImage(dockerPath, name, tag)
}

return this
