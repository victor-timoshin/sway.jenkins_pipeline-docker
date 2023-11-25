package sway.jenkins_pipeline.docker.model

enum ContainerState {

  NONE("none"),
  CREATED("created"),
  RUNNING("running"),
  EXITED("exited")

  public final String status

  private ContainerState(String status) {
    this.status = status
  }

}
