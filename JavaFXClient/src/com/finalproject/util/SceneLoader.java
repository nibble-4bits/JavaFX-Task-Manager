package com.finalproject.util;

import java.util.ResourceBundle;

public class SceneLoader {

    public static String getScene(String sceneName) {
        ResourceBundle rb = ResourceBundle.getBundle("scenes");
        String sceneRoute = rb.getString(sceneName);

        return sceneRoute;
    }

    public static String getTitle(String sceneName) {
        ResourceBundle rb = ResourceBundle.getBundle("scenetitles");
        String sceneTitle = rb.getString(sceneName);

        return sceneTitle;
    }
}
