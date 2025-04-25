package main.java;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/scrape")
public class ScraperServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getParameter("url");
        String showTitle = request.getParameter("title");
        String showLinks = request.getParameter("links");

        HttpSession session = request.getSession();
        Integer visitCount = (Integer) session.getAttribute("visitCount");
        if (visitCount == null) visitCount = 0;
        session.setAttribute("visitCount", visitCount + 1);

        WebScraper.ScrapedData scrapedData = WebScraper.scrape(url);

        request.setAttribute("scrapedData", scrapedData);
        request.setAttribute("showTitle", showTitle != null);
        request.setAttribute("showLinks", showLinks != null);
        request.setAttribute("visitCount", visitCount + 1);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}