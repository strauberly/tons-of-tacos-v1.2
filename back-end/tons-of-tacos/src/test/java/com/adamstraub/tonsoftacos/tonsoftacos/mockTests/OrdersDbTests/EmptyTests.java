package com.adamstraub.tonsoftacos.tonsoftacos.mockTests.OrdersDbTests;

import com.adamstraub.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmptyTests extends OwnersToolsTestsSupport {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrdersRepository ordersRepository;

    @MockBean
    private CustomerRepository customerRepository;



}
