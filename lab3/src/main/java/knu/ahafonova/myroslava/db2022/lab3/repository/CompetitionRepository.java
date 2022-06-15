package knu.ahafonova.myroslava.db2022.lab3.repository;

import knu.ahafonova.myroslava.db2022.lab3.entity.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompetitionRepository implements IRepository<Competition> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Competition entity) {
        return jdbcTemplate.update("INSERT INTO competition (day, id, name, participant_id, place_id) VALUES(?,?,?,?,?)",
                entity.getDay(), entity.getId(), entity.getName(), entity.getParticipant_id(), entity.getPlace_id());
    }

    @Override
    public Competition findById(int id) {
        try {
            Competition competition = jdbcTemplate.queryForObject("SELECT * FROM competition WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Competition.class), id);
            return competition;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Competition> findAll() {
        return jdbcTemplate.query("SELECT * from discount", BeanPropertyRowMapper.newInstance(Competition.class));
    }

    @Override
    public int update(Competition entity) {
        return jdbcTemplate.update("UPDATE competition SET day=?, id=?, name=?, participant_id=?, place_id=? WHERE id=?",
                entity.getPlace_id(), entity.getDay(), entity.getName(), entity.getId());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM competition WHERE id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from competition");
    }
}
