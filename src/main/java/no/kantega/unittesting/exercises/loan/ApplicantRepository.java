package no.kantega.unittesting.exercises.loan;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ApplicantRepository {

    private DataSource dataSource;

    public ApplicantRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Applicant insert(final Applicant applicant) {
        final String sql = "insert into APPLICANT (SSN, EDUCATION, EMPLOYED_SINCE) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        new JdbcTemplate(dataSource).update((Connection con) -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, applicant.getSsn());
            statement.setString(2, applicant.getEducation().toString());
            statement.setObject(3, applicant.getEmployedSince());
            return statement;
        }, keyHolder);

        Long key = (Long) keyHolder.getKey();
        applicant.setId(key);
        return applicant;
    }

    public Applicant find(Long id) {
        List<Applicant> result = new JdbcTemplate(dataSource).query("select ID, SSN, EDUCATION, EMPLOYED_SINCE from APPLICANT where ID = ?", new Object[]{id}, (ResultSet rs, int rowNum) -> {
            Applicant applicant = new Applicant();
            applicant.setId(rs.getLong("ID"));
            applicant.setSsn(rs.getString("SSN"));
            applicant.setEducation(Applicant.Education.valueOf(rs.getString("EDUCATION")));

            Integer employedSince = rs.getInt("EMPLOYED_SINCE");
            if (!rs.wasNull()) {
                applicant.setEmployedSince(employedSince);
            }

            return applicant;
        });
        if (result.size() == 0) {
            return null;
        } else if (result.size() > 1) {
            throw new RuntimeException("Unexpected duplicate");
        } else {
            return result.get(0);
        }
    }

}
