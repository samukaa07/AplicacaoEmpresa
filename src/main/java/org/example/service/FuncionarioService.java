package org.example.service;

import org.example.entity.Departamento;
import org.example.entity.Funcionario;
import org.example.functional.ValidadorFuncionario;
import org.example.repository.DepartamentoRepository;
import org.example.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Funcionario associarDepartamento(UUID funcionarioId, UUID departamentoId) {
        Funcionario f = funcionarioRepository.findById(funcionarioId).orElseThrow();
        Departamento d = departamentoRepository.findById(departamentoId).orElseThrow();
        f.getDepartamentos().add(d);
        return funcionarioRepository.save(f);
    }

    public Funcionario desassociarDepartamento(UUID funcionarioId, UUID departamentoId) {
        Funcionario f = funcionarioRepository.findById(funcionarioId).orElseThrow();
        f.getDepartamentos().removeIf(dep -> dep.getId().equals(departamentoId));
        return funcionarioRepository.save(f);
    }

    /**
     * Esse metodo Retorna os funcionários com salário maior que 0,
     * usando Stream API e uma Functional Interface personalizada.
     */
    public List<Funcionario> listarComSalarioMaiorQue(BigDecimal valorMinimo) {
        ValidadorFuncionario salarioValido = f -> f.getSalario().compareTo(valorMinimo) > 0;

        return funcionarioRepository.findAll()
                .stream()
                .filter(salarioValido::validar)
                .collect(Collectors.toList());
    }
}


