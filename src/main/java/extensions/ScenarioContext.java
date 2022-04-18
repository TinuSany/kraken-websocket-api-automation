package extensions;

import java.util.HashMap;
import java.util.Map;
/*
 * ScenarioContext is used to share the data between steps and tests.
 */

public class ScenarioContext {
	private static  Map<String, Object> scenarioContext;

    public ScenarioContext(){
        scenarioContext = new HashMap<>();
    }

    //setter method
    public static void setContext(String key, Object value) {
        scenarioContext.put(key.toString(), value);
    }
    
    //getter method
    public static Object getContext(String key){
        return scenarioContext.get(key.toString());
    }
    
    //to check a specific key present in the ScenarioContext
    public static Boolean contains(String key){
        return scenarioContext.containsKey(key.toString());
    }
    
    //Initializing scenarioContext for the first time use.
    public static boolean initializeScenarioContext(){
    	if(ScenarioContext.scenarioContext == null)
    	{
    		new ScenarioContext();
    		return true;
    	}
    	return false;
    }

}