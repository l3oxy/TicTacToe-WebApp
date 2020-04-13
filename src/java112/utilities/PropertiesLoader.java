package java112.utilities;

import java.io.*;
import java.util.*;

/**
 * @author Kevin Leader
 */
public interface PropertiesLoader{

    default Properties loadProperties(String propertiesFilePath)
            throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(
                    propertiesFilePath));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw ioException;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
        return properties;
    }
}
