package test;

import tasks.FollowPath;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class TestFollowPath extends TestBase{
	public static void main(String[] args) {
		FollowPath fp = new FollowPath();
		
		fp.execute();
	}
}
