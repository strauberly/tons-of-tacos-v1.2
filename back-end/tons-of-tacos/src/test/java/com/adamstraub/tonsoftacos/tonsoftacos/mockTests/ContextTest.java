//package com.adamstraub.tonsoftacos.tonsoftacos.mockTests;
//
//import com.adamstraub.tonsoftacos.tonsoftacos.controllers.ownersControllers.customers.OwnersCustomersController;
//import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.customers.OwnersCustomersService;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static io.restassured.authentication.FormAuthConfig.springSecurity;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@WebMvcTest(OwnersCustomersController.class)
//class OwnersCustomersControllerTest{
//    @MockBean
//    private OwnersCustomersService ownersCustomersService;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setupMockMvc(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
//    }
//
//    @Test
//    void shouldCreateMockMvc(){
//        assertNotNull(mockMvc);
//    }
//}
