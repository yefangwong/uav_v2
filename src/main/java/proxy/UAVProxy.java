package proxy;

import uav.IUAVFacade;
import uav.impl.UAVFacadeImpl;

/**
 * 無人機的遠端代理人介面
 * TODO 因為是公開介面，要留意資訊安全
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public class UAVProxy implements IUAVFacade {
    UAVFacadeImpl realUAV;                                                                                                  // 實際的飛行機物件，但此處只會維護一個參考，不會馬上配置系統資源

    @Override
    public void on() {
        if (realUAV == null) {
            realUAV = new UAVFacadeImpl();
        } else {
            realUAV.on();                                                                                               // 因為無人機實際要開機啟動了，所以要使用真實的無人機物件進行開機啟動
        }
    }

    @Override
    public void fly() {                                                                                                 // fly by proxy
        if (realUAV == null)
            realUAV = new UAVFacadeImpl();
        // 模擬遙控器要與遠端無人機建立連線的時間
        try {
            Thread.sleep(1 * 1000);
            System.out.println("waiting to connect UAV...");
            System.out.println("ready to send fly command to UAV...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO 之後要改為 Socket
        realUAV.fly();                                                                                                  // 此處才真正的將起飛指令送到遠端的無人機
    }
}
