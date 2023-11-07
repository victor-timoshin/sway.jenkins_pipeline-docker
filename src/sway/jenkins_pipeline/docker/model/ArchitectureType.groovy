package sway.jenkins_pipeline.docker.model

enum ArchitectureType {

  X64("x64", "amd64"),
  AARCH64("aarch64", "arm64")

  public final String classifier

  public final String alias

  private ArchitectureType(String classifier, String alias) {
    this.classifier = classifier
    this.alias = alias
  }

  public static ArchitectureType canonicalize(String arch) {
    switch (arch) {
      case ["x64", "amd64", "x86_64"]: return X64
      case ["aarch64", "arm64", "arm64/v8"]: return AARCH64
      default: throw new IllegalArgumentException("Architecture type [${arch}] is unknown or not supported")
    }
  }

  public static ArchitectureType current() {
    return ArchitectureType.canonicalize(System.getProperty("os.arch"))
  }

}
