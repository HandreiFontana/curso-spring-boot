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
    private static String SELECT_ALL = "SELECT * FROM customers";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer create(Customer customer) {
        jdbcTemplate.update( INSERT, new Object[]{customer.getName()} );
        return customer;
    }

    public List<Customer> list() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                return new Customer(id, name);
            }
        });
    }
}
