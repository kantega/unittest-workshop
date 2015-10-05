package no.kantega.unittesting.exercises.confirmation;


import java.util.Date;

public class Document {

    private Long id;
    private String title;
    private Date distributionDate;
    private DistributionGroup distributionGroup;

    public Document(Long id) {
        this.id = id;
    }

    public Document(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setDistributionDate(Date distributionDate) {
        this.distributionDate = distributionDate;
    }

    public void setDistributionGroup(DistributionGroup distributionGroup) {
        this.distributionGroup = distributionGroup;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDistributionDate() {
        return distributionDate;
    }

    public DistributionGroup getDistributionGroup() {
        return distributionGroup;
    }
}
