package warroom;

import com.hongfang.csp.webframeworx.common.mediator.Colleague;
import com.hongfang.csp.webframeworx.common.mediator.MediatorType;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 客戶服務同事類別
 * @Author Wong Ye Fang 709410012
 * @date 2022/06/04
 */
public class CustomerServiceTeam extends Colleague {
    private Socket clientSocket;

    private BufferedReader inFromServer;

    public void run() {
        try {
            this.clientSocket = new Socket("127.0.0.1", 6789);
            this.inFromServer  =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (true) {
                receive("");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void receive(String s) {
        try {
            String messageFromPM = inFromServer.readLine();
            System.out.println("客戶服務團隊收到請求： " + messageFromPM + "。" );
        } catch (Exception ex) {
            System.err.println("[CLIENT] got error:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void send(MediatorType mediatorType, String s) {
        System.out.println("客戶服務團隊發出請求： " + s + "。" );
        mediator.relay(mediatorType, s);
    }
}
