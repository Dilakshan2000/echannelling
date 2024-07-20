package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AppointmentDAO;
import com.db.DBConnect;

@WebServlet("/updateRecord")
public class UpdateRecord extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String disc = req.getParameter("disc");

			AppointmentDAO dao = new AppointmentDAO(DBConnect.getConn());

			HttpSession session = req.getSession();

			if (dao.updateRecord(id,disc)) {
				session.setAttribute("succMsg", "Record Added");
				resp.sendRedirect("doctor/patient.jsp");
			} else {
				session.setAttribute("errorMsg", "Something wrong on server");
				resp.sendRedirect("doctor/patient.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}