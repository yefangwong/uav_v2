package uav.impl;

import photography.IDistanceCalculator;
import photography.impl.DistanceCalculator;
import uav.IUAVFacade;
import util.MyObservable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 無人機物件的實際物件，本身繼承 MyObservable 和實作無人機統一介面，提供無人機主要功能
 * 和發送 AI 偵測事件，進行多目標的偵測和計算
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public class UAVFacadeImpl extends MyObservable implements IUAVFacade {
	private BootLoader bootLoader;																			  // 無人機作業系統載入器
	private DetectManager objectDetector;																      // AI 物件偵測的功能
	private IDistanceCalculator distanceCalculator;															  // 目標經緯度計算器，樣式三，觀察者
	private ServerSocket uavListener;																		  // 主機：用來傾聽遙控器送來的指令
	public UAVFacadeImpl() {
		this.on();
		distanceCalculator = new DistanceCalculator();
		super.addObserver(distanceCalculator);																  // 註冊為 AI 預測結果的觀察者
	}

	private DetectManager getDetectApproach() {
		//只能有一個演算法管理員
		//DetectManager dm = new DetectManager();
		return DetectManager.getInstance();																	  // 樣式四：單例，控制無人機全域使用一份 AI 模型，預設 CNN
		//return DetectManager.getInstance(DetectManager.TRANSFORMER);										  // 樣式五：策略，也可以是 Transformer
	}

	public void on() {
		this.bootLoader = new BootLoader();
		this.objectDetector = getDetectApproach();
		bootLoader.load();
		// 模擬無人機作業系統載入時間
		try {
			Thread.sleep(2 * 1000);
			System.out.println("UAV: Vmworks load complete.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void fly() {
		System.out.println("UAV: ready to fly...");
		// 模擬等待遙控送出起飛指令
		try {
			Thread.sleep(2 * 1000);
			System.out.println("UAV: UAV get command : Fly");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		takeOff();
	}

	private void takeOff() {
		System.out.println("UAV: uav take off.");
		try {
			System.out.println("reconnaissance...");
			// 模擬無人機滯空搜索時間
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (true) {
			if (objectDetector.detect()) {
				System.out.println("UAV: got tank!");

				// TODO 將計算座標的功能開新的執行緒執行，以便立即進行下一個目標的偵蒐
				// do position calculation
				doImagePhotography();
			} else {
				System.out.println("Not Found! Keep Reconnaissance...");
			}
		}

	}

	private void doImagePhotography() {
		System.out.println("UAV: do lon/lat calculation");

		notifyObservers(new Object());

		// 將座標回傳後方中控
		sendBack();
	}

	private void sendBack() {
		System.out.println(String.format("UAV: Send %f, %f.", distanceCalculator.getTargetLon(),
				distanceCalculator.getTargetLat()));
	}

	@Override
	public void run() {
		try {
			uavListener = new ServerSocket(6789);

			while (true) {
				Socket connectionSocket = uavListener.accept();												  // 接收遙控器指令
				BufferedReader cmdFromRemoteController =
						new BufferedReader((new InputStreamReader((connectionSocket.getInputStream()))));
				String cmd = cmdFromRemoteController.readLine();

				if (cmd.equals("on")) {
					System.out.println("UAV: Drone Receive command:" + cmd + "\n");
					this.on();
				} else if (cmd.equals("fly")) {
					System.out.println("UAV: Drone Receive command:" + cmd + "\n");
					this.fly();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
