package br.edu.unicatolicaquixada.cipa.model;

/**
 * Created by lapis on 19/05/17.
 */
public class Alternativa {
    private int numero;
    private String texto;
    private Boolean isCorreta;

    public Alternativa() {
    }

    public Alternativa(int numero, String texto, Boolean isCorreta) {
        this.numero = numero;
        this.texto = texto;
        this.isCorreta = isCorreta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean isCorreta() {
        return isCorreta;
    }

    public void setCorreta(Boolean correta) {
        isCorreta = correta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alternativa that = (Alternativa) o;

        if (numero != that.numero) return false;
        return texto.equals(that.texto);

    }

    @Override
    public int hashCode() {
        int result = numero;
        result = 31 * result + texto.hashCode();
        return result;
    }
}
