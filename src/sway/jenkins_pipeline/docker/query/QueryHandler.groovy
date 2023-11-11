package sway.jenkins_pipeline.docker.query

public interface QueryHandler<TQuery extends Query, TReturn> {

  TReturn handle(TQuery query)
  
}
