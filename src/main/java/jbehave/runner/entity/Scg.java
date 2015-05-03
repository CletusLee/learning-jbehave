package jbehave.runner.entity;

import java.util.LinkedList;
import java.util.List;

public class Scg {
	List<Dp> dps;
	private String bladeId;
	
	
	public List<Dp> getDps() {
		return dps;
	}

	public void setDps(List<Dp> dps) {
		this.dps = dps;
	}

	public String getBladeId() {
		return bladeId;
	}

	public void setBladeId(String bladeId) {
		this.bladeId = bladeId;
	}

	public Scg() {
		dps = new LinkedList<>();
	}
	
	public List<Dp> getAllDps() {
		return dps;
	}
	
	public void addDp(Dp dp) throws Exception {
		if(dps.size() >= 4) {
			throw new Exception("one SCG can only have four dp");
		}
		
		dps.add(dp);
	}
}
