package no.kantega.unittesting.exercises.loan;

public class Applicant {

    public enum Education { PRIMARY_SCHOOL, SECONDARY_SCHOOL, UNIVERSITY_DEGREE }

    private Long id;
    private String ssn;
    private Education education;
    private Integer employedSince;

    public Applicant() {
    }

    public Applicant(String ssn, Education education, Integer employedSince) {
        this.ssn = ssn;
        this.education = education;
        this.employedSince = employedSince;
    }

    public String getSsn() {
        return ssn;
    }

    public Education getEducation() {
        return education;
    }

    public Integer getEmployedSince() {
        return employedSince;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public void setEmployedSince(Integer employedSince) {
        this.employedSince = employedSince;
    }

}
