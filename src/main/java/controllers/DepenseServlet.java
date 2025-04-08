package controllers;

import java.io.IOException;
import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import models.Depense;
import models.Service;


public class DepenseServlet extends HttpServlet {

    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            List<Depense> depenses = Depense.findAll();
            req.setAttribute("depenses", depenses);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Erreur lors du chargement des données.");
        }

        req.getRequestDispatcher("list-depense.jsp").forward(req, res);
    }

    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new ServletException("Non autorisé : vous devez être connecté.");
        }

        String idCreditStr = req.getParameter("id_credit");
        String montantStr = req.getParameter("montant");
        String dateStr = req.getParameter("date"); 

        try {
            int idCredit = Integer.parseInt(idCreditStr);
            double montant = Double.parseDouble(montantStr);

            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(dateStr);
            Date date = new Date(parsedDate.getTime());

            Depense depense = new Depense();
            depense.setIdCredit(idCredit);
            depense.setMontant(montant);
            depense.setDate(date); 

            Service service = new Service();
            service.ajoutDepense(depense);

            List<Depense> depenses = Depense.findAll();
            req.setAttribute("depenses", depenses);
            req.getRequestDispatcher("list-depense.jsp").forward(req, res);

        } catch (NumberFormatException e) {
            throw new ServletException(e.getMessage());


        } catch (Exception e) {
            throw new ServletException(e.getMessage());

        }
    }
}
