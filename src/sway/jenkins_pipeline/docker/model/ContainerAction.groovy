package sway.jenkins_pipeline.docker.model

enum ContainerAction {

  START("start"),
  STOP("stop")

  public final String name

  private ContainerAction(String name) {
    this.name = name
  }

}
