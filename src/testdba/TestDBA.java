/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdba;

//import com.mysql.jdbc.ResultSetMetaData;
//import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/**
 *
 * @author CSX101
 */
public class TestDBA {

    /**
     * @param args the command line arguments
     */
    
   
    
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
       doQuery();
    }
    
    static public void doQuery() throws SQLException{
        String requete = "CREATE USER 'newuser'@localhost IDENTIFIED BY '1234'";
        String requete2 ="GRANT ALL PRIVILEGES ON etab.* TO 'newuser'@'localhost'";
       // String quer="REPAIR TABLE mysql.db";
       // String quer2="CHECK TABLE mysql.db";
        
        
        Connection conn = Connexion.connMysql("etab", "newuser", "1234");
        PreparedStatement ps=null;
        ResultSet resultSet=null;
                  
        
        
                 ps = conn.prepareStatement("Select * from etudiant");

             
                     
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
                      
                      
                      
                      
                      
                      
                      
                      
                     /*
                     ps = conn.prepareStatement(quer2);
                     
                     ps.execute();
                     
                      Integer rowCount = 0;
                        while ( rs.next() )
                        {
                            rowCount++;
                        }
                      //rs.beforeFirst();
                      System.out.println("nombre = "+rowCount);
*/
    }
    
}
