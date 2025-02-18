package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
    private final ClienteService clienteService;

    @Autowired
    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<>(clienteService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Cliente> find(@PathVariable Long id) {
        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ClienteNewDTO clienteNewDTO) {
        Cliente cliente = new Cliente(clienteNewDTO);
        cliente = clienteService.save(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO);
        cliente.setId(id);
        clienteService.update(cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
