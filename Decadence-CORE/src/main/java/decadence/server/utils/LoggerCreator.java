package decadence.server.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import decadence.server.Decadence;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class LoggerCreator {

	private static final Properties SERVER_PROPERTIES = Decadence.getServerProperties();

	public static void setDebugLogger() {
		boolean debug = Boolean.parseBoolean(SERVER_PROPERTIES.getProperty("enable-debug", "false"));
		if (!debug) return;

		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		context.reset();

		ConsoleAppender<ILoggingEvent> consoleAppender = createConsoleAppender(context);
		consoleAppender.start();

		ch.qos.logback.classic.Logger rootLogger =
			(ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		rootLogger.setLevel(Level.DEBUG);
		rootLogger.addAppender(consoleAppender);
	}

	private static ConsoleAppender<ILoggingEvent> createConsoleAppender(LoggerContext context) {
		ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
		consoleAppender.setContext(context);
		consoleAppender.setName("CONSOLE");

		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(context);
		encoder.setPattern("[%d{HH:mm:ss} | %logger{36} | %-5level] %msg%n");
		encoder.start();

		consoleAppender.setEncoder(encoder);
		return consoleAppender;
	}
}
