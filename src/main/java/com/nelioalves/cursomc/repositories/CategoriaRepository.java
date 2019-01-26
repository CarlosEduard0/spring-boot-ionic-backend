package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Categoria;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long> {
}
