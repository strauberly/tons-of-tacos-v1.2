package com.adamstraub.tonsoftacos.tonsoftacos.mockTests.OrdersDbTests;

//import com.adamstraub.tonsoftacos.controllers.menuItemControllers.MenuItemControllerInterface;
//import com.adamstraub.tonsoftacos.dao.MenuItemRepository;
//import com.adamstraub.tonsoftacos.dao.OrdersRepository;
//import com.adamstraub.tonsoftacos.dto.businessDto.OrderReturnedToOwner;
//import com.adamstraub.tonsoftacos.exceptionHandler.GlobalExceptionHandler;
//import com.adamstraub.tonsoftacos.services.menuItemServices.MenuItemService;
//import com.adamstraub.tonsoftacos.services.ownersServices.orders.OwnersOrdersService;
import com.adamstraub.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.dao.OrdersRepository;
        import com.adamstraub.tonsoftacos.services.ownersServices.orders.OwnersOrdersService;
        import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import jakarta.persistence.EntityNotFoundException;
//import org.aspectj.lang.annotation.Before;
        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
//import org.mockito.Mock;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.request.WebRequest;
//
//import java.lang.reflect.Array;
//import java.util.List;
//import java.util.Map;
//
//import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.willThrow;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

        import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.when;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

        import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
//====================================================================
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(controllers = MenuItemController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
//@WebMvcTest(secure = false)
//@SpringBootTest
//@AutoConfigureMockMvc
//
////@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
////@TestPropertySource("classpath:/test-application.properties")
////@Sql(scripts = {
////        "classpath:/test-schema.sql",
////        "classpath:/test-data.sql",
////},
////        config = @SqlConfig(encoding = "utf-8"))
//
//
////public class EmptyDBTest{
//
//class EmptyDBTests{
//    @Autowired
//    public MockMvc mockMvc;
//    @Autowired
//    public JdbcTemplate jdbcTemplate;
//@Nested
//    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//    @TestPropertySource("classpath:/test-application.properties")
//    @Sql(scripts = {
//            "classpath:/test-schema.sql",
//            "classpath:/test-data.sql",
//    },
//            config = @SqlConfig(encoding = "utf-8"))
//
//class testThatDoesNotPolluteTheApplicationContextUris extends OwnersToolsTestsSupport {
//
////    @Autowired
////    public MockMvc mockMvc;
//
//    @MockBean
//    public TestRestTemplate restTemplate;
//
//
//    //    @MockBean
//
////    @Autowired
////    private WebApplicationContext context;
//
////    @Before
////    private void setUp(){
////        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
////    }
//
////@Mock
////    private OrdersRepository ordersRepository;
////@Mock
////private GlobalExceptionHandler globalExceptionHandler;
////@InjectMocks
////    private OwnersOrdersService ownersOrdersService;
////
////@Mock
////private MenuItemRepository menuItemRepository;
////
////    @Mock
////    private JwtAuthFilter jwtAuthFilter;
//
//    //    @Mock
////    private JwtService jwtService;
//    @MockBean
//    private MenuItemService menuItemService;
//
////@Mock
////    MenuItemServiceInterface menuItemServiceInterface;
////
////@Mock
////private MenuItemControllerInterface menuItemControllerInterface;
//
//    @MockBean
//    private MenuItemController menuItemController;
//
//    @MockBean
////private MenuItemController menuItemController;
//    private JwtService jwtService;
//
//    @MockBean
//    private OwnerRepository ownerRepository;
//
////@MockBean
////private  JdbcTemplate jdbcTemplate;
//
//    @Test
////@WebMvcTest
//    void noOrdersTest404() throws Exception {
////    mock the error return response
////    ResponseEntity<Map<String, Object>> response;
////    List<OrderReturnedToOwner> allOrders = null;
//////    when(ordersRepository.findAll()).thenThrow(EntityNotFoundException.class);
////    given(ordersRepository.findAll()).willThrow(EntityNotFoundException.class);
////    assertThrows(ownersOrdersService.getAllOrders(), () -> willThrow());
////        when(ordersRepository.findAll()).thenReturn(null).thenThrow();
////        mockMvc
////                .perform((RequestBuilder) RestAssuredMockMvc.get("owners-tools/orders/get-orders")).andExpect(status().isNotFound());
//
////    ResultActions response = mockMvc.perform(get(""))
////    MvcResult result = (MvcResult) mockMvc.perform((RequestBuilder)get("/yourcontrollerurl")).andExpect(MockMvcResultMatchers.status().isNotFound());
//
////    we want given ordersrepository find all will throw 404 we want to verify that the response contains our exception handler.
//
////    OwnersToolsTestsSupport ownersToolsTestsSupportMock = mock(OwnersToolsTestsSupport.class);
//
////    String get
//// figure out how to print
////    RequestBuilder request = MockMvcRequestBuilders
//////            .get("/api/owners-tools/orders/get-orders")
//////            .get("/owners-tools/orders/get-orders")
//////            .get("http://localhost:8080/api/menu/id?id=2")
////            .get( "http://localhost:8080/api/menu/id?id=2")
////            .header("Authorization", buildGoodToken())
////            .accept(MediaType.APPLICATION_JSON);
////    System.out.println(request);
////    MvcResult result = mockMvc.perform(request).andReturn();
////    System.out.println(result.getRequest());
//
//        RequestBuilder request = MockMvcRequestBuilders
////            .get("/api/owners-tools/orders/get-orders")
////            .get("/owners-tools/orders/get-orders")
////            .get("http://localhost:8080/api/menu/id?id=2")
//                .get("http://localhost:8080/api/owners-tools/orders/get-orders")
//                .header("Authorization", buildGoodToken())
//                .accept(MediaType.APPLICATION_JSON);
//        System.out.println(request);
//        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//        System.out.println(result.getRequest());
////    assert(result.getResponse().getStatus());
//
//
//    }
//}
//}
//==========================================

//@Nested
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
        System.out.println(result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());
        System.out.println("All orders returned successfully.");
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