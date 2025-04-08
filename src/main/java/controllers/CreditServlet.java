package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import models.Credit;


public class CreditServlet extends HttpServlet {

    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        String libelle = req.getParameter("libelle");
        String montantStr = req.getParameter("montant");

        try {
            double montant = Double.parseDouble(montantStr);
            Credit credit = new Credit(libelle, montant);
            credit.save();  

            List<Credit> credits = Credit.findAll(); 

            req.setAttribute("credits", credits);
            req.getRequestDispatcher("list-credit.jsp").forward(req, res);

        } catch (NumberFormatException e) {
            throw new ServletException(e.getMessage());
            

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new ServletException("Non autorisé : vous devez être connecté.");
        }

        try {
            List<Credit> credits = Credit.findAll();  
            req.setAttribute("credits", credits);
        } catch (Exception e) {
            //e.printStackTrace();
            //req.setAttribute("error", "Erreur lors du chargement des crédits.");
            throw new ServletException(e.getMessage());

        }

        req.getRequestDispatcher("list-credit.jsp").forward(req, res);
    }
}
