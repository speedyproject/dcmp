package org.nicosoft.config.support.utils;

/**
 * Logger support
 *
 * @author nico
 * @since 2017.8.28
 */
public class Logger {

    private static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(Logger.class);

    /**
     * Try to format messages using java Formatter. Fall back to the plain
     * message if error.
     */
    static String format(String msg, Object... args) {
        try {
            if (args != null && args.length > 0) {
                return String.format(msg, args);
            }
            return msg;
        } catch (Exception e) {
            return msg;
        }
    }

    /**
     * Info about the logger caller
     */
    static class CallInfo {

        public String className;
        public String methodName;

        public CallInfo() {
        }

        public CallInfo(String className, String methodName) {
            this.className = className;
            this.methodName = methodName;
        }
    }

    /**
     * @return the className of the class actually logging the message
     */
    static String getCallerClassName() {
        final int level = 5;
        return getCallerClassName(level);
    }

    /**
     * @return the className of the class actually logging the message
     */
    static String getCallerClassName(final int level) {
        CallInfo ci = getCallerInformations(level);
        return ci.className;
    }

    /**
     * Examine stack trace to get caller
     *
     * @param level method stack depth
     * @return who called the logger
     */
    static CallInfo getCallerInformations(int level) {
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StackTraceElement caller = callStack[level];
        return new CallInfo(caller.getClassName(), caller.getMethodName());
    }

    /**
     * Log with TRACE level
     *
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void trace(String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).trace(format(message, args));
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with DEBUG level
     *
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void debug(String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).debug(format(message, args));
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with DEBUG level
     *
     * @param e       the exception to log
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void debug(Throwable e, String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).debug(format(message, args), e);
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with INFO level
     *
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void info(String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).info(format(message, args));
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with INFO level
     *
     * @param e       the exception to log
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void info(Throwable e, String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).info(format(message, args), e);
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with WARN level
     *
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void warn(String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).warn(format(message, args));
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with WARN level
     *
     * @param e       the exception to log
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void warn(Throwable e, String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).warn(format(message, args), e);
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with ERROR level
     *
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void error(String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).error(format(message, args));
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with ERROR level
     *
     * @param e       the exception to log
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void error(Throwable e, String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).error(format(message, args), e);
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with FATAL level
     *
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void fatal(String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).fatal(format(message, args));
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with FATAL level
     *
     * @param e       the exception to log
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void fatal(Throwable e, String message, Object... args) {
        try {
            org.apache.log4j.Logger.getLogger(getCallerClassName()).fatal(format(message, args), e);
        } catch (Throwable ex) {
            log4j.error("Oops. Error in Logger !", ex);
        }
    }

}
