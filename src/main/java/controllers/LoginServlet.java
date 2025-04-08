package controllers;

import java.io.*;
import java.sql.*; 
import java.util.List;

import models.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        String login = req.getParameter("login");
        String mdp = req.getParameter("mdp");
        Connection conn=null;
        
        if(login != null && !login.isEmpty() && mdp != null && !mdp.isEmpty()) {
            try {
                conn=MyCoSQL.GetConnection();
                User u=new User();
                u.setLogin(login);
                u.setMdp(mdp);
                boolean isAuthenticated = u.login();
                
                if(isAuthenticated) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", login);
                
                    res.sendRedirect("./");
                } else {
                    req.setAttribute("error", "Identifiants incorrects");
                    RequestDispatcher rd = req.getRequestDispatcher("/form-login.jsp");
                    rd.forward(req, res);
                }
            } catch(Exception e) {
                //req.setAttribute("error", "Erreur lors de l'authentification");
                //RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                //rd.forward(req, res);
                throw new ServletException(e.getMessage());
            }
        } else {
            req.setAttribute("error", "Veuillez remplir tous les champs");
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(req, res);
        }
    }


}