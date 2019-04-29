/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.dao;

import static java.lang.System.out;
import trabalhofinal.model.Professor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaquim
 */
public class ProfessorDAO {
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    private PreparedStatement stmRO;
    
    private Connection conn;
   
    
    public ProfessorDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trabalhoFinal","root","4sus#R0cK5");
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
//            out.println("Driver JDBC carregado!");
//            
//            String url = "jdbc:derby://localhost:1527/faculdade";
//            String usuario = "app";
//            String senha ="asd";
        
            
//            this.conn = DriverManager.getConnection(url,usuario,senha);

            this.stmC = this.conn.prepareStatement("INSERT INTO professor(nome, matricula) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM professor");
            this.stmU = this.conn.prepareStatement("UPDATE professor SET nome=?, matricula=? WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM professor WHERE id=?");
            this.stmRO = this.conn.prepareStatement("Select * FROM professor WHERE id=?");
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void close(){
        try{
            this.conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Professor> read() {
        try {
            ResultSet rs = this.stmR.executeQuery();
            
            List<Professor> professores = new ArrayList<>();
            
            while(rs.next()) {
                Professor p = new Professor();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setMatricula(rs.getInt("matricula"));
                
                professores.add(p);
            }
            
            return professores;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public Professor create(Professor novoProfessor) {
        try {
            this.stmC.setString(1, novoProfessor.getNome());
            this.stmC.setInt(2, novoProfessor.getMatricula());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            novoProfessor.setId(id);
            
            return novoProfessor;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Professor readOne(int id) {
        try {
            this.stmRO.setInt(1, id);
            
            ResultSet rs = this.stmRO.executeQuery();
            
            if(rs.next()) {
                Professor p = new Professor();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setMatricula(rs.getInt("matricula"));
                return p;
            }
            else {
                return null;
            }
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Professor update(int id, Professor p) {
        try {
            this.stmU.setString(1, p.getNome());
            this.stmU.setInt(2, p.getMatricula());
            this.stmU.setInt(3, id);
            
            int retorno = stmU.executeUpdate();
            
            
            if(retorno == 0)
                return null;
            else {
                p.setId(id);
                return p;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean delete(int id) {
        try {
            stmD.setInt(1, id);
            int retorno = stmD.executeUpdate();
            if(retorno != 0)
                return true;
            return false;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
