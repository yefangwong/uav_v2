package detect;

import java.util.Random;

public class CNN extends ObjectDetectionStrategy {

	public boolean predict() {
		System.out.println("detecting by CNN");
		return (new Random().nextInt() % 2 == 0);
	}

}