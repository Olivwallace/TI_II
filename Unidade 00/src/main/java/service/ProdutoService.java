package service;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dao.ProdutoDAO;
import model.Produto;
import spark.Request;
import spark.Response;

public class ProdutoService {
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	ResponseService resposta;
	
	public Object createProduto(Request req, Response resp) {	
	    resp.type("application/json");
	    
	    Gson gson = new Gson();
	    Produto produto = gson.fromJson(req.body(), Produto.class);
	    
	    if(produtoDAO.createProduto(produto)) {
	        JsonObject data = new JsonObject();
	        
	        data.addProperty("id", produtoDAO.lastID());
	        data.addProperty("nome", produto.getNome());
	        data.addProperty("marca", produto.getMarca());
	        data.addProperty("departamento", produto.getDepartamento());
	        data.addProperty("preco", produto.getPreco());
	        
	        resp.body(gson.toJson(data));
	        resposta = new ResponseService(201, "SUCCESS", data);
	    } else {
	        resposta = new ResponseService(404, "PRODUTO NAO INSERIDO");
	    }
	    
	    return resposta.toJson();
	}

	public Object updateProduto(Request req, Response resp) {
		resp.type("application/json");
	    
	    Gson gson = new Gson();
	    Produto produto = gson.fromJson(req.body(), Produto.class);
	    
	    if(produtoDAO.updateProduto(produto)) {
	        JsonObject data = new JsonObject();
	        data.addProperty("id", produto.getIDProduto());
	        data.addProperty("nome", produto.getNome());
	        data.addProperty("marca", produto.getMarca());
	        data.addProperty("departamento", produto.getDepartamento());
	        data.addProperty("preco", produto.getPreco());
	        
	        resp.body(gson.toJson(data));
	        resposta = new ResponseService(201, "SUCCESS", data);
	    } else {
	    	resp.body(gson.toJson(produto));
	        resposta = new ResponseService(404, "PRODUTO NAO INSERIDO");
	    }
	    
	    return resposta.toJson();
	}
	
	public Object getAll(Request req, Response resp) {
		resp.type("application/json");
	    
	    Gson gson = new Gson();
	    
	    ArrayList<Produto> produtos = produtoDAO.getAll();
	    
	    if(produtos != null) {
	    	JsonObject data = new JsonObject();
		    JsonArray jsonArray = gson.toJsonTree(produtos).getAsJsonArray();
		    data.add("produtos", jsonArray);
		    
		    resp.body(gson.toJson(data));
		    resposta = new ResponseService(201, "SUCCESS", data);
	    }else {
	    	resposta = new ResponseService(404, "PRODUTO NAO INSERIDO");
	    }
	    
		return resposta.toJson();
	}
	
	public Object deleteProduto(Request req, Response resp) {
		resp.type("application/json");
	    
	    Gson gson = new Gson();
	    Produto produto = new Produto();
	    
	    int id = Integer.parseInt(req.params(":id"));
	    produto.setIDProduto(id);
	    
	    if(produtoDAO.deleteProduto(produto)) {
	    	resposta = new ResponseService(201, "SUCCESS");
	    	resp.body(gson.toJson(resposta));
	    }else {
	    	resposta = new ResponseService(404, "PRODUTO NAO INSERIDO");
	    }
	    
		return resposta.toJson();
	}
	
}
