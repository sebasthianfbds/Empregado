/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.dao;

import trabalhofinal.model.Empregado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebasthian
 */
public class EmpregadoDAO {
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    private PreparedStatement stmRO;
    
    private Connection conn;
   
    
    public EmpregadoDAO() {
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

            this.stmC = this.conn.prepareStatement("INSERT INTO empregado(nome,cargo,salario) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM empregado");
            this.stmU = this.conn.prepareStatement("UPDATE empregado SET nome=?, cargo=?, salario=? WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM empregado WHERE id=?");
            this.stmRO = this.conn.prepareStatement("Select * FROM empregado WHERE id=?");
            
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void close(){
        try{
            this.conn.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Empregado> read() {
        try {
            ResultSet rs = this.stmR.executeQuery();
            
            List<Empregado> empregados = new ArrayList<>();
            
            while(rs.next()) {
                Empregado e = new Empregado();
                e.setId(rs.getLong("id"));
                e.setNome(rs.getString("nome"));
                e.setCargo(rs.getString("cargo"));
                e.setSalario(rs.getDouble("salario"));
                empregados.add(e);
            }
            
            return empregados;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
 
    public Empregado create(Empregado novoEmpregado) {
        try {
            this.stmC.setString(1, novoEmpregado.getNome());
            this.stmC.setString(2,novoEmpregado.getCargo());
            this.stmC.setDouble(3,novoEmpregado.getSalario());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            novoEmpregado.setId(id);
            
            return novoEmpregado;
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public Empregado readOne(int id) {
        try {
            this.stmRO.setInt(1, id);
            
            ResultSet rs = this.stmRO.executeQuery();
            
            if(rs.next()) {
                Empregado e = new Empregado();
                e.setId(rs.getLong("id"));
                e.setNome(rs.getString("nome"));
                e.setCargo(rs.getString("cargo"));
                e.setSalario(rs.getDouble("salario"));
                
                return e;
            }
            else {
                return null;
            }
            
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public Empregado update(int id, Empregado e) {
        try {
            this.stmU.setString(1, e.getNome());
            this.stmU.setString(2, e.getCargo());
            this.stmU.setDouble(3, e.getSalario());
            this.stmU.setInt(4, id);
            
            int retorno = stmU.executeUpdate();
            
            
            if(retorno == 0)
                return null;
            else {
                e.setId(id);
                return e;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
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
        }catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
