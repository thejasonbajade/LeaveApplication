/*package com.orangeandbronze.leaveapp.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

import rewards.web.ResultActions;

import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Configuration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/root-context.xml",
		"classpath:leaveapp/app-config.xml"
})
public class LeaveApplicationControllerTest {
	

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testShowApplyLeave() {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/apply_leave"));
		
		results.andExpect(MockMvcResultMatchers.status().isOk())
		results.andExpect(MockMvcResultMatchers.view().name("apply_leave"));
		results.andExpect(MockMvcResultMatchers.model().attributeExists("departments"));
		results.andExpect(MockMvcResultMatchers.model().attributeExists("supervisors"));		
	}
	
	@Test
	public void testProcessLeaveApplication() {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/procees_leave_application")
				.param("leaveType", "SICK_LEAVE")
				.param("supervisorId", "1")
				.param("startDate", "03/07/2016")
				.param("startHalfDay", "startHalfDay")
				.param("endDate", "05/07/2016")
				.param("endHalfDay", "endHalfDay")
				.param("reason", "I am sick"));
		
		
		results.andExpect(MockMvcResultMatchers.status().isOk());
		results.andExpect(MockMvcResultMatchers.view().name("redirect:/view_leave_history"));
	}
	
	@Test
	public void testShowLeaveHistoryOfEmployee() {
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.post("/view_leave_history"));
		
		results.andExpect(MockMvcResultMatchers.status().isOk());
		results.andExpect(MockMvcResultMatchers.view().name("leave_history"));
		results.andExpect(MockMvcResultMatchers.model().attributeExists("leaveApplications"));
	}

	@Test
	public void testShowLeaveHistoryForSupervisor() {
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
}*/
