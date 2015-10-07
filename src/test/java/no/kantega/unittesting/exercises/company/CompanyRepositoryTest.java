package no.kantega.unittesting.exercises.company;

import no.kantega.unittesting.utilities.TestUtilities;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.fail;

public class CompanyRepositoryTest {

    private BasicDataSource dataSource;
    private CompanyRepository repository;

    @Before
    public void setUp() throws Exception {
        dataSource = TestUtilities.createTestDateSource();
        Connection connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("CREATE TABLE COMPANY ("
                        + "ID identity not null primary key,"
                        + "NAME varchar(255))"
        );
        connection.close();
        repository = new CompanyRepository(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        Connection connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("DROP TABLE COMPANY");
        connection.close();
        dataSource.close();
    }

    @Test
    public void insertCompanyWithApplicationProvidedIdShallFail() {

        //given
        Company company = new Company(100L, "some name");

        try {

            //when
            repository.insert(company);
            fail();

        } catch (Exception e) {
            //expected
        }

    }

}
