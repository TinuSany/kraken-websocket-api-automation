package hooks;

import java.io.IOException;

import extensions.ScenarioContext;
import extensions.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	//This hook will be executed before each Scenario and this has the higher priority
		@Before(order = 1)
		public void inIt() {
			boolean inItStatus = ScenarioContext.initializeScenarioContext();
			if (inItStatus)
				try {
					Utility.inItLogger();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		//This hook will be executed before each Scenario and this has the lower priority
		@Before(order = 2)
		public void beforeScenarioUpdateLogger(Scenario s) {
			try {
				Utility.BeforeScenarioAppendLogger(s.getName().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//This hook will be executed after each Scenario and this has the higher priority
		@After(order = 1)
		public void afterScenarioUpdateLogger(Scenario s) {

			try {
				Utility.AfterScenarioAppendLogger(s.getName().toString(), s.getStatus().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}
