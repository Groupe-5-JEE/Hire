package com.hire.controller;

import com.hire.service.CustomerServiceInterface;
import com.hire.service.impl.CustomerServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stats")
public class BestCustomerController extends BaseController {

	private static final String pageName = "/views/stats/stats.jsp";
	private static final String pageTitle = "Statistiques clients";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(isAuthenticated(req, resp))
		{
			if(!employeeService.canAccessStats(getEmployee(req)))
				redirectToHome(req, resp);
			else
			{
				CustomerServiceInterface service = new CustomerServiceImp();

				req.setAttribute("bestHiring", service.getBestHiringCustomer());
				req.setAttribute("bestBuyer", service.getBestBuyerCustomer());

				redirectToView(req, resp, pageName, pageTitle);
			}
		}
	}
}
