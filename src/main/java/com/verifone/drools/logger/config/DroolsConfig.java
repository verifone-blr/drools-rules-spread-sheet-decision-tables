package com.verifone.drools.logger.config;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.verifone.drools.logger.aspect.GenericLogger;

@Configuration
public class DroolsConfig {

   @Bean(name = "expressionParser")
   @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
   public ExpressionParser expressionParser() {
      return new SpelExpressionParser();
   }

   @Bean(name = "evalutionContext")
   @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
   public StandardEvaluationContext standardEvaluationContext(ApplicationContext applicationContext) {
      StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
      standardEvaluationContext.setBeanResolver(new BeanFactoryResolver(applicationContext));
      return standardEvaluationContext;
   }

   @Bean("drools.logger")
   public GenericLogger logger() {
      return new GenericLogger(LogManager.getLogger("drools.logger"));
   }
}
