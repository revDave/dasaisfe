package control.modules;

public class I_Control implements ControllerInterface {
	protected double constant = 1.;
	protected double ki;
	protected double integral = 0.;
	protected double lastError = 0;

	public I_Control(double ki, double constant) {
		this.constant = constant;
		this.ki = ki;
	}

	public double calcOutputDefault(double error) {
		integral = constant * integral + error;
		return integral * ki;
	}

	public void reset() {
		integral = 0;
	}

	public double calcOutputReset(double error) {
		if(error * lastError < 0){
			reset();
		}
		integral = integral + error;
		lastError = error;
		return integral * ki;
	}
}
