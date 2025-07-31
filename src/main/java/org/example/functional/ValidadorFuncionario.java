package org.example.functional;

import org.example.entity.Funcionario;

@FunctionalInterface
public interface ValidadorFuncionario {
    boolean validar(Funcionario funcionario);
}
