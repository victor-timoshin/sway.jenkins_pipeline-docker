package sway.jenkins_pipeline.docker.annotations

public final class CommandLineOptionDescriptor<T> {

  String name

  T data

  CommandLineOptionDescriptor(String name, T data) {
    this.name = name
    this.data = data
  }

}
