package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties properties;

    public ConfigReader() throws IOException {

        properties = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/configs/config.properties");
        properties.load(file);

    }

    public String getURL() {

        return properties.getProperty("url", "");
    }

    public String getBrowser() {

        return properties.getProperty("browser", "chrome");

    }

    public String getUsername() {

        return properties.getProperty("username");
    }

    public String getPassword() {

        return properties.getProperty("password");
    }
}
