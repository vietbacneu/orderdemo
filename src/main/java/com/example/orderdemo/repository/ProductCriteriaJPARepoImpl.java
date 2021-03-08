package com.example.orderdemo.repository;

import com.example.orderdemo.entity.Product;
import com.example.orderdemo.entity.Product_;
import com.example.orderdemo.request.SearchRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductCriteriaJPARepoImpl {
        //basic
    public static Specification<Product> byName(String name) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"), "%" + name + "%");
            }
        };
    }
    //basic
    public static Specification<Product> byId(Long id) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("id"), id);
            }
        };
    }

    public static Specification<Product> productSpecification(SearchRequest searchRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate predicate = criteriaBuilder.equal(root.get(Product_.category).get("id"), searchRequest.getIdCat());
            Predicate predicate1 = criteriaBuilder.like(root.get(Product_.name), searchRequest.getProName());
            if (searchRequest.getIdCat() != null) {
                predicates.add(predicate);
            }
            if (searchRequest.getProName() != null && searchRequest.getProName().trim().length() > 0) {
                predicates.add(predicate1);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
