package org.example;

import org.example.entity.Departamento;
import org.example.entity.Funcionario;
import org.example.repository.DepartamentoRepository;
import org.example.repository.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner loadData(DepartamentoRepository deptoRepo, FuncionarioRepository funcRepo) {
        return args -> {
            Departamento d1 = new Departamento();
            d1.setDepartamento("TI");
            d1.setQtdeFuncionarios(1);
            deptoRepo.save(d1);

            Funcionario f1 = new Funcionario();
            f1.setNome("João Silva");
            f1.setEndereco("Rua A");
            f1.setSalario(new BigDecimal("4500.00"));
            f1.setDataContrato(LocalDateTime.now());
            f1.setFuncao("Desenvolvedor");
            f1.setDepartamentos(Set.of(d1));
            funcRepo.save(f1);
        };
    }
}
