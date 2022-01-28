package dpk.plugins.dpkcreative;

public class Config {
    public static String getMsg(String key) {
        return DPKCreative.config.getString("messages." + key);
    }
}
