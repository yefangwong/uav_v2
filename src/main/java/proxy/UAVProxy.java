package proxy;

import uav.IUAVFacade;
import uav.impl.UAVFacadeImpl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 無人機的遠端代理人介面
 * TODO 因為是公開介面，要留意資訊安全
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public class UAVProxy implements IUAVFacade, Runnable {
    IUAVFacade realUAV;                                                                                       // 實際的飛行機物件，但此處只會維護一個參考，不會馬上配置系統資源

    public UAVProxy() throws InterruptedException {
        realUAV = new UAVFacadeImpl();
        Thread uavCmdListener = new Thread(realUAV);                                                          // 啟動無人機主機，以便頃聽客戶端的指令
        uavCmdListener.start();
    }

    private Socket remoteControllerSocket;                                                                    // 客戶端 Socket 表示遙控器
    public void on() throws IOException, InterruptedException {
        if (realUAV == null) {
            realUAV = new UAVFacadeImpl();
        } else {
            //@deprecated
            //realUAV.on();                                                                                   // 因為無人機實際要開機啟動了，所以要使用真實的無人機物件進行開機啟動
            Thread remoteControllerCmdSender = new Thread(this);                                        // 啟動遙控器指令發送給無人機
            remoteControllerCmdSender.start();
            //remoteControllerCmdSender.join();
            sendCmd("on");
            remoteControllerCmdSender.join();
        }
    }

    private void sendCmd(String cmd) throws IOException, InterruptedException {
        waitIfNull();
        DataOutputStream cmdToUAV = new DataOutputStream((this.remoteControllerSocket.getOutputStream()));
        cmdToUAV.writeBytes(cmd + '\n');
    }

    private synchronized void waitIfNull() throws InterruptedException {
        while (this.remoteControllerSocket == null) {
            wait();
        }
    }

    public void fly() throws IOException, InterruptedException {                                                                                       // fly by proxy
        if (realUAV == null)
            realUAV = new UAVFacadeImpl();
        // 模擬遙控器要與遠端無人機建立連線的時間
        Thread remoteControllerCmdSender = new Thread(this);                                            // 啟動遙控器指令發送給無人機
        remoteControllerCmdSender.start();
        remoteControllerCmdSender.join();
        System.out.println("waiting to connect UAV...");
        System.out.println("ready to send fly command to UAV...");
        //realUAV.fly();                                                                                      // 此處才真正的將起飛指令送到遠端的無人機
        sendCmd("fly");
    }

    @Override
    public synchronized void run() {
        try {
            this.remoteControllerSocket = new Socket("localhost", 6789);
            this.notify();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
