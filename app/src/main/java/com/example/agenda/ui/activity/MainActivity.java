package com.example.agenda.ui.activity;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Usar a AppCompatActivity da suport a várias versões antigas no android
public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Aluno> adapter;
    AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Lista de Alunos");


        List<String> alunos = new ArrayList<>(Arrays.asList("Felipe", "Biah", "Bruno"));

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
        menu.add("Remover");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
        remove(alunoEscolhido);

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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(
            adapter
        );
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
