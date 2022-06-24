package controller;

import java.io.IOException;

/**
 * 無人機的遙控器介面
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public interface IRemoteController {
    /**
     * 命令無人機開機
     */
    void uavOn() throws IOException, InterruptedException;

    /**
     * 命令無人機飛行
     */
    void uavFly() throws IOException, InterruptedException;

}
