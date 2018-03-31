package com.adsearch;

import com.adsearch.dao.MemcachedManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@WebServlet(name = "AdSearchServlet")
public class AdSearchServlet extends HttpServlet {

    private ServletConfig config = null;
    private AdEngine adEngine = null;

    public AdSearchServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        super.init(config);
        ServletContext application = config.getServletContext();

        String adsDataFilePath = application.getInitParameter("adsDataFilePath");
        String campaignDataFilePath = application.getInitParameter("campaignDataFilePath");

        String invertedIndexServer = application.getInitParameter("invertedIndexServer");
        int invertedIndexServerPort = Integer.parseInt(application.getInitParameter("invertedIndexServerPort"));
        String mysqlDatabaseHost = application.getInitParameter("mysqlDatabaseHost");
        String mysqlDatabaseName = application.getInitParameter("mysqlDatabaseName");
        String mysqlUser = application.getInitParameter("mysqlUser");
        String mysqlPassword = application.getInitParameter("mysqlPassword");

        this.adEngine = new AdEngine(adsDataFilePath, campaignDataFilePath, invertedIndexServer,
                invertedIndexServerPort, mysqlDatabaseHost, mysqlDatabaseName, mysqlUser, mysqlPassword);
        System.out.println("The ad engine is up.");

        this.adEngine.init();
        System.out.println("The ad engine initialized.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String key = request.getParameter("key");
//        String val = request.getParameter("val");
//        MemcachedManager memcachedClient = new MemcachedManager();
//        memcachedClient.addListOfStringData(key, val);
//
//        HashSet<String> list = memcachedClient.getListOfStringData(key);
//        StringBuilder result = new StringBuilder();
//        if (list != null) {
//            for (String str : list) {
//                result.append(str + " ");
//            }
//        } else {
//            result.append("No data !!!");
//        }
//
//        response.getWriter().append("server at " + result.toString()).append(request.getContextPath());
    }
}
