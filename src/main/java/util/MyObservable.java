package util;

import java.util.Vector;

/**
 * 觀察者模式的主題 (Topic)
 *
 * @Author Wong Ye Fang 709410012
 * @date 2022/05/14
 */
public class MyObservable {

	private boolean changed = false;
	private Vector obs;                 																				// 用 vector 儲存所有對此主題有興趣的觀察者

    public MyObservable() {
        obs = new Vector();
    }

	/**
	 * 註冊觀察者
	 * @param o
	 */
	public synchronized void addObserver(Listener o) {
		obs.add(o);
	}

	/**
	 * 通知觀察者主題有更新
	 * @param arg
	 */
	public void notifyObservers(Object arg) {
		for (Object listener : obs) {
			((Listener)listener).update();
		}
	}

}
