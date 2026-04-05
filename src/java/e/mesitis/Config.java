package e.mesitis;

public class Config {
    public static String get(String key) {
        String value = System.getenv(key);
        if (value == null) {
            throw new RuntimeException("Missing env var: " + key);
        }
        return value;
    }
}
