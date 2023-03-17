package app;

import static spark.Spark.*;
import service.ProdutoService;

class Aplicacao {
	
	private static ProdutoService produtoService = new ProdutoService();
	
	public static void main(String[] args) {
		port(4567);
		
		staticFiles.location("/app");
		
		post("/criar", (request, response) -> produtoService.createProduto(request, response));
		
		get("/produto/list", (request, response) -> produtoService.getAll(request, response));
		
		get("/update/:id", (request, response) -> produtoService.updateProduto(request, response));

		get("/delete/:id", (request, response) -> produtoService.deleteProduto(request, response));
		
	}
	
}
