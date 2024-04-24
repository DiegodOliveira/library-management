package com.backendiiproject.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendiiproject.library.Model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{

}
