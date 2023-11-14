package sway.jenkins_pipeline.docker.entity

class Entity {

  public String uid

  public final String name

  Entity(String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Name can't be empty")
    }

    this.name = name
  }

}
