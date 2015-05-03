package jbehave.runner.steps;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jbehave.runner.entity.Dp;
import jbehave.runner.entity.Scg;
import net.thucydides.core.Thucydides;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BlockUnauthorizedDpSteps {
	
	List<Dp> dps;
	List<Scg> scgs = new LinkedList<>();
	Dp rejectDp;
	
	@Given("$amountOfDp vDPs have connected to $amountOfScg vSCG node $bladeId")
	public void setUpDpAndScg(int amountOfDp, int amountOfScg, String bladeId) {
		dps = new LinkedList<>();
		 for (int i =0;i<amountOfDp;i++) {
			 dps.add(new Dp());
		 }
		 
		 Scg scg = new Scg();
		 scg.setDps(dps);
		 scg.setBladeId(bladeId);
		 scgs.add(scg);
	}
	
	@Given("I have following vSCG nodes: $vscgData")
	public void setUpEnv(ExamplesTable vscgData) {
		for(Map<String, String> env : vscgData.getRows()) {
			String bladeId = env.get("bladeId");
			Scg scg = new Scg();
			scg.setBladeId(bladeId);
			int numberOfDp = Integer.parseInt(env.get("#connected_dp"));
			scg.setDps(generateDp(numberOfDp));
			scgs.add(scg);
		}
	}
	
	
	private List<Dp> generateDp(int numberOfDp) {
		List<Dp> dps = new LinkedList<>();
		for(int i = 0; i < numberOfDp; i++) {
			Dp dp = new Dp();
			dps.add(dp);
		}
		return dps;
	}

	@When("$amountOfDp vDP named $dpName connects to the vSCG node $bladeId")
	public void connectDpToScg(int amountOfDp, String dpName, String bladeId) {
		Scg theScg = findScgWithBladeId(bladeId);
		
		Dp joinDp = new Dp();
		joinDp.setName(dpName);
		try {
			theScg.addDp(joinDp);
		} catch(Exception e) {
			rejectDp = joinDp;
		}
		
	}
	
	private Scg findScgWithBladeId(String bladeId) {
		for(Scg scg:scgs) {
			if(scg.getBladeId().equals(bladeId)) {
				return scg;
			}
		}
		return null;
	}

	@Then("the $dpName should be rejected")
	public void shouldRejectDp(String dpName) {
		assertThat(rejectDp.getName(), is(equalTo(dpName)));
	}
	
	
//	@Then("the fifthDp should be rejected")
//	public void thenTheFifthDpShouldBeRejected(){
//		  
//	}
//	@When("1 vDP named fifthDp connects to the vSCG node")
//	public void when1VDPNamedFifthDpConnectsToTheVSCGNode(){
//	}
//	@Given("4 vDPs have connected to 1 vSCG node ")
//	public void given4VDPsHaveConnectedTo1VSCGNode(){
//	}
//	@When("1 vDP named ninthDp connects to the vSCG node")
//	public void when1VDPNamedNinthDpConnectsToTheVSCGNode(){
//	}
//	@Given("8 vDPs have connected to 1 vSCG node")
//	public void given8VDPsHaveConnectedTo1VSCGNode(){
//	}
//	@Then("the ninthDp should be rejected")
//	public void thenTheNinthDpShouldBeRejected(){
//	}
	
}
