package com.luna.media.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * @author Luna@win10
 * @date 2020/6/8 20:07
 */

@Component
@ConfigurationProperties(prefix = "luna.location")
public class JavaCvConfigValue {

    private static String faceModel;

    private static String frontalFace;

    public static String getFaceModel() {
        return faceModel;
    }

    public static void setFaceModel(String faceModel) {
        JavaCvConfigValue.faceModel = faceModel;
    }

    public static String getFrontalFace() {
        return frontalFace;
    }

    public static void setFrontalFace(String frontalFace) {
        JavaCvConfigValue.frontalFace = frontalFace;
    }

    public static String getFaceModel(Class<?> c) {
        URL faceModelUrl =
            c.getClassLoader().getResource(faceModel);
        return faceModelUrl.getFile();
    }

    public static String getFrontalFace(Class<?> c) {
        URL frontalFaceUrl =
            c.getClassLoader().getResource(frontalFace);
        return frontalFaceUrl.getFile();
    }

}
