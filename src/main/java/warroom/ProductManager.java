package warroom;

import com.hongfang.ckernel.http.socket.ClientHandler;
import com.hongfang.csp.webframeworx.common.mediator.Colleague;
import com.hongfang.csp.webframeworx.common.mediator.Mediator;
import com.hongfang.csp.webframeworx.common.mediator.MediatorType;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 以產品經理模擬 C4ISR 戰情室協調者
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public class ProductManager extends Mediator implements Runnable {
    private Map<MediatorType, Colleague> colleagues = new HashMap<MediatorType, Colleague>();
    private ServerSocket listener;
    private List<ClientHandler> clients = new ArrayList<ClientHandler>();
    private ExecutorService pool = Executors.newFixedThreadPool(4);

    public void register(MediatorType type, Colleague colleague) {
        colleague.setMedium(this);
        colleagues.put(type, colleague);
    }

    public void relay(MediatorType type, String message) {
        Colleague toCl = colleagues.get(type);
        toCl.receive(message);
    }

    public void initServerSocket() throws IOException {
        listener = new ServerSocket(6789);
    }

    @Override
    public void run() {
        try {
            initServerSocket();
        } catch (IOException e) {
            throw new RuntimeException("[SERVER] Server start fail : " + e);
        }

        while(true) {
            System.out.println("[SERVER] Waiting for client connection...");
            Socket client = null;
            try {
                client = listener.accept();
                System.out.println("[SERVER] Connected to client!");
                ClientHandler clientThread = new ClientHandler(client, clients, this);
                clients.add(clientThread);

                pool.execute(clientThread);
            } catch (IOException e) {
                throw new RuntimeException("[SERVER] Connected fail : " + e);
            }
        }
    }
}
