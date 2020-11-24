package controller;

import model.Customer;
import service.CustomerServiceImp;
import service.CustomerServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client/modify")
public class ModifyCustomerController extends BaseController {
	public static final String pageName = "/client/modify.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(isAuthenticated(req, resp))
		{
			CustomerServiceInterface customerService = new CustomerServiceImp();
			Customer customer = customerService.getById(Integer.parseInt(req.getParameter("id")));

			req.setAttribute("customer",customer);

			redirectToView(req, resp, pageName);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(isAuthenticated(req, resp))
		{
			Customer customer = new Customer();
			customer.setId(Integer.parseInt(req.getParameter("id")));
			customer.setName(req.getParameter("name"));
			customer.setFirstName(req.getParameter("firstName"));
			customer.setAddress(req.getParameter("address"));
			customer.setEmail(req.getParameter("email"));
			customer.setPhone(req.getParameter("phone"));

			CustomerServiceInterface customerService = new CustomerServiceImp();
			customerService.update(customer);

			resp.sendRedirect(req.getContextPath() + "/client/sheet?id=" + customer.getId());
		}
	}
}
