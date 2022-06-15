package knu.ahafonova.myroslava.db2022.lab3.repository;

import knu.ahafonova.myroslava.db2022.lab3.entity.Place;
import knu.ahafonova.myroslava.db2022.lab3.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceRepository implements IRepository<Place> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Place entity) {
        return jdbcTemplate.update("INSERT INTO place (country_id,id,name) VALUES(?,?,?)",
                entity.getCountry_id(), entity.getId(), entity.getName());
    }

    @Override
    public Place findById(int id) {
        try {
            Place place = jdbcTemplate.queryForObject("SELECT * FROM place WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Place.class), id);
            return place;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Place> findAll() {
        return jdbcTemplate.query("SELECT * from place", BeanPropertyRowMapper.newInstance(Place.class));
    }

    @Override
    public int update(Place entity) {
        return jdbcTemplate.update("UPDATE place SET country_id=?,id=?,name=? WHERE id=?",
                entity.getCountry_id(), entity.getId(), entity.getName());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM place WHERE id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from place");
    }
}
