package sway.jenkins_pipeline.docker.model

enum OSType {

  LINUX("linux"),
  WIN("windows")

  public final String name

  private OSType(String name) {
    this.name = name
  }

  public static OSType detect() {
    return LINUX
  }

}
