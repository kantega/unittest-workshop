package no.kantega.unittesting.exercises.confirmation;

public interface EmployeeRepository {
    Employee insert(Employee employee);

    void update(Employee employee);

    Employee find(Long id);

    void delete(Long id);

    long count();
}
