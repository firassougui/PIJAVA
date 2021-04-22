/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import utils.DataSource;
import Entities.commantaire;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import pidevv.AffichageController;

/**
 *
 * @author M'Amine
 */
public class commentaireService {

    private final Connection cnx;
    private static commentaireService instance;
    ObservableList<commantaire> listPub = FXCollections.observableArrayList();

    public commentaireService() {
        cnx = DataSource.getInstance().getCnx();
    }

    public static commentaireService getInstance() {
        if (instance == null) {
            instance = new commentaireService();
        }
        return instance;

    }

    public void AjouterCommentaire(commantaire p) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement(
                    "INSERT INTO commantaire (com_pub, contenu) VALUES ( ? , ? )");
            preparedStatement.setInt(1, p.getPublication_id());
            preparedStatement.setString(2, p.getDescription());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Success ajout commentaire");
        } catch (SQLException e) {
            System.out.println("Erreur d'ajout commentaire : " + e.getMessage());
        }
    }

    public boolean Add_nbr_like(int id, int vus) {

        int test = 0;
        boolean check;
        try {
            PreparedStatement pst = cnx.prepareStatement("update commantaire set vus = ?  where id=" + id);
            pst.setInt(1, vus);

        } catch (SQLException ex) {
            Logger.getLogger(commentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (test == 0) {
            check = false;
        } else {
            check = true;
        }
        return check;

    }

    public List<commantaire> getAllByPubId(int id) {
        try {
            PreparedStatement preparedStatement
                    = cnx.prepareStatement("SELECT * FROM commantaire WHERE com_pub = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listPub.add(new commantaire(
                        resultSet.getInt("id"),
                        resultSet.getInt("com_pub"),
                        resultSet.getString("contenu")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage (tout) commentaire : " + e.getMessage());
        }
        return listPub;
    }

    public void incrementerjaime(int id) {
        try {
            PreparedStatement ste = cnx.prepareStatement("update commantaire set vus=vus+1  WHERE id='" + id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(commentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<commantaire> findpubBytitre(String titre) {
        listPub = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(
                    "SELECT * FROM commantaire WHERE titre LIKE ?");
            preparedStatement.setString(1, titre + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listPub.add(new commantaire(
                        resultSet.getInt("id"),
                        resultSet.getInt("com_pub"),
                        resultSet.getString("contenu")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur recherche commentaire : " + e.getMessage());
        }
        return listPub;
    }

    public void SupprimerCommentaire(commantaire commentaire) {

        // ... user chose CANCEL or closed the dialog
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement("DELETE FROM `commantaire` WHERE `id`=?");
            preparedStatement.setInt(1, commentaire.getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Erreur de suppresion commantaire : " + e.getMessage());
        }

    }

    public void ModiferCommentaire(commantaire u) {
        PreparedStatement preparedStatement;
        System.out.println("aaaaaaa");
        try {
            preparedStatement = cnx.prepareStatement(
                    "UPDATE commantaire "
                    + "SET com_pub = ?, contenu = ? "
                    + "WHERE id = ?");
            preparedStatement.setInt(1, u.getPublication_id());
            preparedStatement.setString(2, u.getDescription());
            preparedStatement.setInt(3, u.getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Success modification commentaire");
        } catch (SQLException e) {
            System.out.println("Erreur de modification commentaire : " + e.getMessage());
        }
    }

}
