package test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    LoadProperties loadProperties;
    static Properties properties = new Properties();

    private LoadProperties() {

    }

    public static void init() {
        try {
            ///Users/cb-sarathkumar/work/cb-ui-middleware/GensparkMavenUI/src/test/utils/LoadProperties.java
//            String prop = System.getProperty("region");
//            if (prop.contains("us"))
                properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties.properties"));
//            if (prop.contains("eu"))
//                properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties_eu.properties"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static String getProperty(String key) {
        init();
        return properties.getProperty(key);
    }
}
