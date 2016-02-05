package control.modules;

public class PID_Control implements ControllerInterface {
	private P_Control p;
	private I_Control i;
	private D_Control d;
	private double kc;
	private double pc;
	private double dt;
	private double offset;

	public PID_Control(double kc, double pc, double dt) {
		this.kc = kc;
		this.pc = pc;
		this.dt = dt;
	}

	public void reset() {
		i.reset();
		d.reset();
	}

	public void init(double p_factor, double i_factor, double i_constant, double d_factor) {
		p = new P_Control(p_factor * kc);
		i = new I_Control(i_factor * p.getKp() * dt / pc, i_constant);
		d = new D_Control((p.getKp() * pc / (d_factor * dt)));
	}

	public double calcOutput(double error) throws IllegalStateException {
		if (p == null || i == null || d == null)
			throw new IllegalStateException("P, I or D was not initialized");
		return p.calcOutput(error) + i.calcOutput(error) + d.calcOutput(error);
	}

	public double getKc() {
		return kc;
	}

	public void setKc(double kc) {
		this.kc = kc;
	}

	public double getPc() {
		return pc;
	}

	public void setPc(double pc) {
		this.pc = pc;
	}

	public double getDt() {
		return dt;
	}

	public void setDt(double dt) {
		this.dt = dt;
	}

	public double getErrorThreshold() {
		return errorThreshold;
	}

	public void setErrorThreshold(double errorThreshold) {
		this.errorThreshold = errorThreshold;
	}

	public double getOffset() {
		return offset;
	}

	private double errorThreshold;

	public void setOffset(double offset) {
		this.offset = offset;
	}

}
