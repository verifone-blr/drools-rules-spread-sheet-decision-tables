package com.verifone.drools.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DroolsLogger {

   private static final String NEWLINE = "\n";

   private static Logger errorLogger;

   private static Logger serverLogger;

   private static Logger droolsLogger;

   static {
      errorLogger = LogManager.getLogger("error.logger");
      serverLogger = LogManager.getLogger("server.logger");
      droolsLogger = LogManager.getLogger("drools.logger");
   }

   private enum LogLevels {
      FATAL,
      ERROR,
      WARN,
      INFO,
      DEBUG,
      TRACE;
   }

   private static void log(LogLevels logLevel, String message, Exception exception) {
      switch (logLevel) {
         case INFO:
            droolsLogger.info(message);
            break;
         case DEBUG:
            serverLogger.debug(message);
            break;
         case WARN:
            errorLogger.warn(message, exception);
            break;
         case ERROR:
            errorLogger.error(message, exception);
            break;
         default:
            break;
      }
   }

   public static void debug(String message) {
      log(LogLevels.DEBUG, message, null);
   }

   public static void info(String message) {
      log(LogLevels.INFO, message, null);
   }

   public static void trace(String message) {
      log(LogLevels.TRACE, message, null);
   }

   public static void warn(String message, Exception exception) {
      log(LogLevels.WARN, message, exception);
   }

   public static void error(String message, Exception exception) {
      log(LogLevels.ERROR, message, exception);
   }

   public static void droolsInOutMessage(String message) {
      droolsLogger.info(message);
   }

   public static void inbound(String message, Logger logger) {
      StringBuilder builder = new StringBuilder();
      builder.append("<<<<<<<<<<<<<<<<<<<<<<< INCOMING REQUEST STARTS <<<<<<<<<<<<<<<<<<<<<<<").append(NEWLINE);
      builder.append(message).append(NEWLINE);
      builder.append("<<<<<<<<<<<<<<<<<<<<<<< INCOMING REQUEST ENDS <<<<<<<<<<<<<<<<<<<<<<<").append(NEWLINE);
      logger.info(builder.toString());
   }

   public static void outbound(String message, Logger logger) {
      StringBuilder builder = new StringBuilder();
      builder.append(">>>>>>>>>>>>>>>>>>>>>>> OUTGOING REQUEST STARTS >>>>>>>>>>>>>>>>>>>>>>>").append(NEWLINE);
      builder.append(message).append(NEWLINE);
      builder.append(">>>>>>>>>>>>>>>>>>>>>>> OUTGOING REQUEST ENDS >>>>>>>>>>>>>>>>>>>>>>>").append(NEWLINE);
      logger.info(builder.toString());
   }

}
