package com.adamstraub.tonsoftacos.tonsoftacos.config;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager = theEntityManager;

    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
//        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.POST, HttpMethod.DELETE};
//        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE};
        HttpMethod[] theUnsupportedActions = {HttpMethod.DELETE};

        config.getExposureConfiguration()
                .forDomainType(MenuItem.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        config.getExposureConfiguration()
                .forDomainType(OrderItem.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

//        internal helper
        exposeIds(config);

    }

    private void exposeIds(RepositoryRestConfiguration config) {


        // get a list off all entity classes from entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();


        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());

        }
        //expose the ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
