/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Seminar;
import domain.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCManageSeminar {

    public JDBCManageSeminar() {
    }
    
    public void saveSeminar(Seminar aSeminar) {
        String statement = "insert into SEMINAR(TOPIC, TITLE, ABSTRACT, LOCATION, MODALITY, DAY_DATE, DISPLAYNAME) values(?,?,?,?,?,?,?)";

        try (
            // get connection to database
            Connection dbCon = DbConnection.getConnection(DbConnection.getDefaultConnectionUri());
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);) {
                  
            
            stmt.setString(1, aSeminar.getTopic());
            stmt.setString(2, aSeminar.getTitle());
            stmt.setString(3, aSeminar.getAbstracts());
            stmt.setString(4, aSeminar.getLocation());
            stmt.setString(5, aSeminar.getModality());
            stmt.setString(6, aSeminar.getDate());
            stmt.setString(7, aSeminar.getDisplayName());

            stmt.executeUpdate(); 

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
     
        } catch (SQLException ex) { 
            throw new DAOException(ex.getMessage(), ex);
        }
    }
        
    public void deleteSeminar(Seminar sem) {
        String sql = "delete from SEMINAR";
        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(DbConnection.getDefaultConnectionUri());
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            //stmt.setInt(1, sem.getSeminarID());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }


    public Seminar getSeminarById(int idDefined) {
        String statement = "select * from SEMINAR where SEMINARID = ?";

        try (
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(DbConnection.getDefaultConnectionUri());
                
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(statement);) {
            
            
            stmt.setInt(1, idDefined);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Integer seminarID = rs.getInt("SEMINARID");
                String topic = rs.getString("TOPIC");
                String title = rs.getString("TITLE");
                String abstracts = rs.getString("ABSTRACT");
                String location = rs.getString("LOCATION");
                String modality = rs.getString("MODALITY");
                String date = rs.getString("DAY_DATE");
                String displayName = rs.getString("DISPLAYNAME");

                return new Seminar(seminarID, topic, title, abstracts, location, modality, date, displayName);

            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }
    
   
    public Seminar getSeminarThroughTitle(String titles) {
        String statement = "select * from SEMINAR where TITLE = ?";
        
        try (
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(DbConnection.getDefaultConnectionUri());
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(statement);) {
            stmt.setString(1, titles);

            // execute the query
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Integer seminarID = rs.getInt("SEMINARID");
                String topic = rs.getString("TOPIC");
                String title = rs.getString("TITLE");
                String abstracts = rs.getString("ABSTRACT");
                String location = rs.getString("LOCATION");
                String modality = rs.getString("MODALITY");
                String date = rs.getString("DAY_DATE");
                String displayName = rs.getString("DISPLAYNAME");

                return new Seminar(seminarID, topic, title, abstracts, location, modality, date, displayName);

            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }
    public Collection<Seminar> getSeminars() {
        String statement = "select * from SEMINAR";

        try (
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(DbConnection.getDefaultConnectionUri());
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(statement);) {

            ResultSet rs = stmt.executeQuery();

            Collection<Seminar> seminars = new ArrayList<>();

            while (rs.next()) {

                Integer seminarID = rs.getInt("SEMINARID");
                String topic = rs.getString("TOPIC");
                String title = rs.getString("TITLE");
                String abstracts = rs.getString("ABSTRACT");
                String location = rs.getString("LOCATION");
                String modality = rs.getString("MODALITY");
                String date = rs.getString("DAY_DATE");
                String displayName = rs.getString("DISPLAYNAME");
                
                Seminar s = new Seminar(seminarID, topic, title, abstracts, location, modality, date, displayName);

                seminars.add(s);
            }

            return seminars;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }    
    
    /*public static void main(String[] args) {
        JDBCManageSeminar sem = new JDBCManageSeminar();
        Collection<Seminar> seminars = sem.getSeminars();

        for(Seminar s : seminars){
            System.out.println(s.toString());
        }

    }
    */
}
