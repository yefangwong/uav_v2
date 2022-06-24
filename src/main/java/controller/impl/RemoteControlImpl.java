package controller.impl;

import controller.IRemoteController;
import uav.IUAVFacade;

import java.io.IOException;

/**
 * 無人機遙控器的實作介面
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public class RemoteControlImpl implements IRemoteController {
    IUAVFacade uav;                                                                                                     // 實際上為無人機的代理物件

    public RemoteControlImpl(IUAVFacade uav) {
        this.uav = uav;
    }

    public void uavOn() throws IOException, InterruptedException {
        // delegate UAV On Command
        System.out.println("Remote Control: send UAV On command.");
        uav.on();
    }

    public void uavFly() throws IOException, InterruptedException {
        // delegate UAV Fly Command
        System.out.println("Remote Control: send UAV Fly command.");
        uav.fly();
    }
}
