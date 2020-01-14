package br.edu.unicatolicaquixada.cipa.data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.unicatolicaquixada.cipa.R;
import br.edu.unicatolicaquixada.cipa.model.Alternativa;
import br.edu.unicatolicaquixada.cipa.model.Questao;

/**
 * Created by lapis on 19/05/17.
 */
public class Dados {
    public static List<Questao> getQuestoes() {
        List<Questao> questoes = new ArrayList<>();
        questoes.add(getQuestaoUm());
        questoes.add(getQuestaoDois());
        questoes.add(getQuestaoTres());
        questoes.add(getQuestaoQuatro());
        questoes.add(getQuestaoCinco());
        return questoes;
    }

    public static Questao getQuestaoUm() {
        Alternativa alt1 = new Alternativa(1, "A) Semana Internacional de Precaução em Ambiente do Trabalho.", false);
        Alternativa alt2 = new Alternativa(2, "B) Semana Internacional de Prevenção de Acidentes no Trabalho.", false);
        Alternativa alt3 = new Alternativa(3, "C) Semana Interna de Precaução em Ambiente do Trabalho.", false);
        Alternativa alt4 = new Alternativa(4, "D) Semana Interna de Prevenção de Acidentes no Trabalho.", true);
        return new Questao(1, R.drawable.sipat, "O que significa SIPAT?", 3, Arrays.asList(alt1, alt2, alt3, alt4), alt4.getTexto());
    }

    public static Questao getQuestaoDois() {
        Alternativa alt1 = new Alternativa(1, "A) A cor verde representa o risco físico (ruídos, calor, frio, pressões, umidade, radiações e vibrações).", false);
        Alternativa alt2 = new Alternativa(2, "B) A cor vermelha representa o risco de acidentes (condições físicas e de segurança inadequada: iluminação deficiente, risco de incêndio, explosões e eletricidade). ", true);
        Alternativa alt3 = new Alternativa(3, "C) A cor amarela representa o risco ergonômico (esforços físicos, levantamento de peso, postura inadequada, produtividade, ritmos excessivos e repetitividade).", false);
        Alternativa alt4 = new Alternativa(4, "D) A cor marrom representa o risco biológico (vírus, bactérias, fungos, bacilos, parasitas).", false);
        return new Questao(2, R.drawable.sipat, "Com relação ao Mapa de Risco responda o item INCORRETO.", 1, Arrays.asList(alt1, alt2, alt3, alt4), alt2.getTexto());
    }
    public static Questao getQuestaoTres() {
        Alternativa alt1 = new Alternativa(1, "A) Os coletores na cor azul são destinados a coleta de vidro.", false);
        Alternativa alt2 = new Alternativa(2, "B) Os coletores na cor vermelho são destinados a coleta de resíduos perigosos.", false);
        Alternativa alt3 = new Alternativa(3, "C) Os coletores na cor amarela são destinados a coleta de metal.", true);
        Alternativa alt4 = new Alternativa(4, "D) Os coletores na cor verde são destinados a coleta de resíduos orgânicos.", false);
        return new Questao(3, R.drawable.sipat, "No que se refere a coleta seletiva responda o item correto.", 2, Arrays.asList(alt1, alt2, alt3, alt4), alt3.getTexto());
    }

    public static Questao getQuestaoQuatro() {
        Alternativa alt1 = new Alternativa(1, "A) Comissão Interna de Prevenção de Acidentes e Serviço Especializado em Engenharia de Segurança e em Medicina do Trabalho.", true);
        Alternativa alt2 = new Alternativa(2, "B) Comissão Interna de Precaução de Acidentes e Serviço Especializado em Engenharia de Segurança e em Medicina do Trabalho.", false);
        Alternativa alt3 = new Alternativa(3, "C) Comissão Interna de Prevenção de Acidentes e Serviço Específico em Engenharia de Segurança e em Medicina do Trabalho.", false);
        Alternativa alt4 = new Alternativa(4, "D) Comissão Interna de Precaução de Acidentes e Serviço Específico em Engenharia de Segurança e em Medicina do Trabalho.", false);
        return new Questao(4, R.drawable.sipat, "O que significam, respectivamente, as siglas CIPA e SESMT?", 0, Arrays.asList(alt1, alt2, alt3, alt4), alt1.getTexto());
    }

    public static Questao getQuestaoCinco() {
        Alternativa alt1 = new Alternativa(1, "A) Abril Verde (conscientização sobre acidentes de trabalho), Outubro Rosa (conscientização sobre o câncer de mama) e Janeiro Branco (conscientização contra a hepatite).", false);
        Alternativa alt2 = new Alternativa(2, "B) Julho Vermelho (conscientização sobre doação de sangue), Dezembro Laranja (conscientização sobre câncer de pele) e Novembro Azul (conscientização sobre câncer de próstata).", false);
        Alternativa alt3 = new Alternativa(3, "C) Maio Amarelo (conscientização no trânsito), Setembro Amarelo (conscientização contra o suicídio) e Julho Amarelo (conscientização contra a hepatite).", true);
        Alternativa alt4 = new Alternativa(4, "D) Novembro dourado (conscientização sobre câncer infanto-juvenil), Agosto Azul (conscientização sobre a prevenção da saúde de homens) e Abril Branco (conscientização sobre acidentes de trabalho).", false);
        return new Questao(5, R.drawable.sipat, "Assinale o item correto quanto aos meses e as cores das campanhas educativas.", 2, Arrays.asList(alt1, alt2, alt3, alt4), alt3.getTexto());
    }
}
