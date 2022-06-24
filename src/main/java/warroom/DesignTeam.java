package warroom;

import com.hongfang.csp.webframeworx.common.mediator.Colleague;
import com.hongfang.csp.webframeworx.common.mediator.MediatorType;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 實體設計師同事類別
 * @Author Wong Ye Fang 709410012
 * @date 2022/06/04
 */
public class DesignTeam extends Colleague {

    public void receive(String s) {
        System.out.println("設計團隊收到請求： " + s + "。" );
    }

    public void send(MediatorType mediatorType, String s) {
        System.out.println("設計團隊發出請求： " + s + "。" );
        //mediator.relay(mediatorType, s);
        ProductManagerProxy mediatorProxy = new ProductManagerProxy(mediator);
        mediatorProxy.relay(mediatorType, s);
    }

    @Override
    public void run() {
        //do nothing
    }
}
