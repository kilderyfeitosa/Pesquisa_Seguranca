package br.edu.unicatolicaquixada.cipa;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.edu.unicatolicaquixada.cipa.data.Dados;
import br.edu.unicatolicaquixada.cipa.model.Questao;

public class GameActivity extends AppCompatActivity {
    private Questao atual;
    private List<Questao> questoes = Dados.getQuestoes();
    private int countCorretas = 0;
    private int count = 0;
    private ImageView placa;
    private TextView enunciado, inicio, sair;
    private Chronometer tempo;
    private RadioGroup radios;
    private RadioButton radioUm, radioDois, radioTres, radioQuatro;
    private Button botao;
    private long mLastStopTime = 0;
    private String respostas = "[";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ActionBar actionBar = getSupportActionBar();

        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        actionBar.setTitle(text);


        tempo = (Chronometer) findViewById(R.id.tempo);

        placa = (ImageView) findViewById(R.id.placa_game);

        enunciado = (TextView) findViewById(R.id.enunciado);
        inicio = (TextView) findViewById(R.id.inicio);
        sair = (TextView) findViewById(R.id.sair);

        radios = (RadioGroup) findViewById(R.id.radioGroup);
        radioUm = (RadioButton) findViewById(R.id.radio1);
        radioDois = (RadioButton) findViewById(R.id.radio2);
        radioTres = (RadioButton) findViewById(R.id.radio3);
        radioQuatro = (RadioButton) findViewById(R.id.radio4);

        botao = (Button) findViewById(R.id.btnProxima);

        radios.clearCheck();

        iniciarVoltarTempo();
        tempo.setText("02:00");

        atual = questoes.get(count);
        setQuestaoAtual(atual);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnBotao();
            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sair();
            }
        });
    }

    private void clickOnBotao() {
        RadioButton resposta = (RadioButton) findViewById(radios.getCheckedRadioButtonId());
        if (radios.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Selecione uma opção para continuar!", Toast.LENGTH_SHORT).show();
        } else {

            if (atual.getAlternativas().get(atual.getOpcaoCorreta()).getTexto().equals(resposta.getText())) {
                countCorretas++;
            }
            if (count < 4) {
                respostas = respostas + atual.getAlternativaSelecionada(resposta.getText().toString()) + ", ";
                atual = questoes.get(++count);
                setQuestaoAtual(atual);
            } else {
                tempo.stop();
                respostas = respostas + atual.getAlternativaSelecionada(resposta.getText().toString()) + "]";
                        Intent intent = new Intent(this, ResultadoActivity.class);
                intent.putExtra("result", countCorretas);
                intent.putExtra("tempo", milis());
                intent.putExtra("respostas", respostas);
                startActivity(intent);
                this.finish();
            }
        }
    }

    private void setQuestaoAtual(Questao atual) {
        radios.clearCheck();
        enunciado.setText(atual.getNumero() + "ª) " + atual.getEnunciado());
        radioUm.setText(atual.getAlternativas().get(0).getTexto());
        radioDois.setText(atual.getAlternativas().get(1).getTexto());
        radioTres.setText(atual.getAlternativas().get(2).getTexto());
        radioQuatro.setText(atual.getAlternativas().get(3).getTexto());
        placa.setImageResource(atual.getIdImagem());
        if (atual.getNumero() == questoes.size())
            botao.setText("FINALIZAR");
    }

    private long milis() {
        return SystemClock.elapsedRealtime() - tempo.getBase();
    }

    private long timeWhenStopped() {
        return tempo.getBase() - SystemClock.elapsedRealtime();
    }

    private void sair() {
        pausarTempo();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tem certeza que deseja sair do aplicativo?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        out();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        iniciarVoltarTempo();
                    }
                }).create().show();
    }

    private void toHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void out() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("SAIR", true);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        pausarTempo();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tem certeza que deseja voltar para o Início?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        toHome();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        iniciarVoltarTempo();
                    }
                }).create().show();
    }

    private void iniciarVoltarTempo() {
        if (mLastStopTime == 0)
            tempo.setBase(SystemClock.elapsedRealtime());
        else {
            long intervalOnPause = (SystemClock.elapsedRealtime() - mLastStopTime);
            tempo.setBase(tempo.getBase() + intervalOnPause);
        }
        tempo.start();
    }

    private void pausarTempo() {
        tempo.stop();
        mLastStopTime = SystemClock.elapsedRealtime();
    }
}