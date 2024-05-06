// Função para mostrar o modal
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

// Conexão com o Back-end

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('cadastroUsuarioForm').addEventListener('submit', function(event) {
        event.preventDefault();

        var nome = document.getElementById('nome').value;
        var email = document.getElementById('email').value;
        var tipoAcesso = document.getElementById('tipoAcesso').value;

        var usuario = {
            nome: nome,
            email: email,
            tipoAcesso: tipoAcesso
        };

        fetch('/usuario', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuario)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Ocorreu um erro ao cadastrar o usuário.');
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('cadastroUsuarioForm').reset();
            showModal('Usuário cadastrado com sucesso!', true);
        })
        .catch(error => {
            showModal(error.message, false);
        });
    });
});
