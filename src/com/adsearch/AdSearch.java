package com.adsearch;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

@WebServlet(name = "AdSearch")
public class AdSearch extends HttpServlet {

    private ServletConfig config = null;

    public AdSearch() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        super.init(config);
        ServletContext application = config.getServletContext();

        String adsDataFilePath = application.getInitParameter("adsDataFilePath");
        System.out.println("File path=" + adsDataFilePath);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        String val = request.getParameter("val");
        Memcached memcachedClient = new Memcached();
        memcachedClient.addListOfStringData(key, val);

        HashSet<String> list = memcachedClient.getListOfStringData(key);
        StringBuilder result = new StringBuilder();
        if (list != null) {
            for (String str : list) {
                result.append(str + " ");
            }
        } else {
            result.append("No data !!!");
        }

        response.getWriter().append("server at " + result.toString()).append(request.getContextPath());
    }
}
