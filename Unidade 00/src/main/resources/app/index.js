const listaProdutos = document.getElementById('lista-produtos');

// Função para popular a lista de produtos
function popularListaProdutos(produtos) {
  console.log(produtos)
  listaProdutos.innerHTML = '';
  if (produtos.mensagem == "SUCCESS") {
    let produts = produtos.data.produtos
    for (let i = 0; i < produts.length; i++) {
      const produto = produts[i];
      const li = document.createElement('li');
      li.innerHTML = `<strong>ID:</strong> ${parseInt(produto.IDProduto)} | <strong>Nome:</strong> ${produto.nome} | <strong>Departamento:</strong> ${produto.departamento} | <strong>Marca:</strong> ${produto.marca} | <strong>Preço:</strong> R$ ${produto.preco.toFixed(2)}`;

      const btnAlterar = document.createElement('button');
      btnAlterar.innerHTML = "Alterar";
      btnAlterar.onclick = function () {
        carregarFormularioAlterar(produto);
      }
      li.appendChild(btnAlterar);

      const btnDeletar = document.createElement('button');
      btnDeletar.innerHTML = "Deletar";
      btnDeletar.value = produto.IDProduto;
      btnDeletar.onclick = function () {
        id = btnDeletar.value
        deleteProduto(id);
      }
      li.appendChild(btnDeletar);

      listaProdutos.appendChild(li);
    }
  }
}

function carregarFormularioAlterar(produto) {
  id.value = produto.IDProduto;
  nome.value = produto.nome;
  marca.value = produto.marca;
  departamento.value = produto.departamento;
  preco.value = produto.preco.toFixed(2);
  btnlista.innerHTML = "Alterar";
}

function lerProduto() {
  return produto = {
    id: document.getElementById('id').value,
    nome: document.getElementById('nome').value,
    marca: document.getElementById('marca').value,
    departamento: document.getElementById('departamento').value,
    preco: document.getElementById('preco').value
  };
}

function buscarProdutos() {
  btnlista.innerHTML = "Criar";
  fetch('/produto/list')
    .then(response => response.json())
    .then(data => popularListaProdutos(data))
    .catch(error => console.error(error));
}

function criarProduto() {
  produto = lerProduto();
  fetch('/criar', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(produto)
  })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
}

function updateItem() {
  produto = lerProduto()
  fetch('/update', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(produto)
  })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
}

function deleteProduto(id) {
  fetch('/delete/' + id)
    .then(response => response.json())
    .catch(error => console.error(error));
}

btnlista.onclick = function () {
  console.log(btnlista.innerHTML == "Alterar")
  if(btnlista.innerHTML == "Alterar"){
    id = document.getElementById('id').value
    updateItem();
  }else{
    criarProduto();
  }
}


// Chamar a função buscarProdutos no carregamento da página
buscarProdutos();
