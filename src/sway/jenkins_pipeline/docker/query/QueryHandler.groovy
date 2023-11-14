package sway.jenkins_pipeline.docker.query

interface QueryHandler<TQuery extends Query, TReturn> {

  TReturn handle(TQuery query)
  
}
