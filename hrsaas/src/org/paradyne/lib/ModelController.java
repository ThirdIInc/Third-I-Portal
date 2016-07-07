
/**
 * 
 * 	@Author 	: sachin hegde
 * 	@purpose 	: To call the model through a centralized point
 * 
 * 
 */
package org.paradyne.lib;

 import org.paradyne.lib.ModelBase;

public class ModelController {
	
	public Object getModel(int id) {		
		String modelName=org.paradyne.model.ModelLab.getModel(id);
		try{
			return Class.forName(modelName).newInstance();
		}catch(Exception e){}
		return new Object();
	}
}