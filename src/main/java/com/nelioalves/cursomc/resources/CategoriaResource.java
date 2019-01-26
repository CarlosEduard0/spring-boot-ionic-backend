package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(Pageable pageable) {
        List<CategoriaDTO> lista = categoriaService.findAll(pageable).stream().map(CategoriaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Categoria> find(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoriaService.find(id));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria(categoriaDTO);
        categoria = categoriaService.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria(categoriaDTO);
        categoria.setId(id);
        categoriaService.save(categoria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
