package sway.jenkins_pipeline.docker.strategy

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import sway.jenkins_pipeline.docker.entity.ContainerEntity
import sway.jenkins_pipeline.docker.model.ContainerResponse

class Scenario {
  private static final THREAD_POOL = Executors.newCachedThreadPool()

  static Future<String> readStreamAsync(InputStream inputStream) {
    return THREAD_POOL.submit({ inputStream.text } as Callable) as Future<String>
  }

  static def getStdout(String command) {
    def process = command.execute()

    def stdoutFuture = readStreamAsync(process.inputStream)
    def stderrFuture = readStreamAsync(process.errorStream)

    def returnCode = process.waitFor()
    def stdout = stdoutFuture.get()
    def stderr = stderrFuture.get()

    if (returnCode != 0) {
      println "> $command failed with return code: $returnCode\n$stderr"
    }

    return stdout
  }
}

class MultipleContainerInspector implements ContainerInspector {

  public ContainerResponse inspect(String dockerPath, ContainerEntity cntr) {

    // println Scenario.getStdout("${dockerPath}/docker buildx inspect ${container.name}1")

    // def outStream = new StringBuilder()
    // def errStream = new StringBuilder()

    // def proc = "${dockerPath}/docker buildx inspect ${container.descriptor().name}".execute()
    // proc.waitForProcessOutput(outStream, errStream)

    def response
    // if (errStream.toString().isEmpty()) {
    //   def status
    //   outStream.eachLine {
    //     if (it.startsWith("Status")) {
    //       status = it.replaceAll("\\s","").split(":")[1]
    //       println "out> $status"
    //     }
    //   }

      // response = new ContainerResponse(["status":status])
    // } else {
      response = new ContainerResponse([:])
  //  }

    return response
  }

}
