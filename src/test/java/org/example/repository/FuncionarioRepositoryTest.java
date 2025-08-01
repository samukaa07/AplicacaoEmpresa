package org.example.repository;


import org.example.entity.Funcionario;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

class FuncionarioRepositoryTest {

    @Test
    void testSaveFuncionario() {
        // Mock do repositório
        FuncionarioRepository funcionarioRepository = Mockito.mock(FuncionarioRepository.class);

        // Criação de um objeto Funcionario
        Funcionario funcionario = new Funcionario();
        funcionario.setId(UUID.randomUUID());
        funcionario.setNome("João Silva");
        funcionario.setEndereco("Rua Exemplo, 123");
        funcionario.setBairro("Centro");
        funcionario.setCep("12345-678");
        funcionario.setTelefone("(11) 98765-4321");
        funcionario.setSalario(new BigDecimal("5000.00"));
        funcionario.setDataContrato(LocalDateTime.now());
        funcionario.setFuncao("Desenvolvedor");

        // Simulação do comportamento do método save
        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);

        // Chamada do método save
        Funcionario savedFuncionario = funcionarioRepository.save(funcionario);

        // Verificação se o método save foi chamado
        verify(funcionarioRepository, times(1)).save(funcionario);

        // Verificação do retorno
        assert savedFuncionario != null;
        assert savedFuncionario.getNome().equals("João Silva");
    }
}