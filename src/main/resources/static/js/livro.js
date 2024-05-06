document.getElementById('cadastroLivroForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var livro = {
        nome: document.getElementById('nome').value,
        autor: document.getElementById('autor').value,
        editora: document.getElementById('editora').value,
        dataDeLancamento: document.getElementById('dataDeLancamento').value
    };

    fetch('/livros/cadastrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(livro)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Ocorreu um erro ao cadastrar o livro.');
        }
        return response.json();
    })
    .then(data => {
        showModal('Livro cadastrado com sucesso!');
        document.getElementById('cadastroLivroForm').reset();
    })
    .catch(error => {
        showModal(error.message);
    });
});

function showModal(message, isSuccess = false) {
    var modal = document.getElementById("modal");
    var modalText = document.getElementById("modalText");
    var span = document.getElementsByClassName("close")[0];
    var iconHTML = isSuccess ? "<i class='fas fa-check-circle' style='color: green;'></i> " :
                               "<i class='fas fa-exclamation-circle' style='color: red;'></i> ";

    modalText.innerHTML = iconHTML + message;
    modal.style.display = "block";

    span.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}