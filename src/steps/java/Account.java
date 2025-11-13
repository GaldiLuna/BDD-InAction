package steps.java;

public class Account {
    private final String owner; // Você tem um campo para cada coluna da tabela.
    private final int points;
    private final int statusPoints; // Esta classe representa linhas nas tabelas de cenário.

    public Account(String owner, int points, int statusPoints) {
        this.owner = owner;
        this.points = points; // O Cucumber sabe como instanciar um objeto Account para cada linha da tabela
        this.statusPoints = statusPoints; // usando os parâmetros do construtor.
    }
    public String getOwner() { return owner; }
    public int getPoints() { return points; }
    public int getStatusPoints() { return statusPoints; }
    // A tabela é somente leitura, então você precisa apenas de getters, e não de setters, para acessar os valores do campo.
}
