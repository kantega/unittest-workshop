package no.kantega.unittesting.exercises.company;

public class Company {

    private Long id;
    private String name;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public Company(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
