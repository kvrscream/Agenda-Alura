package com.example.agenda.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Usar a AppCompatActivity da suport a várias versões antigas no android
public class MainActivity extends AppCompatActivity {

    private ListaAlunosAdapter adapter;
    AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Lista de Alunos");

        FloatingActionButton addButton = findViewById(R.id.floatingActionButton3);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FormularioAlunoActivity.class));
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {

        if(item.getItemId() == R.id.remover){
            new AlertDialog.Builder(this)
                    .setTitle("Voce deseja excluir esse aluno?")
                    .setMessage("Ao confirmar este aluno não poderá mais ser recuperado!")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                            remove(alunoEscolhido);
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();

        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }


    private void configuraLista(){
        final List<Aluno> alunos = dao.todos();

        ListView lista = findViewById(R.id.listVW);

        carregaAlunos(lista, alunos);

        registerForContextMenu(lista);

        abreAlunoEdita(lista, alunos);

    }


    private void carregaAlunos(ListView lista, final List<Aluno> alunos){
        adapter = new ListaAlunosAdapter(this);
        adapter.addAll(dao.todos());
        lista.setAdapter(adapter);
    }


    public void remove(Aluno aluno){
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void abreAlunoEdita(ListView lista, final List<Aluno> alunos){
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Pega o aluno na lista com base na posição
                Aluno alunoEscolhido =  alunos.get(i);
                Intent abreForm = new Intent(MainActivity.this, FormularioAlunoActivity.class);

                //Recurso Extra para transferir dados entre activitys
                abreForm.putExtra("aluno", alunoEscolhido);
                startActivity(abreForm);

            }
        });
    }
}
