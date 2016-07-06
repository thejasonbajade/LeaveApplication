package com.orangeandbronze.leaveapp.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class LeaveApplicationControllerTest {
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testShowApplyLeave() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/apply_leave"));
		
		results.andExpect(MockMvcResultMatchers.status().isOk());
		results.andExpect(MockMvcResultMatchers.view().name("apply_leave"));
		results.andExpect(MockMvcResultMatchers.model().attributeExists("departments"));
		results.andExpect(MockMvcResultMatchers.model().attributeExists("supervisors"));		
	}
	
	@Test
	public void testProcessLeaveApplication() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/procees_leave_application")
				.param("leaveType", "SICK_LEAVE")
				.param("supervisorId", "1")
				.param("startDate", "2016-07-03")
				.param("startHalfDay", "startHalfDay")
				.param("endDate", "2016-07-03")
				.param("endHalfDay", "endHalfDay")
				.param("reason", "I am sick"));
		
		
		results.andExpect(MockMvcResultMatchers.status().isOk());
		results.andExpect(MockMvcResultMatchers.view().name("redirect:/view_leave_history"));
	}
	
	@Test
	public void testShowLeaveHistoryOfEmployee() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/view_leave_history"));
		
		results.andExpect(MockMvcResultMatchers.status().isOk());
		results.andExpect(MockMvcResultMatchers.view().name("leave_history"));
		results.andExpect(MockMvcResultMatchers.model().attributeExists("leaveApplications"));
	}

	@Test
	public void testShowLeaveHistoryForSupervisor() throws Exception {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/view_all_leave_history"));
		
		results.andExpect(MockMvcResultMatchers.status().isOk());
		results.andExpect(MockMvcResultMatchers.view().name("leave_list"));
		results.andExpect(MockMvcResultMatchers.model().attributeExists("leaveApplications"));
	}
	
	@Test
	public void testLeaveApplicationApproval() {
		
	}
	
	@Test
	public void testLeaveApplicationDispproval() {
		
	}
	
	@Test
	public void testLeaveApplicationCancellation() {
		
	}
	
	@Test
	public void testLeaveApplicationNotTaken() {
		
	}
}
