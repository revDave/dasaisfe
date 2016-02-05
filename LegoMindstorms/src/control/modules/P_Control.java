package control.modules;

public class P_Control {
	private double kp;

	public P_Control(double kp) {
		this.kp = kp;
	}

	public double calcOutput(double error) {
		return kp * error;
	}

	public double getKp() {
		return kp;
	}

}
