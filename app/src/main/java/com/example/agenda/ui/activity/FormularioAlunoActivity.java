package com.example.agenda.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    EditText edtTelefone;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        final AlunoDAO dao = new AlunoDAO();
        setTitle("Fromulário de Cadastro");

        Button btnSalva = findViewById(R.id.btnSalvar);
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);


        Intent dados = getIntent();
        if(dados.hasExtra("aluno")) {
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            edtNome.setText(aluno.getNome());
            edtEmail.setText(aluno.getEmail());
            edtTelefone.setText(aluno.getTelefone());
            Toast.makeText(this, ""  + aluno.getId(), Toast.LENGTH_SHORT).show();

        } else {
            aluno = new Aluno();
        }

        btnSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preencheAluno();

                if(aluno.getId() > 0) {
                    dao.edit(aluno);
                } else {
                    dao.salva(aluno);
                }
                finish();

            }
        });





    }


    private void preencheAluno(){
        String nome = edtNome.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String email = edtEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }


}
