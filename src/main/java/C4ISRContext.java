import controller.RemoteControl;
import controller.impl.RemoteControlImpl;
import proxy.UAVProxy;
import uav.IUAVFacade;

/**
 * C4ISR 場景模擬：無人機物件偵測與物件位置計算
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public class C4ISRContext {
    public static void main(String[] args) {
        IUAVFacade uav = new UAVProxy();                                                                                // 無人機，樣式1 + 2:門面 + Proxy
        RemoteControl remoteControl = new RemoteControlImpl(uav);                                                       // 遙控器
        remoteControl.uavOn();
        remoteControl.uavFly();
    }
}
