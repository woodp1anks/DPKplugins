package dpk.plugins.dpkeco.method;

import dpk.plugins.dpkeco.DPKEco;

public class Config {
    public static String getMsg(String name) {
        return DPKEco.config.getString("messages." + name);
    }
}
