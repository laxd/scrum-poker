package uk.laxd.poker.dto;

/**
 * Created by lawrence on 08/02/16.
 */
public class VoteDto {
    private String name;
    private String vote;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
