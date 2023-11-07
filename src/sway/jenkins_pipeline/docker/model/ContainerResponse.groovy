package sway.jenkins_pipeline.docker.model

class ContainerResponse {

  public String id

  public String status

  private static getValueOrNull(Map<String, String> map, String key) {
    return map.containsKey(key) ? map[key] : null
  }

  ContainerResponse(Map<String, String> map) {
    this.id = getValueOrNull(map, "id")
    this.status = getValueOrNull(map, "status")
  }

}
