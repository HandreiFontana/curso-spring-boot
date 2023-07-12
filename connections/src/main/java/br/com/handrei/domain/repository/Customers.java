package br.com.handrei.domain.repository;

import br.com.handrei.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Customers {

    private static String INSERT = "INSERT INTO customers (name) VALUES (?)";
    private static String UPDATE = "UPDATE customers SET name = ? WHERE id = ?";
    private static String SELECT_ALL = "SELECT * FROM customers";
    private static String DELETE = "DELETE FROM customers WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer create(Customer customer) {
        jdbcTemplate.update( INSERT, new Object[]{customer.getName()} );
        return customer;
    }

    public Customer update(Customer customer) {
        jdbcTemplate.update( UPDATE, new Object[]{customer.getName(), customer.getId()} );
        return customer;
    }

    public List<Customer> list() {
        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }

    public List<Customer> list(String search) {
        return jdbcTemplate.query(
                SELECT_ALL.concat(" WHERE name LIKE ?"),
                new Object[]{"%" + search + "%"},
                getRowMapper()
        );
    }

    private static RowMapper<Customer> getRowMapper() {
        return new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                return new Customer(id, name);
            }
        };
    }

    public void delete(Customer customer) {
        delete(customer.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update( DELETE, new Object[]{id} );
    }
}
