package models;

import java.sql.Connection;


public class Service {

    public void ajoutDepense(Depense depense) throws Exception {
        Connection conn=null;
        try {
            conn=MyCoSQL.GetConnection();
            conn.setAutoCommit(false);
            Credit credit = new Credit();
            credit.findById(depense.getIdCredit());
            
            if (credit.getId() == 0) {
                throw new Exception("Crédit introuvable pour l'ID : " + depense.getIdCredit());
            }

            if (depense.getMontant() > credit.getMontant()) {
                throw new Exception("Montant de la dépense supérieur au montant du crédit");
            }
            
            double newMontant = credit.getMontant() - depense.getMontant();
            credit.setMontant(newMontant);
            credit.update(conn);
            
            depense.save(conn);
            conn.commit();
        } catch(Exception e) {
            if(conn!=null) {
                conn.rollback();
            }
        } finally {
            conn.close();
        }

    }
}
