package br.com.mhbp.threads.pcd;

import java.io.InputStream;
import java.util.Properties;

public class ConfigurationPCD {

    public static boolean showWarning;
    public static boolean SHOW_RUNTIME_STATS;
    public static String BUILD_INFO;

    private ConfigurationPCD() {
        throw new IllegalStateException("Emptyton, no instance creation expected!");
    }

    static {
//        initializeFlags();
    }

    public static int readIntProperty(SystemPropertyPCD numWorkers) {
        return 0;
    }

    private interface Lambda<P, R> {
        R apply(P var1);
    }
    private static <T> T extractProperty(SystemPropertyPCD propertyName, ConfigurationPCD.Lambda<String, T> converter) {
        try {
            String valueStr = propertyName.getPropertyValue();
            return converter.apply(valueStr);
        } catch (Exception var3) {
            throw new IllegalStateException("Error while converting property: " + propertyName);
        }
    }

    public static String readStringProperty(SystemPropertyPCD systemProperty) {
        ConfigurationPCD.Lambda<String, String> converter = new ConfigurationPCD.Lambda<String, String>() {
            public String apply(String s) {
                return s;
            }
        };
        return (String)extractProperty(systemProperty, converter);
    }

}
