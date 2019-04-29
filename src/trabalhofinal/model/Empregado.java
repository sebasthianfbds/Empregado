package trabalhofinal.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Fabricio
 */
public class Empregado {
    private long id;
    private String nome;
    private String cargo;
    private double salario;
    public Empregado() {}
    public Empregado(long id, String n, String c, double s) {
        this.id = id;
        nome = n;
        cargo = c;
        salario = s;
    }
    public long getId() { return id; }
    public String getNome() { return nome; }
    public double getSalario() { return salario; }
    public String getCargo() { return cargo; }
    
    public void setId(long id) { this.id = id; }
    public void setNome(String n) { nome = n; }
    public void setSalario(double s) { salario = s; }
    public void setCargo(String c) { cargo = c; }
}
