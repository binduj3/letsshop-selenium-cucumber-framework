package io.github.binduj3.letsshop.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * Lookup order for every key:
 * 1. JVM system property   (-Dbrowser=firefox)
 * 2. Environment variable  (BROWSER=firefox)
 * 3. config.properties (path from Constants.CONFIG_FILE_PATH)
 */
public final class ConfigReader {

    private static final String CONFIG_FILE = Constants.CONFIG_FILE_PATH;
    private static final Properties FILE = load();

    private ConfigReader() {
    }

    /** Required value: throws a clear error if it isn't set anywhere. */
    public static String read(String key) {
        String value = lookup(key);
        if (value == null) {
            throw new IllegalStateException("Missing config '" + key + "': set it as -D" + key
                    + ", env var " + key.toUpperCase() + ", or in " + CONFIG_FILE);
        }
        return value;
    }

    /** Optional value: returns the default if it isn't set anywhere. */
    public static String readOrDefault(String key, String defaultValue) {
        String value = lookup(key);
        return value == null ? defaultValue : value;
    }

    /** Checks -D, then env var, then the file. Returns null if not found. */
    private static String lookup(String key) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) return sys.trim();

        String env = System.getenv(key.toUpperCase());
        if (env != null && !env.isBlank()) return env.trim();

        String file = FILE.getProperty(key);
        return (file == null || file.isBlank()) ? null : file.trim();
    }

    private static Properties load() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not read config file: " + CONFIG_FILE
                    + " (run from the project root folder)", e);
        }
        return properties;
    }
}