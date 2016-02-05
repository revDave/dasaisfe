package control.modules;

public class I_Control {
	private double constant = 1.;
	private double ki;
	private double integral = 0.;

	public I_Control(double ki, double constant) {
		this.constant = constant;
		this.ki = ki;
	}

	public double calcOutput(double error) {
		integral = constant * integral + error;
		return integral * ki;
	}

	public void reset() {
		integral = 0;
	}
}
