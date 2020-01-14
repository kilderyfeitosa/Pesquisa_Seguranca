package br.edu.unicatolicaquixada.cipa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView botaoIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botaoIniciar = (ImageView) findViewById(R.id.botao_iniciar);

        botaoIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar();
            }
        });


    }

    @Override
    protected void onResume() {
        if(getIntent().getBooleanExtra("SAIR", false)){
            finish();
        }
        super.onResume();
    }

    private void iniciar() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void finalizar(View v){
        this.finish();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}