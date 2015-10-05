package no.kantega.unittesting.exercises.confirmation;

public class DistributionGroup {

    private String code;
    private String description;

    public DistributionGroup() {
    }

    public DistributionGroup(String code, String description) {
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
