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
        <a href="credit">Liste Credits</a>
        <a href="form-credit">Ajout Credit</a>
        <a href="depense">Liste Depenses</a>
        <a href="form-login">Login</a>
        <h2>Ajouter depense</h2>
        <form action="depense" method="post">
            <label for="id_credit">Credit</label>
            <select name="id_credit" id="">
                <% List<Credit> depts=(List<Credit> )request.getAttribute("credits"); 
                    for (Credit d : depts) {%>
                        <option value="<%= d.getId() %>"><%= d.getLibelle()%></option>
                <% } %>
            </select>
            <input type="number" name="montant" id="" placeholder="Montant">
            <input type="date" name="date" id="" placeholder="Date">
        <input type="submit" value="Ajouter">
    </form>
</body>
</html>