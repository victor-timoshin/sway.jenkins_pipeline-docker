package sway.jenkins_pipeline.docker.annotations

public final class CommandLineOptionDescriptor<TCommandLineOptionData> {

  String name

  TCommandLineOptionData data

  CommandLineOptionDescriptor(String name, TCommandLineOptionData data) {
    this.name = name
    this.data = data
  }

}
