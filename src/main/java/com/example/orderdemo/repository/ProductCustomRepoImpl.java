package com.example.orderdemo.repository;

import com.example.orderdemo.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductCustomRepoImpl implements ProductCustomRepo {
    @PersistenceContext
    EntityManager em;

    public List<Product> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);
        CriteriaQuery<Product> select = cq.select(productRoot);
        TypedQuery<Product> query = em.createQuery(select);
        return query.getResultList();
    }

    //    Single or MultipleExpression
//    Là select 1 hay nhiều cột
    public List<Product> singleOrMultipleExpression() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);
        //        Đây là single
        cq.select(productRoot.get("name"));
//        đây là multi
//        cq.multiselect(productRoot.get("name"),productRoot.get("image"));
        CriteriaQuery<Product> select = cq.select(productRoot);
        TypedQuery<Product> query = em.createQuery(select);
        return query.getResultList();
    }

    //    whereByPredicate
    public List<Product> whereByNameOrId(String name, Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);
        cq.multiselect(productRoot.get("id"), productRoot.get("name"), productRoot.get("price"));
        cq.orderBy(cb.asc(productRoot.get("price")));
        Predicate predicateByName = cb.equal(productRoot.get("name"), name);
        Predicate predicateById = cb.equal(productRoot.get("id"), id);
        Predicate condition = cb.or(predicateById, predicateByName);
        cq.where(condition);
        CriteriaQuery<Product> select = cq.select(productRoot);
        TypedQuery<Product> query = em.createQuery(select);
        return query.getResultList();
    }

    //    Group by and having and Order
    public List<Product> groupByAndHavingAndOrder() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);
        cq.multiselect(productRoot.get("id"), productRoot.get("name"), cb.sum(productRoot.get("price"))).groupBy(productRoot.get("name"), productRoot.get("id"));
        cq.having(cb.gt(cb.sum(productRoot.get("price")),2000));
        cq.orderBy(cb.asc(productRoot.get("price")));
        CriteriaQuery<Product> select = cq.select(productRoot);
        TypedQuery<Product> query = em.createQuery(select);
        return query.getResultList();
    }

}
