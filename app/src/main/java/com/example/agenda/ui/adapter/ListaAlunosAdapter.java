package com.example.agenda.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Aluno.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {

    private Context context;
    public List<Aluno> alunos = new ArrayList<>();

    public ListaAlunosAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.layout_item_aluno, parent, false);

        pegaItensViewCriada(position, viewCriada);

        return viewCriada;
    }

    private void pegaItensViewCriada(int position, View viewCriada) {
        TextView txtAlunoNome = (TextView) viewCriada.findViewById(R.id.txtAlunoNome);
        txtAlunoNome.setText(alunos.get(position).getNome());

        TextView txtAlunoTelefone = (TextView) viewCriada.findViewById(R.id.txtAlunoTelefone);
        txtAlunoTelefone.setText(alunos.get(position).getTelefone());
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void addAll(List<Aluno> todos) {
        alunos.addAll(todos);
        notifyDataSetChanged();
    }
}
