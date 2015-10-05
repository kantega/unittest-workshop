package no.kantega.unittesting.exercises.confirmation;

public interface ConfirmationRepository {

    Confirmation find(Employee employee, Document document);

    Confirmation insert(Confirmation confirmation);
}
