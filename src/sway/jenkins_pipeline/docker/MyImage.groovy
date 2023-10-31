package sway.jenkins_pipeline.docker

class MyImage implements MyEntity {
  private String docker_
  private MyImageName imageName_

  MyImage(String dockerPath, String name, String tag) {
    this.docker_ = "${dockerPath}/docker"
    this.imageName_ = new MyImageName(name, tag)
  }

  // override
  String name() {
    return "${this.imageName_.name()}:${this.imageName_.tag()}"
  }

  String id(step) {
    return step.sh(
      script: "${this.docker_} images --filter=reference=${this.name()} --format {{.ID}}",
      returnStdout: true
    ).trim()
  }
}
