package test;

import tasks.FollowPath;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class TestFollowPath{
	public static void main(String[] args) {
		FollowPath fp = new FollowPath(null);
		
		LocalEV3.get().getKey("Escape").addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(Key k) {
            }

            @Override
            public void keyReleased(Key k) {
                    System.exit(0);
            }
		});
		fp.execute();
	}
}
