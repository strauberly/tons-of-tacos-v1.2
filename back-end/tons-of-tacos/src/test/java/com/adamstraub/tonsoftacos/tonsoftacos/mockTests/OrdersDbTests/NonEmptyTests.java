package com.adamstraub.tonsoftacos.tonsoftacos.mockTests.OrdersDbTests;

        import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;


        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.http.MediaType;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

        import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
    @TestPropertySource("classpath:test-application.properties")
@Sql(scripts = {
        "classpath:/test-schema.sql",
        "classpath:/test-data.sql",
},
        config = @SqlConfig(encoding = "utf-8"))

public class NonEmptyTests extends OwnersToolsTestsSupport{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Mock
//    private OrdersRepository ordersRepository;

//    @Mock
//   private CustomerRepository customerRepository;

//    @Mock
//    private OrdersRepository ordersRepositoryMock;
/*
    @Mock
    GlobalExceptionHandler exceptionHandler;

    @Mock
    JwtAuthFilter jwtAuthFilter;
    @Mock
    JwtService jwtService;

    @Mock
    SecurityConfig securityConfig;
    @Mock
    AuthService authService;

 */

//    @InjectMocks
//    private OwnersOrdersService ownersOrdersService;

//    @Autowired
//    OwnersOrdersService ownersOrdersService;


//Break out into two different tests
//    @mockbean for 404

//    @InjectMocks
//    OwnersOrdersService ownersOrdersService;

//    not mocking just calling the db and returning no need for mockito - consider use case and if neccesary

    @Test
        void nonEmptyOrdersDBTest200() throws Exception {
                RequestBuilder request = MockMvcRequestBuilders
                .get(getBaseUriForGetAllOrders())
                .header("Authorization", "Bearer " + buildGoodToken())
                .accept(MediaType.APPLICATION_JSON);
//        System.out.println(request);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//        System.out.println(result.getRequest());
//        assert results
//        System.out.println(result.getResponse().getContent);
        Assertions.assertNotNull(result.getResponse().getContentAsString());

        System.out.println(result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());
        System.out.println("All orders returned successfully.");
    }

    @Test
    void nonEmptyCustomersDBTest200() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get(getBaseUriForGetAllCustomers())
                .header("Authorization", "Bearer " + buildGoodToken())
                .accept(MediaType.APPLICATION_JSON);
//        System.out.println(request);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//        System.out.println(result.getRequest());
        Assertions.assertNotNull(result.getResponse().getContentAsString());
        System.out.println(result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());
        System.out.println("All customers returned successfully.");
    }
//        OrderReturnedToOwner orderReturnedToOwner = mock(OrderReturnedToOwner.class);
//        ownersOrdersService.getAllOrders() = null
////        OwnersOrdersService mockOwnersOrdersService = mock(OwnersOrdersService.class);
//        when(mockOwnersOrdersService.getAllOrders().isEmpty()).thenThrow(EntityNotFoundException.class);
//     List<OrderReturnedToOwner>  ordersList = new ArrayList<>();
//        System.out.println("blank orders = " + ordersList);
//        Exception exception = EntityNotFoundException.class.newInstance();
//        when(ordersRepository.findAll()).thenThrow(EntityNotFoundException.class);
//        when(ordersRepository.findAll()).thenThrow(new EntityNotFoundException("No orders found. Verify data integrity."));
//        ownersOrdersService.getAllOrders();
//       when(ordersRepository.findAll()).thenReturn(null);
//        when(ownersOrdersService.getAllOrders()).thenThrow(new EntityNotFoundException("No orders found. Verify data integrity."));
//        System.out.println(ownersOrdersService.getAllOrders());
//        System.out.println(exception.getMessage());
//        assert(exception.getMessage().contains("No orders found. Verify data integrity."));



////
////
//        RequestBuilder request = MockMvcRequestBuilders
//                .get(getBaseUriForGetAllOrders())
//                .header("Authorization", "Bearer " + buildGoodToken())
//                .accept(MediaType.APPLICATION_JSON);
////        System.out.println(request);
//        MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound()).andReturn();
//
////        System.out.println(result.getRequest());
////        System.out.println(result.getResponse().getStatus());
//        System.out.println(result.getResponse().getContentAsString());
//        System.out.println("Test for unsuccessful use case complete.");
//
////        Exception exception = assertThrows(EntityNotFoundException.class, () -> ownersOrdersService.getAllOrders().isEmpty());
////        String expectedMessage = "No orders found. Verify data integrity.";
////        String actualMessage = exception.getMessage();
////        assertTrue(actualMessage.contains(expectedMessage));
////
////        Exception exception = new EntityNotFoundException();
////        given(ownersOrdersService.getAllOrders()).willThrow(exception);
//    }

}