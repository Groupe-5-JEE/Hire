package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TechnicalManager")
public class TechnicalManager extends Employee implements I_TechnicalManager {

	/**
	 * 
	 */
	
	@Override
	public void createVehicle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVehicle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVehicle() {
		// TODO Auto-generated method stub
		
	}

}
