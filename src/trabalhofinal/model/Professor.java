package trabalhofinal.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author x036716
 */
public class Professor {
    private long id;
    private String nome;
    private int matricula;
    public Professor() {}
    public Professor(long id, String n, int m) {
        this.id = id;
        nome = n;
        matricula = m;
    }
    public long getId() { return id; }
    public String getNome() { return nome; }
    public int getMatricula() { return matricula; }
    public void setId(long id) { this.id = id; }
    public void setNome(String n) { nome = n; }
    public void setMatricula(int m) { matricula = m; }
}
