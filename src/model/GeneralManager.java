package model;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class GeneralManager extends Employee implements I_CommercialManager, I_CustomerManager, I_TechnicalManager {

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

	@Override
	public void createClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getTheTenMostSpending() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getTheTenMoreHiring() {
		// TODO Auto-generated method stub
		
	}

}
