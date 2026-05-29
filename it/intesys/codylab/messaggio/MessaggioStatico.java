package it.intesys.codylab.messaggio;

public class MessaggioStatico implements Messaggio {

    private final String nome;

    public MessaggioStatico(String nome) {
        this.nome = nome;
    }

    public String messaggio() {
        return "Benvenuto in My CodyLab " + nome + "!";
    }
}
