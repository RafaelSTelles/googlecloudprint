package br.com.rafaelstelles.gcp.api.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {

    private static Properties properties;

    public static synchronized Properties load(String path) throws IOException {
        if (properties == null) {
            properties = new Properties();
        }
        properties.load(PropertiesFileUtils.class.getResourceAsStream(path));
        return properties;
    }
}
