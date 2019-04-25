package com.avaya.jtapi.tsapi.util;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class JTAPILoggingAdapter {
	private static boolean traceLoggingEnabled;
	private static boolean errorLoggingEnabled;
	private static boolean performanceLoggingEnabled;
	private static boolean somethingChanged = false;
	private static String traceLoggerLevel;
	private static String altTraceFile;
	private static String traceFileCount;
	static final String DEFAULT_TRACE_FILE_COUNT = "9";
	private static String traceFileSize;
	static final String DEFAULT_TRACE_FILE_SIZE = "50MB";
	private static String errorFile;
	private static String errorFileCount;
	static final String DEFAULT_ERROR_FILE_COUNT = "9";
	private static String errorFileSize;
	static final String DEFAULT_ERROR_FILE_SIZE = "50MB";
	private static String perfFile;
	private static String perfFileCount;
	static final String DEFAULT_PERFORMANCE_FILE_COUNT = "9";
	private static String perfFileSize;
	static final String DEFAULT_PERFORMANCE_FILE_SIZE = "50MB";
	static final String JTAPI_APPENDER = "org.apache.log4j.RollingFileAppender";
	static final String JTAPI_DEFAULT_APPENDER = "org.apache.log4j.ConsoleAppender";
	static final String JTAPI_APPENDER_LAYOUT = "org.apache.log4j.PatternLayout";
	static final String JTAPI_APPENDER_LAYOUT_PATTERN = "%d [%t] %-5p %c{1} - %m%n";
	static final String PROP_ALT_TRACE_FILE = "altTraceFile";
	static final String PROP_TRACE_FILE_COUNT = "traceFileCount";
	static final String PROP_TRACE_FILE_SIZE = "traceFileSize";
	static final String PROP_ERROR_FILE = "errorFile";
	static final String PROP_ERROR_FILE_COUNT = "errorFileCount";
	static final String PROP_ERROR_FILE_SIZE = "errorFileSize";
	static final String PROP_TRACE_LEVEL = "traceLoggerLevel";
	static final String PROP_PERFORMANCE_FILE = "perfFile";
	static final String PROP_PERFORMANCE_FILE_COUNT = "perfFileCount";
	static final String PROP_PERFORMANCE_FILE_SIZE = "perfFileSize";
	static final String PERFORMANCE_LOGGER = "log4j.logger.jtapi.performanceLogger";
	private static Hashtable<String, Boolean> propertyStatusTable = new Hashtable<String, Boolean>();

	private static void resetPropertyStatusTable() {
		propertyStatusTable.put("altTraceFile", Boolean.FALSE);
		propertyStatusTable.put("traceFileCount", Boolean.FALSE);
		propertyStatusTable.put("traceFileSize", Boolean.FALSE);
		propertyStatusTable.put("errorFile", Boolean.FALSE);
		propertyStatusTable.put("errorFileCount", Boolean.FALSE);
		propertyStatusTable.put("errorFileSize", Boolean.FALSE);
		propertyStatusTable.put("traceLoggerLevel", Boolean.FALSE);
		propertyStatusTable.put("perfFile", Boolean.FALSE);
		propertyStatusTable.put("perfFileCount", Boolean.FALSE);
		propertyStatusTable.put("perfFileSize", Boolean.FALSE);
	}

	private static void hasChanged(String oldValue, String newValue) {
		if ((oldValue == null) && (newValue == null))
			return;
		if ((oldValue != null) && (newValue == null))
			somethingChanged = true;
		else if ((oldValue == null) && (newValue != null))
			somethingChanged = true;
		else if (!oldValue.equalsIgnoreCase(newValue))
			somethingChanged = true;
	}

	public static void initializeLogging() {
		if (somethingChanged) {
			somethingChanged = false;
			Properties loggingProperties = new Properties();

			if ((isTraceLoggingEnabled()) || (isErrorLoggingEnabled())) {
				if (!isErrorLoggingEnabled())
					loggingProperties.put("log4j.logger.com.avaya.jtapi.tsapi",
							traceLoggerLevel + ",defaultAppender");
				else if (!isTraceLoggingEnabled())
					loggingProperties.put("log4j.logger.com.avaya.jtapi.tsapi",
							"ERROR,errorAppender");
				else {
					loggingProperties
							.put("log4j.logger.com.avaya.jtapi.tsapi",
									traceLoggerLevel
											+ ",defaultAppender,errorAppender");
				}
			}
			if (isTraceLoggingEnabled()) {
				if (altTraceFile != null) {
					loggingProperties.put("log4j.appender.defaultAppender",
							"org.apache.log4j.RollingFileAppender");
					loggingProperties
							.put("log4j.appender.defaultAppender.File",
									altTraceFile);

					if (traceFileCount != null)
						loggingProperties
								.put("log4j.appender.defaultAppender.MaxBackupIndex",
										String.valueOf(Integer
												.parseInt(traceFileCount) - 1));
					else {
						loggingProperties
								.put("log4j.appender.defaultAppender.MaxBackupIndex",
										"9");
					}
					if (traceFileSize != null)
						loggingProperties.put(
								"log4j.appender.defaultAppender.MaxFileSize",
								traceFileSize);
					else
						loggingProperties.put(
								"log4j.appender.defaultAppender.MaxFileSize",
								"50MB");
				} else {
					loggingProperties.put("log4j.appender.defaultAppender",
							"org.apache.log4j.ConsoleAppender");
					loggingProperties.put(
							"log4j.appender.defaultAppender.Target",
							"System.out");
				}

				loggingProperties.put("log4j.appender.defaultAppender.layout",
						"org.apache.log4j.PatternLayout");
				loggingProperties
						.put("log4j.appender.defaultAppender.layout.ConversionPattern",
								"%d [%t] %-5p %c{1} - %m%n");
			}

			if (isErrorLoggingEnabled()) {
				loggingProperties.put("log4j.appender.errorAppender",
						"org.apache.log4j.RollingFileAppender");
				loggingProperties.put("log4j.appender.errorAppender.File",
						errorFile);
				loggingProperties.put("log4j.appender.errorAppender.threshold",
						"ERROR");

				if (errorFileCount != null)
					loggingProperties
							.put("log4j.appender.errorAppender.MaxBackupIndex",
									String.valueOf(Integer
											.parseInt(errorFileCount) - 1));
				else {
					loggingProperties.put(
							"log4j.appender.errorAppender.MaxBackupIndex", "9");
				}
				if (errorFileSize != null)
					loggingProperties.put(
							"log4j.appender.errorAppender.MaxFileSize",
							errorFileSize);
				else
					loggingProperties.put(
							"log4j.appender.errorAppender.MaxFileSize", "50MB");
				loggingProperties.put("log4j.appender.errorAppender.layout",
						"org.apache.log4j.PatternLayout");
				loggingProperties
						.put("log4j.appender.errorAppender.layout.ConversionPattern",
								"%d [%t] %-5p %c{1} - %m%n");
			}
			if (isPerformanceLoggingEnabled()) {
				loggingProperties.put("log4j.logger.jtapi.performanceLogger",
						Level.TRACE + ",performanceAppender");
				loggingProperties.put("log4j.appender.performanceAppender",
						"org.apache.log4j.RollingFileAppender");
				loggingProperties.put(
						"log4j.appender.performanceAppender.File", perfFile);

				if (perfFileCount != null)
					loggingProperties
							.put("log4j.appender.performanceAppender.MaxBackupIndex",
									String.valueOf(Integer
											.parseInt(perfFileCount) - 1));
				else {
					loggingProperties
							.put("log4j.appender.performanceAppender.MaxBackupIndex",
									"9");
				}
				if (perfFileSize != null)
					loggingProperties.put(
							"log4j.appender.performanceAppender.MaxFileSize",
							perfFileSize);
				else
					loggingProperties.put(
							"log4j.appender.performanceAppender.MaxFileSize",
							"50MB");
				loggingProperties.put(
						"log4j.appender.performanceAppender.layout",
						"org.apache.log4j.PatternLayout");
				loggingProperties
						.put("log4j.appender.performanceAppender.layout.ConversionPattern",
								"%d [%t] %-5p %c{1} - %m%n");
			}
			if ((isTraceLoggingEnabled()) || (isErrorLoggingEnabled())
					|| (isPerformanceLoggingEnabled())) {
				PropertyConfigurator.configure(loggingProperties);
				Logger theLogger = Logger.getLogger(JTAPILoggingAdapter.class);
				theLogger.info("Logging initialized correctly");
				theLogger.info("Logging properties = "
						+ loggingProperties.toString());
				if (performanceLoggingEnabled)
					PerfStatisticsCollector.initPerfStatisticsCollector();
			}
		}
		resetPropertyStatusTable();
	}

	public static void updateLoggingProperties() {
		Set<String> keySet = propertyStatusTable.keySet();
		Iterator<String> keySetIterator = keySet.iterator();
		while (keySetIterator.hasNext()) {
			String key = (String) keySetIterator.next();
			Boolean value = (Boolean) propertyStatusTable.get(key);
			if (!value.booleanValue())
				if (key.equals("altTraceFile")) {
					setAltTraceFile(null);
				} else if (key.equals("errorFile")) {
					setErrorFile(null);
				} else if (key.equals("errorFileCount")) {
					setErrorFileCount(null);
				} else if (key.equals("errorFileSize")) {
					setErrorFileSize(null);
				} else if (key.equals("traceFileCount")) {
					setTraceFileCount(null);
				} else if (key.equals("traceFileSize")) {
					setTraceFileSize(null);
				} else if (key.equals("traceLoggerLevel")) {
					setTraceLoggerLevel(null);
				} else if (key.equals("perfFile")) {
					setPerfFile(null);
					PerfStatisticsCollector.shutdown();
				} else if (key.equals("perfFileCount")) {
					setPerfFileCount(null);
				} else if (key.equals("perfFileSize")) {
					setPerfFileSize(null);
				}
		}
		initializeLogging();
		if (perfFile != null)
			PerfStatisticsCollector.updatePerfStatisticsCollectorConfig();
	}

	public static String getAltTraceFile() {
		return altTraceFile;
	}

	public static void setAltTraceFile(String altTraceFile) {
		hasChanged(altTraceFile, altTraceFile);
		JTAPILoggingAdapter.altTraceFile = altTraceFile;
		if (altTraceFile != null)
			propertyStatusTable.put("altTraceFile", Boolean.TRUE);
	}

	public static String getTraceFileCount() {
		return traceFileCount;
	}

	public static void setTraceFileCount(String traceFileCount) {
		hasChanged(traceFileCount, traceFileCount);
		JTAPILoggingAdapter.traceFileCount = traceFileCount;
		if (traceFileCount != null)
			propertyStatusTable.put("traceFileCount", Boolean.TRUE);
	}

	public static String getTraceFileSize() {
		return traceFileSize;
	}

	public static void setTraceFileSize(String traceFileSize) {
		hasChanged(traceFileSize, traceFileSize);
		JTAPILoggingAdapter.traceFileSize = traceFileSize;
		if (traceFileSize != null)
			propertyStatusTable.put("traceFileSize", Boolean.TRUE);
	}

	public static String getErrorFile() {
		return errorFile;
	}

	public static void setErrorFile(String errorFile) {
		hasChanged(errorFile, errorFile);
		JTAPILoggingAdapter.errorFile = errorFile;
		if (errorFile != null) {
			setErrorLoggingEnabled(true);
			propertyStatusTable.put("errorFile", Boolean.TRUE);
		} else {
			setErrorLoggingEnabled(false);
		}
	}

	public static String getErrorFileCount() {
		return errorFileCount;
	}

	public static void setErrorFileCount(String errorFileCount) {
		hasChanged(errorFileCount, errorFileCount);
		JTAPILoggingAdapter.errorFileCount = errorFileCount;
		if (errorFileCount != null)
			propertyStatusTable.put("errorFileCount", Boolean.TRUE);
	}

	public static String getErrorFileSize() {
		return errorFileSize;
	}

	public static void setErrorFileSize(String errorFileSize) {
		hasChanged(errorFileSize, errorFileSize);
		JTAPILoggingAdapter.errorFileSize = errorFileSize;
		if (errorFileSize != null)
			propertyStatusTable.put("errorFileSize", Boolean.TRUE);
	}

	public static boolean isTraceLoggingEnabled() {
		return traceLoggingEnabled;
	}

	public static void setTraceLoggingEnabled(boolean traceLoggingEnabled) {
		JTAPILoggingAdapter.traceLoggingEnabled = traceLoggingEnabled;
	}

	public static boolean isErrorLoggingEnabled() {
		return errorLoggingEnabled;
	}

	public static void setErrorLoggingEnabled(boolean errorLoggingEnabled) {
		JTAPILoggingAdapter.errorLoggingEnabled = errorLoggingEnabled;
	}

	public static String getTraceLoggerLevel() {
		return traceLoggerLevel;
	}

	public static String getPerfFile() {
		return perfFile;
	}

	public static void setPerfFile(String perfFile) {
		hasChanged(perfFile, perfFile);
		JTAPILoggingAdapter.perfFile = perfFile;
		if (perfFile != null) {
			setPerformanceLoggingEnabled(true);
			propertyStatusTable.put("perfFile", Boolean.TRUE);
		} else {
			setPerformanceLoggingEnabled(false);
		}
	}

	public static String getPerfFileCount() {
		return perfFileCount;
	}

	public static void setPerfFileCount(String perfFileCount) {
		hasChanged(perfFileCount, perfFileCount);
		JTAPILoggingAdapter.perfFileCount = perfFileCount;
		if (perfFileCount != null)
			propertyStatusTable.put("perfFileCount", Boolean.TRUE);
	}

	public static String getPerfFileSize() {
		return perfFileSize;
	}

	public static void setPerfFileSize(String perfFileSize) {
		hasChanged(perfFileSize, perfFileSize);
		JTAPILoggingAdapter.perfFileSize = perfFileSize;
		if (perfFileSize != null)
			propertyStatusTable.put("perfFileSize", Boolean.TRUE);
	}

	public static boolean isPerformanceLoggingEnabled() {
		return performanceLoggingEnabled;
	}

	public static void setPerformanceLoggingEnabled(
			boolean performanceLoggingEnabled) {
		JTAPILoggingAdapter.performanceLoggingEnabled = performanceLoggingEnabled;
	}

	public static void setTraceLoggerLevel(String traceLoggerLevel) {
		String log4jLevel = null;

		if (traceLoggerLevel == null) {
			setTraceLoggingEnabled(false);
			return;
		}
		try {
			int debugLevel = Integer.parseInt(traceLoggerLevel);
			if ((debugLevel >= 0) && (debugLevel <= 7)) {
				setTraceLoggingEnabled(true);
				if (debugLevel == 0) {
					log4jLevel = Level.OFF.toString();
					setTraceLoggingEnabled(false);
				} else if ((debugLevel > 0) && (debugLevel < 6)) {
					log4jLevel = Level.INFO.toString();
				} else if (debugLevel == 6) {
					log4jLevel = Level.DEBUG.toString();
				} else if (debugLevel == 7) {
					log4jLevel = Level.TRACE.toString();
				}
				hasChanged(traceLoggerLevel, log4jLevel);
				traceLoggerLevel = log4jLevel;
			} else if (JtapiUtils.isLog4jConfigured()) {
				Logger theLogger = Logger.getLogger(JTAPILoggingAdapter.class);
				theLogger
						.error("JTAPILoggingAdapter - Invalid value for debugLevel. Enter a value between 1 and 7");
				theLogger
						.error("Error occured while reading logger level property. You entered : "
								+ traceLoggerLevel);
			} else {
				System.out
						.println("JTAPILoggingAdapter - Invalid value for debugLevel. Enter a value between 1 and 7");
				System.out
						.println("Error occured while reading logger level property. You entered : "
								+ traceLoggerLevel);
			}
		} catch (Exception e) {
			if (JtapiUtils.isLog4jConfigured()) {
				Logger theLogger = Logger.getLogger(JTAPILoggingAdapter.class);
				theLogger
						.error("JTAPILoggingAdapter - Invalid value for debugLevel. Enter a value between 1 and 7");
				theLogger.error(
						"Error occured while reading logger level property. You entered : "
								+ traceLoggerLevel, e);
			} else {
				System.out
						.println("JTAPILoggingAdapter - Invalid value for debugLevel. Enter a value between 1 and 7");
				System.out
						.println("Error occured while reading logger level property. You entered : "
								+ traceLoggerLevel);
				e.printStackTrace();
			}
			return;
		}
		propertyStatusTable.put("traceLoggerLevel", Boolean.TRUE);
	}

	static {
		resetPropertyStatusTable();
	}
}