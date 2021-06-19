package com.compassouol.productms.repository;

import com.compassouol.productms.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    public List<Product> findProductsByFilter(String q, Double min_price, Double max_price) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        Root<Product> productRoot = criteriaQuery.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if (min_price != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(productRoot.get("price"), min_price));
        }
        if (max_price != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(productRoot.get("price"), max_price));
        }
        if (q != null) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(productRoot.get("name")), "%" + q + "%"),
                            (criteriaBuilder.like(
                                    criteriaBuilder.lower(productRoot.get("description")), "%" + q + "%"))
                    )
            );
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();

    }

}
