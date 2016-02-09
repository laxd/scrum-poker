package uk.laxd.poker.dto;

/**
 * Created by lawrence on 08/02/16.
 */
public class VoteDto {
    private String userName;
    private String vote;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
