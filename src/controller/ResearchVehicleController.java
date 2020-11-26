package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Airplane;
import model.Car;
import model.Motorbike;
import model.Vehicle;
import service.VehicleServiceImp;
import service.VehicleServiceInterface;

@WebServlet("/vehicle/research")
public class ResearchVehicleController extends BaseController {

	private static final long serialVersionUID = 1L;

	private final String pageName = "/vehicle/research.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VehicleServiceInterface vService = new VehicleServiceImp();
		List<String> models = vService.getAllModels();
		List<String> brands = vService.getAllBrand();
		if (isAuthenticated(req, resp)) {

			List<Vehicle> vehicles = vService.getAll();
			req.setAttribute("models", models);
			req.setAttribute("brands", brands);
			req.setAttribute("vehicles", vehicles);
			redirectToView(req, resp, pageName);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (isAuthenticated(req, resp)) {
			VehicleServiceInterface vService = new VehicleServiceImp();
			List<String> models = vService.getAllModels();
			List<String> brands = vService.getAllBrand();

			String vehicleType = req.getParameter("vehicleType");
			String brand = req.getParameter("brand");
			String model = req.getParameter("model");
			System.out.print("test serach 1");

			if ((vehicleType.equals("not-selected"))
					&& (brand.equals("not-selected") && model.equals("not-selected"))) {
				System.out.print("test serach xxxxxx");
				List<Vehicle> vehiclesParType = vService.getAllParType(vehicleType);
				List<Vehicle> vehicles = vService.getAllByCriteria(model, brand);
				
				req.setAttribute("vehicles", vehicles);
				req.setAttribute("msg", "msg");
			} else {
				if ((!vehicleType.equals("not-selected"))
						&& (brand.equals("not-selected") && model.equals("not-selected"))) {
					
					List<Vehicle> vehiclesParType = vService.getAllParType(vehicleType);
					req.setAttribute("vehicles", vehiclesParType);

					req.setAttribute("msg", "msg");
				} else {
					switch (vehicleType) {
					case "Car":
						List<Car> vehiclesCar = vService.getAllCarByCriteria(model, brand);
						
						req.setAttribute("vehicles", vehiclesCar);
						req.setAttribute("msg", "msg");

						break;
					case "Motorbike":
						List<Motorbike> vehiclesMotorbike = vService.getAllMotorbikeByCriteria(model, brand);
					
						req.setAttribute("vehicles", vehiclesMotorbike);
						req.setAttribute("msg", "msg");

						break;
					case "Airplane":
						List<Airplane> vehiclesAireplane = vService.getAllAirplaneByCriteria(model, brand);
						
						req.setAttribute("vehicles", vehiclesAireplane);
						req.setAttribute("msg", "msg");

						break;
					default:
						List<Vehicle> vehicles = vService.getAllByCriteria(model, brand);
						req.setAttribute("models", models);
					
						req.setAttribute("vehicles", vehicles);
						req.setAttribute("msg", "msg");

					}

				}
			}

			req.setAttribute("models", models);
			req.setAttribute("brands", brands);
			redirectToView(req, resp, pageName);

		}

	}
}
