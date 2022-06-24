package warroom;

import com.hongfang.csp.webframeworx.common.mediator.Colleague;
import com.hongfang.csp.webframeworx.common.mediator.MediatorType;

/**
 * 實體工程師同事類別
 * @Author Wong Ye Fang 709410012
 * @date 2022/06/04
 */
public class EngineeringTeam extends Colleague {

    public void run() {
        //do nothing
    }

    public void receive(String s) {
        System.out.println("工程師團隊收到請求： " + s + "。" );
    }

    public void send(MediatorType mediatorType, String s) {
        System.out.println("工程師團隊發出請求： " + s + "。" );
        mediator.relay(mediatorType, s);
    }
}
