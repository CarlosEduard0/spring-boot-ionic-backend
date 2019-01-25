package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> lista = categoriaService.findAll().stream().map(CategoriaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Categoria> find(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoriaService.find(id));
    }

    @GetMapping(path = "page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Categoria> lista = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> listaDTO = lista.map(CategoriaDTO::new);
        return ResponseEntity.ok().body(listaDTO);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Categoria categoria) {
        categoria = categoriaService.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        categoriaService.save(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
