package com.github.polimi_mt_acg;

import javax.jws.WebService;
import java.util.*;

@WebService(endpointInterface = "com.github.polimi_mt_acg.SoccerTeams")
public class SoccerTeamsImpl implements SoccerTeams {
    private static Map<String, Team> teams = new HashMap<>();

    static {
        Team _1 = new Team("Team 1",
                Arrays.asList(new Player("Player A", 1),
                        new Player("Player B", 2),
                        new Player("Player C", 3)));
        // Team 2
        Team _2 = new Team("Team 2",
                Arrays.asList(new Player("Player D", 99),
                        new Player("Player E", 98),
                        new Player("Player F", 97)));

        teams.put("Team 1", _1);
        teams.put("Team 2", _2);
    }

    public SoccerTeamsImpl() {
    }

    public void addTeam(String name, Team team) {
        teams.put(name, team);
    }

    @Override
    public Team getTeam(String name) {
        Team result = teams.get(name);
        if (result == null) {
            throw new RuntimeException("Team " + name + " not found!");
        } else return result;
    }

    @Override
    public List<String> getTeams() {
        List<String> res = new ArrayList<>(teams.keySet());
        res.sort(String::compareTo);
        return res;
    }
}
