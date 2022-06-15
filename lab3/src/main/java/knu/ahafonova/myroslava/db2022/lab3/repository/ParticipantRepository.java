package knu.ahafonova.myroslava.db2022.lab3.repository;

import knu.ahafonova.myroslava.db2022.lab3.entity.Participant;
import knu.ahafonova.myroslava.db2022.lab3.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipantRepository implements IRepository<Participant> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Participant entity) {
        return jdbcTemplate.update("INSERT INTO participant (country_id, id, name, surname) VALUES(?,?,?,?)",
            entity.getCountry_id(), entity.getId(), entity.getName(), entity.getSurname());
    }

    @Override
    public Participant findById(int id) {
        try {
            Participant participant = jdbcTemplate.queryForObject("SELECT * FROM participant WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Participant.class), id);
            return participant;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Participant> findAll() {
        return jdbcTemplate.query("SELECT * from participant", BeanPropertyRowMapper.newInstance(Participant.class));
    }

    @Override
    public int update(Participant entity) {
        return jdbcTemplate.update("UPDATE ski_mapper SET country_id=?, id=?, name=?, surname=? WHERE id=?",
                entity.getCountry_id(), entity.getId(), entity.getName(), entity.getSurname(), entity.getId());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM participant WHERE id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from participant");
    }
}
