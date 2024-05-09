package hello;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class JavaSystem {
    public static void print() {
        Properties properties = System.getProperties();
        System.setProperty("hello_key", "hello_Value");

        log.info("key ={} ,value" , System.getProperty("hello_Key"));
        for(Object key : properties.keySet()) {
            log.info("prop {}={}", key, System.getProperty(String.valueOf(key)));
        }

        String url = System.getProperty("url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        log.info("url = {}, username={}, password ={}", url, username , password);
    }
}
