package sway.jenkins_pipeline.docker.shell

import java.util.Optional

interface ScriptBuilderable {

  Optional<ScriptBuilder> getScriptBuilder()

}
