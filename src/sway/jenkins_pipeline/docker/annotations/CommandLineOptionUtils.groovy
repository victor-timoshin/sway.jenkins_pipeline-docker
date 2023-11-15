package sway.jenkins_pipeline.docker.annotations

import java.lang.reflect.Field
import java.lang.annotation.Annotation
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.Target
import java.util.Optional
import java.util.function.BinaryOperator
import com.cloudbees.groovy.cps.NonCPS

class CommandLineOptionUtils {

  @NonCPS
  public static <T> boolean isValid(Field field, Object object, Class<T> objectClass) {
    return (!field.getAnnotation(CommandLineOption).excluded() && objectClass.isAssignableFrom(field.get(object).getClass()))
  }

  @NonCPS
  public static <T> Optional<CommandLineOptionDescriptor<T>> getDescriptor(Field field, Object object, Class<T> objectClass) {
    if (!CommandLineOptionUtils.isValid(field, object, objectClass)) {
      return Optional.empty()
    }

    field.setAccessible(true)

    return Optional.of(new CommandLineOptionDescriptor<T>(
      field.getAnnotation(CommandLineOption).name(),
      field.get(object)
    ))
  }

  @NonCPS
  public static Set<Field> findFields(Object object) {
    Set<Field> fieldSet = new HashSet<>()
    for (Field field : object.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(CommandLineOption.class)) {
        fieldSet.add(field)
      }
    }

    return fieldSet
  }

  @NonCPS
  public static Field findField(Object object, String name) {
    return object.getClass().getDeclaredField(name)
  }

}
