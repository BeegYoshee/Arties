package org.dreambot.articron.profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Profile {

    private HashMap<String, List<String>> profileMap;

    public Profile() {
        profileMap = new HashMap<>();
    }

    public void putSetting(String token, String... configs) {
        List<String> conf = new ArrayList<>();
        Collections.addAll(conf,configs);
        profileMap.put(token, conf);
    }

    public String getConfig(String token, int index) {
        List<String> configs = profileMap.get(token);
        if (configs == null) return "";
        return configs.get(index);
    }

    public String[] getConfigs(String token) {
        List<String> configs = profileMap.get(token);
        String[] configArray = new String[configs.size()];
        for (int i = 0; i < configs.size(); i++) {
            configArray[i] = configs.get(i);
        }
        return configArray;
    }
}
