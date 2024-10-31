package com.sap.cloud.sdk.tutorial;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRepository;

 
@WebServlet("/ConnectRFC/*")

public class ConnectRFC extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter responseWriter = response.getWriter();

        try {

            // access the RFC Destination "Test"
            JCoDestination destination = JCoDestinationManager.getDestination("S4H_RFC");
            JCoRepository repo = destination.getRepository();
            JCoFunction zfmdemo01 = repo.getFunction("ZFMDEMO01");      
            
            zfmdemo01.execute(destination);
            JCoParameterList exports = zfmdemo01.getExportParameterList();
            String resptext = exports.getString("P_CONNID");

            responseWriter.println("spfli.connid : "+resptext);
            
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}


