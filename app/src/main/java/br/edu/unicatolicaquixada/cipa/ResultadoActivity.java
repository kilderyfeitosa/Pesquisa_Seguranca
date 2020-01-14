package br.edu.unicatolicaquixada.cipa;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.edu.unicatolicaquixada.cipa.data.Dados;
import br.edu.unicatolicaquixada.cipa.model.Questao;

public class ResultadoActivity extends AppCompatActivity {
    private AppCompatRatingBar rating;
    private TextView tvResultado, tvTempo, tvRespostas;
    private Button jogar, sair;
    private List<Questao> questoes = Dados.getQuestoes();
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        ActionBar actionBar = getSupportActionBar();
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        actionBar.setTitle(text);

        Intent intent = getIntent();
        int result = intent.getIntExtra("result", 0);
        long milis = intent.getLongExtra("tempo", 0L);
        String respostas = "Suas respostas: " + intent.getStringExtra("respostas");
        respostas = respostas.replace("1", "a").replace("2", "b").replace("3", "c").replace("4", "d").replace("5", "e");

        String textoQuestao = result == 1 ? " questão!" : " questões!";

        rating = (AppCompatRatingBar) findViewById(R.id.ratingBar);

        tvResultado = (TextView) findViewById(R.id.textResult);
        tvTempo = (TextView) findViewById(R.id.textTempo);
        tvRespostas = (TextView) findViewById(R.id.textRespostas);



        jogar = (Button) findViewById(R.id.jogar_novo);
        sair = (Button) findViewById(R.id.sair_resultado);

        rating.setIsIndicator(true);
        rating.setNumStars(5);
        rating.setStepSize(1);
        rating.setClickable(false);
        rating.setRating(result);

        long segundos = milis / 1000;
        long minutos = segundos / 60;
        String tempo = String.format ("%02d:%02d", minutos, segundos);

        tvResultado.setText("Você acertou: " + result + textoQuestao);
        tvTempo.setText("Tempo: " + tempo.substring(0, 5));
        tvRespostas.setText(respostas);

        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogarNovamente();
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out();
            }
        });

    //----
        Dados enunciado = new Dados();
        Questao teste = new Questao();

        teste.getOpcaoCorreta();
        enunciado.getQuestaoUm().getOpcaoCorreta();
        ListView listview = (ListView) findViewById(R.id.listaPerguntas);

            String[] dadosEnunciados = new String[] {"1ª " + enunciado.getQuestaoUm().getEnunciado(),
                                            "2ª " + enunciado.getQuestaoDois().getEnunciado(),
                                            "3ª " + enunciado.getQuestaoTres().getEnunciado(),
                                            "4ª " + enunciado.getQuestaoQuatro().getEnunciado(),
                                            "5ª " + enunciado.getQuestaoCinco().getEnunciado(),
        };
        String[] dadosAlternativasCorretas = new String[] { enunciado.getQuestaoUm().getCorreta(),
                 enunciado.getQuestaoDois().getCorreta(),
                 enunciado.getQuestaoTres().getCorreta(),
                 enunciado.getQuestaoQuatro().getCorreta(),
                 enunciado.getQuestaoCinco().getCorreta(),
        };
        MyAdapter adapter = new MyAdapter(this,dadosEnunciados, dadosAlternativasCorretas);
        listview.setAdapter(adapter);

    }
    class MyAdapter extends ArrayAdapter {
        String[] enunciadoArray;
        String[] alternativaArray;

        public MyAdapter(Context context, String[] dados1, String[] dados2){
            super(context, R.layout.custlistview, R.id.txtEnunciado, dados1);
            this.enunciadoArray=dados1;
            this.alternativaArray=dados2;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.custlistview, parent, false);

            TextView myEnunciado = (TextView) row.findViewById(R.id.txtEnunciado);
            TextView myAlternativa = (TextView) row.findViewById(R.id.txtCorreta);
            myEnunciado.setText(enunciadoArray[position]);
            myAlternativa.setText(alternativaArray[position]);
            return row;
        }

    }


    private void jogarNovamente() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void out(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("SAIR", true);
        startActivity(intent);
        this.finish();
    }
}