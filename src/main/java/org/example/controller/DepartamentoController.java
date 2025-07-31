package org.example.controller;

import org.example.entity.Departamento;
import org.example.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public List<Departamento> listarTodos() {
        return departamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Departamento> buscarPorId(@PathVariable UUID id) {
        return departamentoRepository.findById(id);
    }

    @GetMapping(params = "departamento")
    public List<Departamento> buscarPorNome(@RequestParam String departamento) {
        return departamentoRepository.findByDepartamento(departamento);
    }

    @PostMapping
    public Departamento criar(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @PutMapping("/{id}")
    public Departamento atualizar(@PathVariable UUID id, @RequestBody Departamento atualizado) {
        return departamentoRepository.findById(id).map(departamento -> {
            departamento.setDepartamento(atualizado.getDepartamento());
            departamento.setQtdeFuncionarios(atualizado.getQtdeFuncionarios());
            return departamentoRepository.save(departamento);
        }).orElseThrow(() -> new RuntimeException("Departamento não encontrado."));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        departamentoRepository.deleteById(id);
    }
}
