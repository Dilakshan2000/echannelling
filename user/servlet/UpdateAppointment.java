package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AppointmentDAO;
import com.db.DBConnect;
import com.entity.Appointment;

@WebServlet("/updateAppointment")
public class UpdateAppointment extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String fullName = req.getParameter("fullName");
			String gender = req.getParameter("gender");
			String age = req.getParameter("age");
			String appoint_date = req.getParameter("appoint_date");
			String diseases = req.getParameter("diseases");
			int doctor_id = Integer.parseInt(req.getParameter("doctor_id"));
			String status = req.getParameter("status");
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println("fullName 2: " + id);
			Appointment ap = new Appointment(id,fullName,gender,age,appoint_date,"","",diseases,doctor_id,"",status);

			AppointmentDAO dao = new AppointmentDAO(DBConnect.getConn());
			HttpSession session = req.getSession();

			if (dao.updateAppointment(ap)) {
				session.setAttribute("succMsg", "Appointment Update Sucessfully..");
				resp.sendRedirect("./view_appointment.jsp");
			} else {
				session.setAttribute("errorMsg", "something wrong on server");
				resp.sendRedirect("./view_appointment.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
