package com.web.userlogin;

import com.web.userlogin.model.User;
import com.web.userlogin.service.UserService;
import com.web.userlogin.validator.UserValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
public class UserUnitTest {

    private MockMvc mvc;

    @Mock
    private UserService mockUserService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    TestHelper testHelper;

    UserValidator userValidator = new UserValidator();

    @Mock
    User mockUser;

    @Mock
    Errors mockErrors;

    User userForm;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        userForm = testHelper.createUser();

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }



     @Test
     public void test_Create_User() throws Exception{
         User user= testHelper.createUser();
         when(mockUserService.save(user)).thenReturn(user);

         User createdUser=mockUserService.save(user);
         assertThat(createdUser.getUserName()).isEqualTo("niketb2309");
         assertThat(createdUser.getAddress()).isEqualTo("6-105, Aryanagar, Nizamabad");

     }


    @Test
    public void test_UserName_validation_works() throws Exception{
        userForm.setUserName("niket");
        Errors errors = new BeanPropertyBindingResult(userForm, "user");
        userValidator.validate(userForm, errors);
        Assert.assertEquals(errors.getFieldError("userName").getDefaultMessage(),"size should be more than 6 charecters");
      }

    @Test
    public void test_Password_Length_Validation_works() throws Exception{
        userForm.setPassword("N!ket");
        Errors errors = new BeanPropertyBindingResult(userForm, "user");
        userValidator.validate(userForm, errors);
        Assert.assertEquals(errors.getFieldError("password").getDefaultMessage(),"size should be more than 6 charecters");
    }

    @Test
    public void test_Password_ConfirmPassword_Match(){
        userForm.setPassword("niket123");
        userForm.setConfirmPassword("niket12344");
        Errors errors = new BeanPropertyBindingResult(userForm, "user");
        userValidator.validate(userForm, errors);
        Assert.assertEquals(errors.getFieldError("confirmPassword").getDefaultMessage(),"Passwords do not match");
    }

    @Test
    public void test_Mobille_Length_Validation_works() throws Exception{
        userForm.setPhoneNumber("123456789009999999");
        Errors errors = new BeanPropertyBindingResult(userForm, "user");
        userValidator.validate(userForm, errors);
        Assert.assertEquals(errors.getFieldError("phoneNumber").getDefaultMessage(),"size should be less than 15");
    }


    @Test
    public void testAddress_validation(){
        userForm.setAddress("H. No 6-104");
        Errors errors = new BeanPropertyBindingResult(userForm, "user");
        userValidator.validate(userForm, errors);
        Assert.assertEquals(errors.getFieldError("address").getDefaultMessage(),"size should be greater than 15");
    }
}
