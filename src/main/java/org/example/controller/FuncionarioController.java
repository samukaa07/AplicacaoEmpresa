package org.example.controller;

import org.example.entity.Funcionario;
import org.example.repository.FuncionarioRepository;
import org.example.repository.DepartamentoRepository;
import org.example.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> buscarPorId(@PathVariable UUID id) {
        return funcionarioRepository.findById(id);
    }

    @GetMapping(params = "nome")
    public List<Funcionario> buscarPorNome(@RequestParam String nome) {
        return funcionarioRepository.findByNome(nome);
    }

    @GetMapping(params = {"nome", "funcao"})
    public List<Funcionario> buscarPorNomeEFuncao(@RequestParam String nome, @RequestParam String funcao) {
        return funcionarioRepository.findByNomeAndFuncao(nome, funcao);
    }

    @GetMapping(params = {"nome", "departamento"})
    public List<Funcionario> buscarPorNomeEDepartamento(@RequestParam String nome, @RequestParam String departamento) {
        return funcionarioRepository.findByNomeAndDepartamentos_Departamento(nome, departamento);
    }

    @PostMapping
    public Funcionario criar(@RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{id}")
    public Funcionario atualizar(@PathVariable UUID id, @RequestBody Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setNome(funcionarioAtualizado.getNome());
            funcionario.setEndereco(funcionarioAtualizado.getEndereco());
            funcionario.setBairro(funcionarioAtualizado.getBairro());
            funcionario.setCep(funcionarioAtualizado.getCep());
            funcionario.setTelefone(funcionarioAtualizado.getTelefone());
            funcionario.setSalario(funcionarioAtualizado.getSalario());
            funcionario.setDataContrato(funcionarioAtualizado.getDataContrato());
            funcionario.setFuncao(funcionarioAtualizado.getFuncao());
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        funcionarioRepository.deleteById(id);
    }

    @PostMapping("/{idFuncionario}/departamentos/{idDepartamento}")
    public Funcionario associarDepartamento(@PathVariable UUID idFuncionario, @PathVariable UUID idDepartamento) {
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        departamentoRepository.findById(idDepartamento)
                .ifPresent(funcionario.getDepartamentos()::add);
        return funcionarioRepository.save(funcionario);
    }

    @DeleteMapping("/{idFuncionario}/departamentos/{idDepartamento}")
    public Funcionario desassociarDepartamento(@PathVariable UUID idFuncionario, @PathVariable UUID idDepartamento) {
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        funcionario.getDepartamentos().removeIf(dep -> dep.getId().equals(idDepartamento));
        return funcionarioRepository.save(funcionario);
    }

    @GetMapping("/salario-maior-que")
    public List<Funcionario> listarSalarioMaiorQue(@RequestParam("valor") BigDecimal valor) {
        return funcionarioService.listarComSalarioMaiorQue(valor);
    }
}