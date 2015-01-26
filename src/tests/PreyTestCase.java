package tests;

import java.util.ArrayList;

import junit.framework.TestCase;
import model.core.Agent;
import model.core.Environment;
import model.core.hunt.Hunter;
import model.core.hunt.Prey;

import org.junit.Test;

public class PreyTestCase extends TestCase {
	Prey p;
	
	Environment env;
	
	@Override
	public void setUp()
	{
		this.env=new Environment(5, Agent.class);
		Hunter h1=new Hunter(0, 0, env);
		Hunter h2=new Hunter(1, 1, env);
		Hunter h3=new Hunter(4, 4, env);
		env.addAgent(h1);
		env.addAgent(h2);
		env.addAgent(h3);
		this.p=new Prey(3, 3, env);
		env.addAgent(p);
	}

	@Test
	public void test_getAllHunter() {
		assertEquals(3, this.env.getAgents().size());
		ArrayList<Agent> hunters=this.env.getAgents();
		//verifison leur position
		assertEquals(hunters.get(0).posX,0);
		assertEquals(hunters.get(0).posY,0);

		assertEquals(hunters.get(1).posX,1);
		assertEquals(hunters.get(1).posY,1);

		assertEquals(hunters.get(2).posX,4);
		assertEquals(hunters.get(2).posY,4);
	}

	@Test
	public void test_hunterPlusProche() {

		Hunter h=(Hunter) this.env.getAgents().get(2);
		assertEquals(h.posX, 4);
		assertEquals(h.posY, 4);

	}


}
