package controller.impl;

import controller.RemoteControl;
import uav.IUAVFacade;

/**
 * 無人機遙控器的實作介面
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public class RemoteControlImpl implements RemoteControl {
    IUAVFacade uav;                                                                                                     // 實際上為無人機的代理物件

    public RemoteControlImpl(IUAVFacade uav) {
        this.uav = uav;
    }

    @Override
    public void uavOn() {
        // delegate UAV On Command
        System.out.println("Remote Control: send UAV On command.");
        uav.on();
    }

    @Override
    public void uavFly() {
        // delegate UAV Fly Command
        System.out.println("Remote Control: send UAV Fly command.");
        uav.fly();
    }
}
