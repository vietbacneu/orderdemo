package com.example.orderdemo.repository;

import com.example.orderdemo.dto.ProCatDTO;
import com.example.orderdemo.dto.ProductDTO;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.entity.ProductDetail;
import com.example.orderdemo.entity.ProductDetail_;
import com.example.orderdemo.entity.Product_;
import com.example.orderdemo.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        List<Predicate> predicates = new ArrayList<>();
        Predicate predicateByName = cb.like(productRoot.get("name"), "%" + name + "%");
        Predicate predicateById = cb.equal(productRoot.get("id"), id);
        Predicate predicateByQuan = cb.equal(productRoot.get("quantity"), 10);
        predicates.add(predicateByQuan);
        if (name != null && name.trim().length() > 0) {
            predicates.add(predicateByName);
        }
        if (id != null) {
            predicates.add(predicateById);
        }

//        Predicate condition = cb.or(predicateById, predicateByName);
        Predicate[] array = predicates.toArray(new Predicate[0]);

        cq.where(array);

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
        cq.having(cb.gt(cb.sum(productRoot.get("price")), 2000));
        cq.orderBy(cb.asc(productRoot.get("price")));

        CriteriaQuery<Product> select = cq.select(productRoot);
        TypedQuery<Product> query = em.createQuery(select);
        return query.getResultList();
    }

    // Bình thường trả ra ProductDto cái nào k có thì null
    public List<ProductDTO> getProductDTO() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductDTO> q = cb.createQuery(ProductDTO.class);
        Root<Product> root = q.from(Product.class);
//        q.select(
//                cb.construct(
//                        ProductDTO.class,
//                        root.get(Product_.id),
//                        root.get(Product_.name)));
        q.multiselect(root.get(Product_.id), root.get(Product_.name));

        TypedQuery<ProductDTO> query = em.createQuery(q);
        List<ProductDTO> productDTOList = query.getResultList();
        return productDTOList;
    }

    //    Kiểu select trực tiếp ra kết quả ko qua dto
    public List<Object[]> getProductDTO1() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
        Root<ProductDetail> root = cq.from(ProductDetail.class);
        root.join("product", JoinType.INNER);
        root.join("size", JoinType.INNER);
        cq.multiselect(root.get(ProductDetail_.product).get("id"),
                root.get(ProductDetail_.product).get("name"),
                root.get(ProductDetail_.quantity),
                root.get(ProductDetail_.size).get("name")
        );
        cq.where(builder.like(root.get(ProductDetail_.size).get("name"), "%X%"));
        List<Object[]> resultList = em.createQuery(cq).getResultList();
        return resultList;
    }


    @Override
    public Page<ProCatDTO> getCriteriaJoin(SearchRequest request, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ProCatDTO> criteriaQuery = criteriaBuilder.createQuery(ProCatDTO.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        root.join("category", JoinType.INNER);
//        criteriaQuery.select(criteriaBuilder.construct(ProSizeDTO.class,
//                root.get(Product_.id),
//                root.get(Product_.name),
//                root.get(Product_.category).get("id"),
//                root.get(Product_.category).get("name")));
        criteriaQuery.multiselect(root.get(Product_.id),
                root.get(Product_.name),
                root.get(Product_.category).get("id"),
                root.get(Product_.category).get("name"));
        List<Predicate> predicates = new ArrayList<>();
        Predicate predicate = criteriaBuilder.equal(root.get(Product_.category).get("id"), request.getIdCat());
        Predicate predicate1 = criteriaBuilder.like(root.get(Product_.name), request.getProName());
        if (request.getIdCat() != null) {
            predicates.add(predicate);
        }
        if (request.getProName() != null && request.getProName().trim().length() > 0) {
            predicates.add(predicate1);
        }
        Predicate[] arr = predicates.toArray(new Predicate[0]);
        criteriaQuery.where(arr);
        int totalPage = em.createQuery(criteriaQuery).getResultList().size();
        criteriaQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), root, criteriaBuilder));
        TypedQuery<ProCatDTO> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        return new PageImpl<>(typedQuery.getResultList(), pageable, totalPage);
    }
}
