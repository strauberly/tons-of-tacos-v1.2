//package com.adamstraub.tonsoftacos.tonsoftacos;
//import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
//import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.MenuItemCrudTestsSupport;
//import lombok.Getter;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.*;
//
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//
////Test basic crud operations// does not test api endpoints and response codes
//// rewrite this as before
//@DataJpaTest
////@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
////@ActiveProfiles("test")
////@Sql(scripts = {
////        "classpath:scripts/tonsOfTacos-Schema.sql",
////        "classpath:scripts/tonsOfTacos-Data.sql"},
////        config = @SqlConfig(encoding = "utf-8"))
//public class MenuItemJPATests extends MenuItemCrudTestsSupport {
//
////    @Autowired
////    private MenuItemRepository menuItemRepository;
//@Autowired
//@Getter
//    private TestRestTemplate restTemplate;
//
//    @Test
////    @Rollback(false)
//    void createMenuItemWith201ResponseTest(){
//
//
//
//    String body = createMenuItemBody();
//    String uri = getBaseUriForMenuItem();
//
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_JSON);
//
//    HttpEntity <String> bodyEntity = new HttpEntity<>(body, headers);
//
//    ResponseEntity<MenuItem> response =
//
////    ResponseEntity<MenuItem> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, MenuItem.class);
//
////   assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//
//
//    }
////    ** CRUD TEST TO BE MIGRATED to network transfer protocol
////    @Test
//////    @Rollback(false)
////    void addMenuItemTestToDB(){
//
////
////
////        MenuItem savedItem = menuItemRepository.save(sample());
////        assertNotNull(savedItem);
////    }
//
//    @Test
//    void getItemFromDatabase(){
//
//    }
//
//}