package org.example.repository;

import org.example.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    List<Funcionario> findByNome(String nome);
    List<Funcionario> findByNomeAndFuncao(String nome, String funcao);
    List<Funcionario> findByNomeAndDepartamentos_Departamento(String nome, String departamento);
}
