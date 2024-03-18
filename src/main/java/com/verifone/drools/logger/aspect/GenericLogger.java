package com.verifone.drools.logger.aspect;

import org.apache.logging.log4j.Logger;

import com.verifone.drools.logger.DroolsLogger;

public class GenericLogger {

      private final Logger logger;

      public GenericLogger(Logger logger) {
         this.logger = logger;
      }

      public Logger getLogger() {
         return logger;
      }

      public void outbound(String message) {
         DroolsLogger.outbound(message, logger);
      }

      public void inbound(String message) {
         DroolsLogger.inbound(message, logger);
      }

   }