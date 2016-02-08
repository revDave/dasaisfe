package control.modules;

public class D_Control implements ControllerInterface {
	private double lastError = 0.;
	private double kd;
	private double derivative = 0.;

	public D_Control(double kd) {
		this.kd = kd;
	}

	public double calcOutputDefault(double error) {
		derivative = lastError - error;
		double output = kd * derivative;
		lastError = error;
		return output;
	}

	public void reset() {
		lastError = 0.;
		derivative = 0.;
	}
}
