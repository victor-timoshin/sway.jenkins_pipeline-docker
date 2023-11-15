package sway.jenkins_pipeline.docker.annotations

import java.lang.reflect.Field
import java.lang.annotation.Annotation
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.Target

@Target([ElementType.FIELD])
@Retention(RetentionPolicy.RUNTIME)
@interface CommandLineOption {

  boolean workspaceDir() default false

  boolean excluded() default false

  String name() default ""

}
