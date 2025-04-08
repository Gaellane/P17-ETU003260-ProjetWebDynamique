package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import models.Credit;

public class FormDepenseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            List<Credit> credits = Credit.findAll();

            req.setAttribute("credits", credits);
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }

        RequestDispatcher rd = req.getRequestDispatcher("/form-depense.jsp");
        rd.forward(req, res);
    }
}
