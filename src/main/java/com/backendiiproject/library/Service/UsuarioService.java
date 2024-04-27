package com.backendiiproject.library.Service;

import com.backendiiproject.library.Model.Usuario;
import com.backendiiproject.library.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    public Usuario create(Usuario usuario){
        return  repository.save(usuario);
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Optional<Usuario> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    // Será preciso usar DTO nessa parte, ainda a ser discutido

//    public void patch(Long usuarioId, String novoEmail){
//        Optional<Usuario> usuarioASerEditado = repository.findById(usuarioId);
//        usuarioASerEditado.get().setEmail(novoEmail);
//        repository.save(usuarioASerEditado.get());
//    }
//
//    public void delete(Long id){
//        repository.deleteById(id);
//    }
}

