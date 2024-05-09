package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class JavaSystemProperties {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        for(Object key : properties.keySet()) {
            log.info("prop {}={}", key, System.getProperty(String.valueOf(key)));
        }

        String url = System.getProperty("url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        System.setProperty("hello_key", "hello_Value");
        log.info("key ={} ,value" , System.getProperty("hello_Key"));
        log.info("url = {}, username={}, password ={}", url, username , password);
;    }

}
