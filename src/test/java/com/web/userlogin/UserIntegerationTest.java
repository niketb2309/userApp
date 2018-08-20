package com.web.userlogin;

import com.web.userlogin.model.User;
import com.web.userlogin.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {UserloginApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages="com")
public class UserIntegerationTest {

	TestHelper helper=new TestHelper();

	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	UserService userService;

	@Autowired
	User user;

	@Before
	public void setup() {
		helper.createUser(userService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void displayHomePageTest() throws Exception {
		this.mockMvc.perform(get("/"))
					.andExpect(status().isOk());
	}

	@Test
	public void submitRegistrationAccountExists() throws Exception {
		this.mockMvc
				.perform(
						post("/createUser")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED)
								.param("name", "Niket Bhagwath")
								.param("userName", "niketb209")
								.param("password", "N!ket@1987")
								.param("confirmPassword", "N!ket@1987")
								.param("gender", "Male")
								.param("phoneNumber", "9177596622")
								.param("emailId", "niketb2309@gmail.com")
								.param("address", "6-105, Aryanagar,Nizamabad")
				)
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("userName", "niketb209"))
				.andExpect(model().attribute("password", "N!ket@1987"))
				.andExpect(status().isOk());
	}

	@Test
	public void registrationFormValidationTest() throws Exception {
		this.mockMvc
				.perform(
						post("/createUser")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED)
								.param("name", "Niket Bhagwath")
								.param("userName", "niket")
								.param("password", "N!ket@1987")
								.param("confirmPassword", "N!ket@1987")
								.param("gender", "Male")
								.param("phoneNumber", "9177596622")
								.param("emailId", "niketb2309@gmail.com")
								.param("address", "6-105, Aryanagar,Nizamabad")

				)
				.andExpect(model().hasErrors())
				.andExpect(model().attributeHasFieldErrorCode("user","userName","Size.userForm.username"))
						.andExpect(status().isOk());
	}

	@Test
	public void loginUserTest() throws Exception {
		this.mockMvc
				.perform(
						post("/login")
								.param("userName", "niketb2309")
								.param("password", "N!ket@1987")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED)

				)
				.andExpect(model().hasNoErrors())
				.andExpect(status().isOk());
	}

	@Test
	public void loginUserValidation() throws Exception {
		this.mockMvc
				.perform(
						post("/login")
								.param("userName", "niketb2309")
								.param("password", "N!ket@198")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED)

				)
				.andExpect(model().hasErrors())
				.andExpect(model().attributeHasFieldErrorCode("login","password",
						"Incorrect.Password"))
				.andExpect(status().isOk());

	}

}
