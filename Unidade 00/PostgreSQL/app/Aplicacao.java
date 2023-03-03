package app;

import model.Produto;

import java.util.ArrayList;

import dao.ProdutoDAO;

class Aplicacao {
	
	public static final int SAIR = 0;
	public static final int CRIAR = 1;
	public static final int BUSCAR = 2;
	public static final int ALTERAR = 3;
	public static final int DELETAR = 4;
	public static final int LISTAR = 5;
	
	public static ProdutoDAO dao = new ProdutoDAO();
			
	public static void main(String[] args) {
		int opc = 0;
		boolean CONTINUE = true;
		Produto prod = null;
		
		do{	
			opc = apresentarMenu();
			
			switch(opc){
				case CRIAR:
					prod = criarProduto();
					break;
				case BUSCAR:
					prod = buscarProduto();
					break;
				case ALTERAR:
					alterarProduto(prod);
					break;
				case DELETAR:
					dao.deleteProduto(prod);;
					break;
				case LISTAR:
					listarProdutos();
					break;
				case SAIR:
					CONTINUE = false;
			}
			
		}while(CONTINUE);
		
		
	}
	
	public static int apresentarMenu() {
		int opc = 0;
		boolean ERRO = false;
		
		do{
			MyIO.println("==== SISTEMA DE PRODUTOS ====");
			MyIO.println("1 - Iserir Produto");
			MyIO.println("2 - Buscar Produto");
			MyIO.println("3 - Alterar Produto");
			MyIO.println("4 - Deletar Produto");
			MyIO.println("5 - Listar Itens");
			MyIO.println("0 - Sair");
			
			opc = MyIO.readInt("DIGITE A OPCAO: ");
			ERRO = opc < SAIR || opc > LISTAR;
			if(ERRO) MyIO.println("=== OPCAO INVALIDA TENTE NOVAMENTE ===");
		}while(ERRO);
		
		
		return opc;
	}
	
	public static Produto criarProduto() {
		Produto novo = new Produto(MyIO.readString("PRODUTO: "), MyIO.readString("MARCA: "),
				MyIO.readString("DEPARTAMENTO: "), MyIO.readFloat("PRECO: "));
		dao.createProduto(novo);
		return novo;
	}
	
	public static void listarProdutos() {
		ArrayList<Produto> produtos = dao.getAll();
		
		for(Produto produto: produtos) {
			MyIO.println(produto.toString());
		}
	}
	
	public static Produto buscarProduto() {
		int opc;
		listarProdutos();
		opc = MyIO.readInt("DIGITE O ID DO PRODUTO: ");
		return dao.getByID(opc);
	}
	
	public static void alterarProduto(Produto p) {
		p.setNome(MyIO.readString("NOME: "));
		p.setMarca(MyIO.readString("MARCA: "));
		p.setDepartamento(MyIO.readString("DEPARTAMENTO: "));
		p.setPreco((float)MyIO.readFloat("PRECO: "));
		
		dao.updateProduto(p);
	}
	
	
}
