package com.example.agenda.application;

import android.app.Application;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno.Aluno;

public class AgendaApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        carregaAlunosTeste();
    }

    private void carregaAlunosTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Felipe", "1122222333", "fel@ipe.com.br"));
        dao.salva(new Aluno("Biah", "1122222333", "bi@ah.com.br"));
    }
}
