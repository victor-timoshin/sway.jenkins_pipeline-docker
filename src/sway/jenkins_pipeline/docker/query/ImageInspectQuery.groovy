package sway.jenkins_pipeline.docker.query

import sway.jenkins_pipeline.docker.annotations.CommandLineOption

class ImageInspectQuery implements Query {

  @CommandLineOption(name = "quiet", required = true)
  public String reference

  @CommandLineOption(name = "format", required = true)
  public String format

  ImageInspectQuery(String reference) {
    this.reference = reference
    this.format = "{\"id\":\"{{.ID}}\",\"repo\":\"{{.Repository}}\"}"
  }

}
