package photography;

import util.Listener;

/**
 * 無人機的目標經緯度計算介面，也是一種觀察者，傾聽者，傾聽無人機 AI 偵測的狀況
 * TODO 因為是公開介面，要留意資訊安全
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public interface IDistanceCalculator extends Listener {

    /**
     * 提供航太攝影的經緯度計算功能
     */
    void calculate();

    /**
     * 取得目標精度座標
     * @return 經度
     */
    double getTargetLon();

    /**
     * 取得目標緯度座標
     * @return 緯度
     */
    double getTargetLat();
}
