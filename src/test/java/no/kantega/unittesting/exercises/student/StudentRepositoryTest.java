package no.kantega.unittesting.exercises.student;

import no.kantega.unittesting.utilities.TestUtilities;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.*;

import java.sql.Connection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class StudentRepositoryTest {

    private BasicDataSource dataSource;
    private StudentRepository repository;

    @Before
    public void setUp() throws Exception {
        dataSource = TestUtilities.createTestDateSource();
        Connection connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("CREATE TABLE STUDENT ("
                        + "ID identity not null primary key,"
                        + "USER_NAME varchar(255) unique,"
                        + "SUR_NAME varchar(255),"
                        + "GIVEN_NAME varchar(255))"
        );
        connection.close();
        repository = new StudentRepository(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        Connection connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("DROP TABLE STUDENT");
        connection.close();
        dataSource.close();
    }

    @Test
    public void insertShallIncreaseCountByOne() {

        //given
        long oldCount = repository.count();

        //when
        repository.insert(newInstance());

        //then
        assertThat(repository.count(), equalTo(oldCount + 1));

    }

    @Test
    public void insertShallReturnStudentWithId() {

        //when
        Student inserted = repository.insert(newInstance());

        //then
        assertThat(inserted.getId(), is(notNullValue()));
    }

    @Test
    public void insertShallPersistAllProperties() {

        //when
        Student inserted = repository.insert(newInstance());

        //then
        Student found = repository.find(inserted.getId());
        assertEqualProperties(inserted, found);
    }

    @Test(expected = Exception.class)
    public void updateNewStudentShallFail() {

        //when
        repository.update(newInstance());
    }

    @Test(expected = Exception.class)
    public void updateNonPersistedStudentShallFail() {

        //given
        Student fakeInstance = newInstance();
        fakeInstance.setId(100L);

        //when
        repository.update(fakeInstance);
    }

    @Test
    public void updateShallNotChangeCount() {

        //given
        Student existing = repository.insert(newInstance());
        long oldCount = repository.count();

        //when
        repository.update(modifiedInstance(existing));

        //then
        assertThat(repository.count(), equalTo(oldCount));

    }

    @Test
    public void updateShallPersistAllProperties() {

        //given
        Student exsisting = repository.insert(newInstance());
        Student modified = modifiedInstance(exsisting);

        //when
        repository.update(modified);

        //then
        Student updated = repository.find(exsisting.getId());
        assertEqualProperties(updated, modified);
    }


    @Test
    public void findShallReturnStudentWithCorrectProperties() {

        //given
        Student existing = repository.insert(newInstance());

        //when
        Student found = repository.find(existing.getId());

        //then
        assertEqualProperties(found, existing);

    }

    @Test
    public void findShallReturnNullWhenStudentIsNotFound() {

        //given
        Long nonExistingId = 100L;

        //when
        Student found = repository.find(nonExistingId);

        //then
        assertThat(found, is(nullValue()));

    }

    @Test
    public void deleteShallDecreaseCountByOne() {

        //given
        Student existing = repository.insert(newInstance());
        long oldCount = repository.count();

        //when
        repository.delete(existing.getId());

        //then
        assertThat(repository.count(), equalTo(oldCount - 1));

    }

    @Test
    public void deleteShallRemoveStudent() {

        //given
        Student existing = repository.insert(newInstance());

        //when
        repository.delete(existing.getId());

        //then
        assertThat(repository.find(existing.getId()), is(nullValue()));
    }

    private Student newInstance() {
        return new Student("oladun", "Ola", "Dunk");
    }

    private Student modifiedInstance(Student student) {
        student.setUserName("oladuu");
        student.setGivenName("Olav");
        student.setSurName("Duun");
        return student;
    }

    private void assertEqualProperties(Student student1, Student student2) {

        assertEquals(student1.getId(), student2.getId());
        assertEquals(student1.getUserName(), student2.getUserName());
        assertEquals(student1.getGivenName(), student2.getGivenName());
        assertEquals(student1.getSurName(), student2.getSurName());

    }

}
