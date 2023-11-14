package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.entity.Entity
import sway.jenkins_pipeline.docker.entity.ImageEntity

class PushEntityCommand implements Command {

  public Entity entity

  PushEntityCommand(Entity entity) {
    this.entity = entity
  }
  
}

