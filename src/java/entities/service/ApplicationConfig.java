/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author jaume
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(entities.service.AddressesFacadeREST.class);
        resources.add(entities.service.CitiesFacadeREST.class);
        resources.add(entities.service.CustomersFacadeREST.class);
        resources.add(entities.service.OrderLinesFacadeREST.class);
        resources.add(entities.service.OrdersFacadeREST.class);
        resources.add(entities.service.ProductPricesFacadeREST.class);
        resources.add(entities.service.ProductProviderFacadeREST.class);
        resources.add(entities.service.ProductsCategoriesFacadeREST.class);
        resources.add(entities.service.ProductsFacadeREST.class);
        resources.add(entities.service.ProvidersFacadeREST.class);
        resources.add(entities.service.ShipTypeFacadeREST.class);
    }
    
}
