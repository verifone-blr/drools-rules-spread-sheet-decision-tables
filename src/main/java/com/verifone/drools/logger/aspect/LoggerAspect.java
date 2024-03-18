package com.verifone.drools.logger.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verifone.drools.logger.aspect.DroolsInOut.PayloadDirection;

@Aspect
@Configuration
public class LoggerAspect {

   @Autowired
   private ApplicationContext applicationContext;

   @Autowired
   private ObjectMapper objectMapper;

   @Around("@annotation(droolsInOut)")
   public Object logSwiggyUserInOutMessage(ProceedingJoinPoint joinPoint, DroolsInOut droolsInOut) throws Throwable {
      GenericLogger logger = applicationContext.getBean(droolsInOut.logger(), GenericLogger.class);
      ExpressionParser expressionParser = applicationContext.getBean("expressionParser", ExpressionParser.class);

      StandardEvaluationContext evaluationContext = applicationContext.getBean("evalutionContext",
            StandardEvaluationContext.class);

      evaluationContext.setVariable("args", joinPoint.getArgs());

      Expression expression = expressionParser.parseExpression(droolsInOut.request());

      Object requestObject = expression.getValue(evaluationContext, joinPoint.getArgs());

      PayloadDirection payloadDirection = PayloadDirection.OUT.equals(droolsInOut.direction()) ? PayloadDirection.OUT
            : PayloadDirection.IN;

      if (PayloadDirection.OUT.equals(payloadDirection)) {
         String jsonStringRequest = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestObject);
         logger.inbound(jsonStringRequest);
      }

      payloadDirection = PayloadDirection.OUT.equals(droolsInOut.direction()) ? PayloadDirection.IN
            : PayloadDirection.OUT;
      
      Object responseObject = joinPoint.proceed();
      if (PayloadDirection.IN.equals(payloadDirection)) {
         String jsonStringResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseObject);
         logger.outbound(jsonStringResponse);
      }
      return responseObject;
   }

}
