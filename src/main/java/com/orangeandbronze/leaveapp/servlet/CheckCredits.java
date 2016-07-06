package com.orangeandbronze.leaveapp.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.domain.LeaveCredits;
import com.orangeandbronze.leaveapp.domain.LeaveDetails;
import com.orangeandbronze.leaveapp.domain.LeaveType;

/**
 * Servlet implementation class CheckCredits
 */
public class CheckCredits extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws ParseException
	 * @see HttpServlet#HttpServlet()
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		response.setContentType("text/html;charset=UTF-8");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		LeaveCredits lc = new LeaveCredits.Builder()
				.vacationLeaveCredits(Float.parseFloat(request.getParameter("vlcredits")))
				.sickLeaveCredits(Float.parseFloat(request.getParameter("slcredits")))
				.emergencyLeaveCredits(Float.parseFloat(request.getParameter("elcredits")))
				.offsetLeaveCredits(Float.parseFloat(request.getParameter("olcredits"))).build();
		Employee e = new Employee(0, null, lc);
		
		DateFormat formatter = new SimpleDateFormat("yyyy-M-d");
		Date start = null, end = null;
		try {
			 start = (Date) formatter.parse(request.getParameter("startdate"));
			 end = (Date) formatter.parse(request.getParameter("enddate"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		LocalDate startC = LocalDate.from(start.toInstant());
		LocalDate endC = LocalDate.from(end.toInstant());
		LeaveType lt = LeaveType.valueOf(request.getParameter("leavetype"));
		boolean isStartHalfDay = request.getParameter("startHalfDay") == null;
		boolean isEndHalfDay = request.getAttribute("endHalfDay") == null;
		
		LeaveDetails details = new LeaveDetails(startC, isStartHalfDay, endC, isEndHalfDay, lt, request.getParameter("reason"));
		LeaveApplication la = new LeaveApplication(details, e, null);
		
		
		String json = new Gson().toJson(la);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
