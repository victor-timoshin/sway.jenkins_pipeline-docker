#!/usr/bin/env groovy

import sway.jenkins_pipeline.docker.*

def booleanToCMakeStr(Boolean val) {
  return (val) ? "ON" : "OFF"
}

return this
