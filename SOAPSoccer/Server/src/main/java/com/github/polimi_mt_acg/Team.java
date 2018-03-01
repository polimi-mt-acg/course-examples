package com.github.polimi_mt_acg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Team {
    @XmlElement
    private String name;
    @XmlElement
    private List<Player> players;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

    public Team() {
        this.name = "";
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void setPlayers(List<Player> players) {
        this.players = new ArrayList<Player>(players);
    }
}
