/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import entities.OrderLines;
import entities.OrderLinesPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author jaume
 */
@Stateless
@Path("orderlines")
public class OrderLinesFacadeREST extends AbstractFacade<OrderLines> {
    @PersistenceContext(unitName = "shopManagementPU")
    private EntityManager em;

    private OrderLinesPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;orderId=orderIdValue;productId=productIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entities.OrderLinesPK key = new entities.OrderLinesPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> orderId = map.get("orderId");
        if (orderId != null && !orderId.isEmpty()) {
            key.setOrderId(new java.lang.Integer(orderId.get(0)));
        }
        java.util.List<String> productId = map.get("productId");
        if (productId != null && !productId.isEmpty()) {
            key.setProductId(new java.lang.Integer(productId.get(0)));
        }
        return key;
    }

    public OrderLinesFacadeREST() {
        super(OrderLines.class);
    }

    @POST
    @Override
    @Consumes("application/json")
    public void create(OrderLines entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void edit(@PathParam("id") PathSegment id, OrderLines entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entities.OrderLinesPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public OrderLines find(@PathParam("id") PathSegment id) {
        entities.OrderLinesPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces("application/json")
    public List<OrderLines> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces("application/json")
    public List<OrderLines> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
