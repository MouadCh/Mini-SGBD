/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdba;


//import com.mysql.cj.jdbc.result.ResultSetMetaData;
//import com.mysql.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author sherlockholmes
 */
public class Connexion {
     static Connection conn=null;
     //static String db="etab";
     //static String name="newuser";
     //static String password="1234";
     
    public static Connection connMysql( String db, String name,String password){
               
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db,name,password);
            if (conn!=null)
                        System.out.println("Connexion à la base de données a été établie avec succès");
             else 
                        System.out.println("Problème de connexion à la base");
            return conn ;
	}catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"","Login information are incorrect", JOptionPane.ERROR_MESSAGE); 
            return null;
	}
    }
    
//    public static Connection close(){
//               
//       try{
//            if (conn!=null)
//                   conn.close();
//             else 
//                        System.out.println("Problème de connexion à la base");
//            return conn ;
//	}catch(Exception e){
//            JOptionPane.showMessageDialog(null, e+"","Login information are incorrect", JOptionPane.ERROR_MESSAGE); 
//            return null;
//	}
//    }
  
    public static String ShowDb() {
        
         PreparedStatement ps=null;
         String ShowDb = "show databases";
         ResultSet resultSet=null;
         Connection conn = Connexion.connMysql("", "root", "");
         try {
             ps =conn.prepareStatement(ShowDb);
              resultSet=ps.executeQuery(); 
         
             ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnsNumber; i++) {
                            if (i > 1) System.out.print(",  ");
                            String columnValue = resultSet.getString(i);
                            System.out.print(columnValue + " " + rsmd.getColumnName(i));
                        }
                        System.out.println("");
                     }
        
         
         } catch (SQLException ex) {
             Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
         }
        return resultSet.toString();
    }
    
    
}
