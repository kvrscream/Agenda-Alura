package com.example.agenda.dao;

import android.util.Log;

import com.example.agenda.model.Aluno.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorId = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorId);
        alunos.add(aluno);
        contadorId++;
    }


    public void edit(Aluno aluno){
        Aluno alunoEncontrado = null;
        for (Aluno a: alunos) {
            if(a.getId() == aluno.getId()){
                alunoEncontrado = a;
            }
        }

        if(alunoEncontrado != null){
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }


    public void remove(Aluno aluno){
        alunos.remove(aluno);
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

}
