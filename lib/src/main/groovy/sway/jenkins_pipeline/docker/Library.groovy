package sway.jenkins_pipeline.docker

class Library {
  private Image image_

  boolean someLibraryMethod() {
    this.image_ = new Image("./", "default", "latest")

    println this.image_.name()

    true
  }
}
