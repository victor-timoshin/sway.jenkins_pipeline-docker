package sway.jenkins_pipeline.docker.query

import sway.jenkins_pipeline.docker.shell.ScriptBuilderable

interface QueryHandler<TQuery extends Query, TReturn> extends ScriptBuilderable  {

  TReturn handle(TQuery query)
  
}
