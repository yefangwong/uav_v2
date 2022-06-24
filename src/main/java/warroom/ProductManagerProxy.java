package warroom;

import com.hongfang.csp.webframeworx.common.mediator.Mediator;
import com.hongfang.csp.webframeworx.common.mediator.MediatorType;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ProductManagerProxy {
    public ProductManagerProxy(Mediator mediator) {
    }

    public void relay(MediatorType mediatorType, String s) {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 6789);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            String messageToServer = "[type = SERVICE, msg = " + s + "]\n";
            outToServer.writeUTF(messageToServer);

            clientSocket.close();
        } catch (IOException ex) {
            System.err.println("[SERVER] relay request error:" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
