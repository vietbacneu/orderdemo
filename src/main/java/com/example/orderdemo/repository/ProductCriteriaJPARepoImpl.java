package com.example.orderdemo.repository;

import com.example.orderdemo.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public  class ProductCriteriaJPARepoImpl {

        public static Specification<Product> byName(String name){
            return new Specification<Product>() {
                @Override
                public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.like(root.get("name"),"%"+name+"%");
                }
            };
        }
    public static Specification<Product> byId(Long id){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("id"),id);
            }
        };
    }
}
