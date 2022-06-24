package photography.impl;

import photography.IDistanceCalculator;

/**
 * 實作無人機的目標經緯度計算介面，同時，程式也會是一個觀察者，當 AI 偵測到目標時，
 * 才啟動目標經緯度計算，節省 UAV 電力
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public class DistanceCalculator implements IDistanceCalculator {
    private double lon;                                                                                                 // UAV 經度
    private double lat;                                                                                                 // UAV 緯度

    private double targetLon;                                                                                           // 目標經度
    private double targetLat;                                                                                           // 目標緯度

    /**
     * 根據無人機經緯度配合航空攝影和大地座標、三角函數推算目標經緯度
     * TODO
     */
    public void calculate() {
        System.out.println("DistanceCalculator: photography calculating....");
        // 模擬影像攝影距離計算
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.targetLon = 121.534648;
        this.targetLat = 25.026406;
    }

    public double getTargetLon() {
        return targetLon;
    }

    public double getTargetLat() {
        return targetLat;
    }

    public void update() {
        this.calculate();                                                                                               // 計算目標經緯度
    }
}
