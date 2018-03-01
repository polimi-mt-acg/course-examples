package com.github.polimi_mt_acg;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

// We leave properties 'name' and 'serviceName' unqualified  which are
// defaulted to SoccerTeams and SoccerTeamsService respectively
@WebService(targetNamespace = "http://github.com/polimi_mt_acg")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface SoccerTeams {
    @WebMethod(operationName = "getTeamByName")
    // Leaving the name unqualified which is defaulted to 'return' for RPC and
    // Document/wrapped while to <method_name> + 'Response' for Document/Bare
    @WebResult()
    public Team getTeam(@WebParam(name = "teamName") String name);

    @WebMethod(operationName = "getTeamsName")
    @WebResult()
    public List<String> getTeams();
}
