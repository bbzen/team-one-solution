package ru.practikum.teamonesolution.models;

import java.util.ArrayList;

public class Team {
    private String name;
    private String gitHubUrl;
    private ArrayList<Participant> memberList;

    public Team(String name, String gitHubUrl, ArrayList<Participant> memberList) {
        this.name = name;
        this.gitHubUrl = gitHubUrl;
        this.memberList = memberList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGitHubUrl() {
        return gitHubUrl;
    }

    public void setGitHubUrl(String gitHubUrl) {
        this.gitHubUrl = gitHubUrl;
    }

    public ArrayList<Participant> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<Participant> memberList) {
        this.memberList = memberList;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", gitHubUrl='" + gitHubUrl + '\'' +
                ", memberList=" + memberList +
                '}';
    }
}
