package no.kantega.unittesting.exercises.confirmation;

public class Role {

    private String code;
    private String description;

    public Role(String code) {
        this.code = code;
    }

    public Role(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
