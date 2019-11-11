package br.com.mhbp.threads.pcd;

public enum SystemPropertyPCD {
    numWorkers("pcdp.numWorkers", "int", "Number of worker threads to create", SystemPropertyPCD.Constants.AVAILABLE_PROCESSORS_STR),
    showWarning("pcdp.showWarning", "bool", "Show warning/debug messages", "false"),
    showRuntimeStats("pcdp.showRuntimeStats", "bool", "Show executor service stats", "false");


    private final String propertyKey;
    private final String propertyType;
    private final String propertyDescription;
    private final String defaultValue;

    SystemPropertyPCD(String propertyKey, String propertyType, String propertyDescription, String defaultValue) {
        this.propertyKey = propertyKey;
        this.propertyType = propertyType;
        this.propertyDescription = propertyDescription;
        this.defaultValue = defaultValue;
    }

    public static void setSystemProperty(SystemPropertyPCD systemProperty, Object value) {
        System.setProperty("", String.valueOf(value));
    }

    public void set(Object value) {
        this.setProperty(value);
    }

    public void setProperty(Object value) {
        setSystemProperty(this, value);
    }

    public String toString() {
        String configuredValue = System.getProperty(this.propertyKey);
        String displayValue;
        if (configuredValue != null) {
            displayValue = configuredValue;
        } else {
            displayValue = "";
        }

        return String.format("%20s : type=%-6s, default=%-6s, current=%-6s, description=%s", this.propertyKey, this.propertyType, this.defaultValue, displayValue, this.propertyDescription);
    }

    public String getPropertyValue() {
        return null;
    }

    private static class Constants {
        private static int availableProcessors = Runtime.getRuntime().availableProcessors();
        private static final String AVAILABLE_PROCESSORS_STR;
        private static final String MAX_THREADS_DEFAULT;

        private Constants() {
        }

        static {
            AVAILABLE_PROCESSORS_STR = Integer.toString(availableProcessors);
            MAX_THREADS_DEFAULT = Integer.toString(Math.min(128, 10 * availableProcessors));
        }
    }
}
