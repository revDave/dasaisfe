package test;



import tasks.ChainBridge;
import tasks.Task;

public class TestChainBridge {
	
	public static void main(String[] args) {
		Task task = new ChainBridge(null);
		task.execute();
	}

}
