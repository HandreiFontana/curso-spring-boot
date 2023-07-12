package br.com.handrei.domain.repository;

import br.com.handrei.domain.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class Customers {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Customer create(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Transactional
    public Customer update(Customer customer) {
        entityManager.merge(customer);
        return customer;
    }

    @Transactional(readOnly = true)
    public List<Customer> list() {
        return entityManager
                .createQuery("FROM Customer", Customer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<Customer> list(String search) {
        String jpql = " SELECT c FROM Customer c WHERE c.name like :name ";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        query.setParameter("name", "%" + search + "%");
        return query.getResultList();
    }

    @Transactional
    public void delete(Customer customer) {
        customer = entityManager.contains(customer) ? customer : entityManager.merge(customer);
        entityManager.remove(customer);
    }

    @Transactional
    public void delete(Integer id) {
        Customer customer = entityManager.find(Customer.class, id);
        delete(customer);
    }
}
