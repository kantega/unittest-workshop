package no.kantega.unittesting.exercises.student;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class StudentRepository {

    private DataSource dataSource;

    public StudentRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Student insert(final Student student) {
        final String sql = "insert into STUDENT (USER_NAME, SUR_NAME, GIVEN_NAME) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        new JdbcTemplate(dataSource).update((Connection con) -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getUserName());
            statement.setString(2, student.getSurName());
            statement.setString(3, student.getGivenName());
            return statement;
        }, keyHolder);

        Long key = (Long) keyHolder.getKey();
        student.setId(key);
        return student;
    }

    public void update(Student student) {
        int result = new JdbcTemplate(dataSource).update("update STUDENT set USER_NAME = ?, SUR_NAME = ?, GIVEN_NAME = ? where ID = ?", student.getUserName(), student.getSurName(), student.getGivenName(), student.getId());
        if (result != 1) {
            throw new RuntimeException("Could not update employee");
        }
    }

    public Student find(Long id) {
        List<Student> result = new JdbcTemplate(dataSource).query("select ID, USER_NAME, SUR_NAME, GIVEN_NAME from STUDENT where ID = ?", new Object[]{id}, (ResultSet rs, int rowNum) -> {
            Student student = new Student();
            student.setId(rs.getLong("ID"));
            student.setUserName(rs.getString("USER_NAME"));
            student.setSurName(rs.getString("SUR_NAME"));
            student.setGivenName(rs.getString("GIVEN_NAME"));
            return student;
        });
        if (result.size() == 0) {
            return null;
        } else if (result.size() > 1) {
            throw new RuntimeException("Unexpected duplicate");
        } else {
            return result.get(0);
        }
    }

    public void delete(Long id) {
        new JdbcTemplate(dataSource).update("delete from STUDENT where ID = ?", id);
    }

    public long count() {
        return new JdbcTemplate(dataSource).queryForObject("select count(*) from STUDENT", Long.class);
    }

}
