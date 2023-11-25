package sway.jenkins_pipeline.docker.shell

import com.cloudbees.groovy.cps.NonCPS

class ExecuteResponse implements Response {

  private final int exitCode

  ExecuteResponse(int code) {
    this.exitCode = code
  }

  @NonCPS
  @Override
  public int getCode() {
    return this.exitCode
  }

}
