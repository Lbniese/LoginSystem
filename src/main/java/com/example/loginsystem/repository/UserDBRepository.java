package com.example.loginsystem.repository;

import com.example.loginsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDBRepository implements IUserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    SqlRowSet sqlRowSet;

    @Override
    public boolean create(User u) {
        String sql ="INSERT INTO user (email, password) VALUES(?, ?)";
        jdbcTemplate.update(sql, u.getEmail(), u.getPassword());
        return true;
    }

    @Override
    public User read(String email) {
        String sql = "SELECT * FROM user WHERE email ='" + email + "'";


        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        while (sqlRowSet.next()) {
            return new User(sqlRowSet.getString("email"), sqlRowSet.getString("password"));
        }

        return null;

        /*
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper, email);
        return user;
        */
    }

    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * from user";


        sqlRowSet = jdbcTemplate.queryForRowSet(sql);


        while (sqlRowSet.next()) {
            users.add(new User(sqlRowSet.getString("email"), sqlRowSet.getString("password")));
        }
        return users;

        /*
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);

        */
    }
}
