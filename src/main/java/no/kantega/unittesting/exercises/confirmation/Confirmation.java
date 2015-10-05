package no.kantega.unittesting.exercises.confirmation;

import java.util.Date;

public class Confirmation {

    private Long id;
    private Document confirmedDocument;
    private Employee confirmedBy;
    private Date confirmationDate;

    public Confirmation(Document confirmedDocument, Employee confirmedBy) {
        this.confirmedDocument = confirmedDocument;
        this.confirmedBy = confirmedBy;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public Long getId() {
        return id;
    }

    public Document getConfirmedDocument() {
        return confirmedDocument;
    }

    public Employee getConfirmedBy() {
        return confirmedBy;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public boolean isPending() {
        return confirmationDate == null;
    }
}
