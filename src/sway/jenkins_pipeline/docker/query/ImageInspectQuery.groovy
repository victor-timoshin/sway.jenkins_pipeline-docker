package sway.jenkins_pipeline.docker.query

import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ImageEntity

class ImageInspectQuery implements Query {

  @CommandLineOption(name = "quiet", required = true)
  public String imageRefName

  @CommandLineOption(name = "format", required = true)
  public String format = "{\"id\":\"{{.ID}}\",\"repo\":\"{{.Repository}}\"}"

  ImageInspectQuery(ImageEntity image) {
    this.imageRefName = image.getReferenceName()
  }

}
