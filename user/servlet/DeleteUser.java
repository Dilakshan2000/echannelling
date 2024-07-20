package com.user.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.db.DBConnect;

@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		UserDao dao = new UserDao(DBConnect.getConn());
		HttpSession session = req.getSession();

		if (dao.deleteUser(id)) {
			session.removeAttribute("userObj");
			session.setAttribute("succMsg", "Appointment Delete Sucessfully..");
			resp.sendRedirect("./user_login.jsp");
		} else {
			session.setAttribute("errorMsg", "something wrong on server");
			resp.sendRedirect("./index.jsp");
		}
		

	}

}