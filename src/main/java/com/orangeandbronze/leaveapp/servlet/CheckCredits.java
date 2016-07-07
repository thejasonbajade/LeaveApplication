package com.orangeandbronze.leaveapp.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;

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
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");

		LocalDate startC = LocalDate.parse(start, dtf);
		LocalDate endC = LocalDate.parse(end, dtf);
		LeaveType lt = LeaveType.valueOf(request.getParameter("leaveType"));
		boolean isStartHalfDay = Boolean.valueOf(request.getParameter("startHalfDay"));
		boolean isEndHalfDay = Boolean.valueOf(request.getParameter("endHalfDay"));
		
		LeaveDetails details = new LeaveDetails(startC, isStartHalfDay, endC, isEndHalfDay, lt, request.getParameter("reason"));
		
		String json = new Gson().toJson(details);
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
