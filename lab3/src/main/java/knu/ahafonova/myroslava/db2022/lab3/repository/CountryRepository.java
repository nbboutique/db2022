package knu.ahafonova.myroslava.db2022.lab3.repository;

import knu.ahafonova.myroslava.db2022.lab3.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryRepository implements IRepository<Country> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int save(Country entity) {
        return jdbcTemplate.update("INSERT INTO users (id, name) VALUES(?,?)", new Object[]{entity.getId(), entity.getName()});
    }

    @Override
    public Country findById(int id) {
        try {
            Country country = jdbcTemplate.queryForObject("SELECT * FROM countries WHERE id=?", BeanPropertyRowMapper.newInstance(Country.class), id);
            return country;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query("SELECT * from countries", BeanPropertyRowMapper.newInstance(Country.class));
    }

    @Override
    public int update(Country entity) {
        return jdbcTemplate.update("UPDATE countries SET id=?, name=? WHERE id=?", new Object[]{entity.getId(), entity.getName()});
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM countries WHERE id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from countries");
    }
}
