package no.kantega.unittesting.exercises.company;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;

public class CompanyRepository {

    private DataSource dataSource;

    public CompanyRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Company insert(final Company company) {
        final String sql = "insert into COMPANY (NAME) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        new JdbcTemplate(dataSource).update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, company.getName());
                return statement;
            }
        }, keyHolder);

        Long key = (Long) keyHolder.getKey();
        company.setId(key);
        return company;
    }

}
