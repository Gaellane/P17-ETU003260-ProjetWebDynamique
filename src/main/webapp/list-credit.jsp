<%@ page import="java.util.List"%>
<%@ page import="models.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <a href="form-credit">Ajout Credit</a>
    <a href="form-depense">Ajout Depense</a>
    <a href="depense">Liste Depenses</a>
    <a href="form-login">Login</a>
    <h2>Liste des Credits</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Libelle</th>
            <th>Montant</th>
        </tr>
        <% List<Credit> depts=(List<Credit> )request.getAttribute("credits"); 
            for (Credit d : depts) {%>
                <tr>
                    <td><%= d.getId()%></td>
                    <td><%= d.getLibelle()%></td>
                    <td><%= d.getMontant()%></td>
                </tr>
            <% } %>
    </table>
</body>
</html>