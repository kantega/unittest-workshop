package no.kantega.unittesting.exercises.loan;

import no.kantega.unittesting.utilities.TestUtilities;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicantRepositoryTest {

    private BasicDataSource dataSource;
    private ApplicantRepository repository;

    @Before
    public void setUp() throws Exception {
        dataSource = TestUtilities.createTestDateSource();
        Connection connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("CREATE TABLE APPLICANT ("
                        + "ID identity not null primary key,"
                        + "SSN varchar(255) unique,"
                        + "EDUCATION varchar(255),"
                        + "EMPLOYED_SINCE int)"
        );
        connection.close();
        repository = new ApplicantRepository(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        Connection connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("DROP TABLE APPLICANT");
        connection.close();
        dataSource.close();
    }

    @Test
    public void shallFindExistingApplicantInDatabase() {

        //given
        Applicant existing = repository.insert(new Applicant("01017012345", Applicant.Education.PRIMARY_SCHOOL, null));

        //When
        Applicant found = repository.find(existing.getId());

        //Then
        assertEqualProperties(existing, found);
    }

    private void assertEqualProperties(Applicant expected, Applicant actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getSsn(), actual.getSsn());
        assertEquals(expected.getEducation(), actual.getEducation());
        assertEquals(expected.getEmployedSince(), actual.getEmployedSince());
    }


}
