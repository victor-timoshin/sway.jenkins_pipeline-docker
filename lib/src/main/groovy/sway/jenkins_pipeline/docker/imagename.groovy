package sway.jenkins_pipeline.docker

class ImageName {
  private String name_
  private String tag_

  ImageName(String name, String tag) {
    this.name_ = name
    this.tag_ = tag
  }

  def name() {
    this.name_
  }

  def tag() {
    this.tag_
  }
}
