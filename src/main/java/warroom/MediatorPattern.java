package warroom;

import com.hongfang.csp.webframeworx.common.mediator.Colleague;
import com.hongfang.csp.webframeworx.common.mediator.MediatorType;

import java.io.IOException;

/**
 * Mediator 樣式主程式
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public class MediatorPattern {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProductManager pm = new ProductManager();
        Colleague c1, c2, c3, c4;
        c1 = new DesignTeam();
        c2 = new MarketingTeam();
        c3 = new CustomerServiceTeam();
        new Thread(c3).start();

        c4 = new EngineeringTeam();
        pm.register(MediatorType.DESIGN, c1);
        pm.register(MediatorType.MARKETING, c2);
        pm.register(MediatorType.SERVICE, c3);
        pm.register(MediatorType.ENGINEERING, c4);

        new Thread(pm).start();                                                                               // 將 PM 當成一個獨立的執行緒 Server

        //c1.send(MediatorType.SERVICE, "設計有問題");
        //System.out.println("----------------------");
        //c1.send(MediatorType.ENGINEERING, "軟體設計有問題");
        //System.out.println("----------------------");
    }
}
