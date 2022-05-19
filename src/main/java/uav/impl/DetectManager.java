package uav.impl;

import detect.CNN;
import detect.ObjectDetectionStrategy;
import detect.Transformer;

public class DetectManager {
    public final static int CNN = 1;
    public final static int TRANSFORMER = 2;

    private static DetectManager instance;
    private static int approach = DetectManager.CNN;                                                                           // 預設使用卷積神經網路

    private DetectManager(int approach) {
        this.approach = approach;
    }

    public static DetectManager getInstance() {
        return getInstance(DetectManager.CNN);
    }

    public static DetectManager getInstance(int approach) {
        if (instance == null) instance = new DetectManager(approach);
        return instance;
    }

    public boolean detect() {
        ObjectDetectionStrategy strategy;
        switch (approach) {
            case DetectManager.CNN:
                strategy = new CNN();
                break;
            case DetectManager.TRANSFORMER:
                strategy = new Transformer();
                break;
            default:
                strategy = new CNN();
                break;
        }
        return strategy.predict();
    }

}
