package br.edu.unicatolicaquixada.cipa.model;

import java.util.List;

/**
 * Created by lapis on 19/05/17.
 */
public class Questao {
    private int numero;
    private int idImagem;
    private String enunciado;
    private List<Alternativa> alternativas;
    private int opcaoCorreta;

    public String getCorreta() {
        return correta;
    }

    public void setCorreta(String correta) {
        this.correta = correta;
    }

    private String correta;


    public Questao() {
    }

    public Questao(int numero, int idImagem, String enunciado, int opcaoCorreta, List<Alternativa> alternativas, String correta) {
        this.numero = numero;
        this.idImagem = idImagem;
        this.enunciado = enunciado;
        this.opcaoCorreta = opcaoCorreta;
        this.alternativas = alternativas;
        this.correta =correta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public Integer getAlternativaSelecionada(String alternativa) {
        for (Alternativa alt : alternativas) {
            if(alt.getTexto().equals(alternativa)) {
                return alt.getNumero();
            }
        }
        return null;
    }

    public int getOpcaoCorreta() {
        return opcaoCorreta;
    }

    public void setOpcaoCorreta(int opcaoCorreta) {
        this.opcaoCorreta = opcaoCorreta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Questao questao = (Questao) o;

        return numero == questao.numero;

    }

    @Override
    public int hashCode() {
        return numero;
    }
}
