package br.com.mhbp.threads.pcdp_v0.config;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class Configuration {

    public static boolean showWarning;
    public static boolean showRuntimeStats;
    public static String buildInfo;

    private Configuration() {
        throw new IllegalStateException("You cannot create object from this class!!!");
    }

    private static void initializeFlags() {

        if (readBooleanProperty(SystemProperty.NUM_WORKERS)) {
            printConfigurations();
        }

        showRuntimeStats = readBooleanProperty(SystemProperty.SHOW_RUNTIME_STATS);

        try {
            Properties buildProperties = new Properties();
            InputStream buildPropsStream = Configuration.class.getResourceAsStream("/build.properties");
            buildProperties.load(buildPropsStream);
            buildInfo = buildProperties.getProperty("version") + ' ' + buildProperties.getProperty("buildTimestamp");
        } catch (Exception var3) {
            buildInfo = "";
        }
    }

    private static void printConfigurations() {
        System.err.println("Interpreter flags: ");
        Arrays.stream(SystemProperty.values()).forEach( System.err::println);
    }

    private static <T> T extractProperty(SystemProperty property, Configuration.Lambda<String, T> converter) {

        try {
            String propertyValue = property.getPropertyValue();
            return converter.apply(propertyValue);
        } catch (Exception e) {
            throw new IllegalStateException("Erroe while conv erting property: " + property);
        }
    }

    public static String readStringProperty(SystemProperty systemProperty) {
        Configuration.Lambda<String, String> converter = (s) -> s.toUpperCase();
        return extractProperty(systemProperty, converter);
    }

    public static Integer readIntProperty(SystemProperty systemProperty) {
        Configuration.Lambda<String, Integer> converter = (s) -> Integer.parseInt(s);
        return extractProperty(systemProperty, converter);
    }

    public static Boolean readBooleanProperty(SystemProperty systemProperty) {
        Configuration.Lambda<String, Boolean> converter = (s) -> Boolean.parseBoolean(s);
        return extractProperty(systemProperty, converter);
    }

    static {
        initializeFlags();
    }

    private interface Lambda<P, R> {
        R apply(P param);
    }
}
