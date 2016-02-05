package test;

import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.ev3.LocalEV3;

public class TestBase {
	public TestBase(){
		LocalEV3.get().getKey("Escape").addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(Key k) {
            }

            @Override
            public void keyReleased(Key k) {
                    System.exit(0);
            }
		});
	}
}
