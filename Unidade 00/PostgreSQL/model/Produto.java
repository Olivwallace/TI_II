package model;

public class Produto {
	
	//------------------------------------------- Atributos ---------------------------------------------------------//

    private int IDProduto;
    private String nome;
    private String marca;
    private String departamento;
    private float preco;

    //----------------------------------------- Fim Atributos -------------------------------------------------------//


    //------------------------------------------- Construtor --------------------------------------------------------//

    public Produto(int IDProduto, String nome, String marca, String departamento, float preco){
        this.IDProduto = IDProduto;
        this.nome = nome;
        this.marca = marca;
        this.departamento = departamento;
        this.preco = preco;
    }
    
    public Produto(String nome, String marca, String departamento, float preco){
        this.nome = nome;
        this.marca = marca;
        this.departamento = departamento;
        this.preco = preco;
    }
    
    public Produto(){
    	this.IDProduto = -1;
    	this.nome = "";
    	this.marca = "";
    	this.departamento = "";
    	this.preco = 0;
    }

    //------------------------------------------ Fim Construtor ----------------------------------------------------//


    //---------------------------------------- Getters and Setters -------------------------------------------------//

    public int getIDProduto() {
        return IDProduto;
    }

    public void setIDProduto(int IDProduto) {
        this.IDProduto = IDProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    //-------------------------------------- Fim Getters and Setters -----------------------------------------------//

	@Override
	public String toString() {
		return "Produto [ID: " + this.getIDProduto() + ", Nome: " + this.getNome() +
				", Departamento: " + this.getDepartamento() + ", Valor: " + this.getPreco() + "]";
	}	
}
