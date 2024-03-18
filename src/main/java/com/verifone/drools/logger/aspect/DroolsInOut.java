package com.verifone.drools.logger.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DroolsInOut {

   String request();

   PayloadDirection direction() default PayloadDirection.OUT;

   String logger();

   public enum PayloadDirection {
      OUT,
      IN
   }
}