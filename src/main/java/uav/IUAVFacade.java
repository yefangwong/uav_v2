package uav;

import java.io.IOException;

/**
 * 統一的無人機功能介面，簡化介面
 * TODO 因為是公開介面，實作的物件要留意資訊安全的問題
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public interface IUAVFacade extends Runnable {

    /**
     * 載入無人機作業系統，例如 vmWorks
     */
    void on() throws IOException, InterruptedException;

    /**
     * 提供無人機飛翔時要進行的功能
     */
    void fly() throws IOException, InterruptedException;                                                                                                         // 無人機有飛的功能
}
