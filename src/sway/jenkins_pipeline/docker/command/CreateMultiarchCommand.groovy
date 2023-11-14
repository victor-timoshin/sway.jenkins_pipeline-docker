package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.entity.ImageEntity

class CreateMultiarchCommand implements Command {

  public ImageEntity entity

  CreateMultiarchCommand(ImageEntity entity) {
    this.entity = entity
  }
  
}

