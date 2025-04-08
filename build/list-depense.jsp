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
    <a href="form-credit">Ajout Credits</a>
    <a href="credit">Liste Credits</a>
    <a href="form-depense">Ajout Depense</a>
    <a href="form-login">Login</a>
    <h2>Liste des Credits</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Credit</th>
            <th>Montant</th>
        </tr>
        <% List<Depense> depts=(List<Depense> )request.getAttribute("depenses"); 
            for (Depense d : depts) {%>
                <tr>
                    <td><%= d.getId()%></td>
                    <td><%= d.getObjCredit().getLibelle()%></td>
                    <td><%= d.getMontant()%></td>
                </tr>
            <% } %>
    </table>
</body>
</html>