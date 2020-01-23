package com.example.agenda.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    EditText edtTelefone;


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



        btnSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                String telefone = edtTelefone.getText().toString();
                String email = edtEmail.getText().toString();

                Aluno aluno = new Aluno(nome, telefone, email);
                dao.salva(aluno);

                finish();

            }
        });


        Intent dados = getIntent();
        Aluno aluno = (Aluno) dados.getSerializableExtra("aluno");

        if(aluno != null) {
            edtNome.setText(aluno.getNome());
            edtEmail.setText(aluno.getEmail());
            edtTelefone.setText(aluno.getTelefone());
        }


    }
}
