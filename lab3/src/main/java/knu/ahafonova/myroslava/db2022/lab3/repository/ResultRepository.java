package knu.ahafonova.myroslava.db2022.lab3.repository;

import knu.ahafonova.myroslava.db2022.lab3.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultRepository implements IRepository<Result> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Result entity) {
        return jdbcTemplate.update("INSERT INTO result (competition_id, participant_id, result) VALUES(?,?,?)",
                entity.getCompetition_id(), entity.getParticipant_id(), entity.getResult());
    }

    @Override
    public Result findById(int id) {
        try {
            Result result = jdbcTemplate.queryForObject("SELECT * FROM result WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Result.class), id);
            return result;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Result> findAll() {
        return jdbcTemplate.query("SELECT * from result", BeanPropertyRowMapper.newInstance(Result.class));
    }

    @Override
    public int update(Result entity) {
        return jdbcTemplate.update("UPDATE result SET competition_id=?, participant_id=?, result=? WHERE id=?",
                entity.getCompetition_id(), entity.getParticipant_id(), entity.getResult());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM result WHERE id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from result");
    }
}
