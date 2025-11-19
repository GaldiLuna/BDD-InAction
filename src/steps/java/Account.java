package steps.java;

public class Account {
    private final String owner; // Você tem um campo para cada coluna da tabela.
    private final int pointBalance;
    private final int statusPoints; // Esta classe representa linhas nas tabelas de cenário.

    public Account(String owner, int pointBalance, int statusPoints) {
        this.owner = owner;
        this.pointBalance = pointBalance;
        this.statusPoints = statusPoints;
    }
    public String getOwner() { return owner; }
    public int getPointBalance() { return pointBalance; }
    public int getStatusPoints() { return statusPoints; }
    // A tabela é somente leitura, então você precisa apenas de getters, e não de setters, para acessar os valores do campo.

    public static Builder forMember(String owner) {
        return new Builder(owner);
    }

    public static class Builder {
        private final String owner;
        private int points;
        private int statusPoints;

        Builder(String owner) { this.owner = owner; }

        public Builder withPointBalance(int points) { this.points = points; return this; }
        public Builder withStatusPoints(int statusPoints) { this.statusPoints = statusPoints; return this; }
        public Account build() { return new Account(owner, points, statusPoints); }

        // convenience: return built account
        public Account toAccount() { return build(); }

        @Override
        public String toString() { return "Account(" + owner + "," + points + "," + statusPoints + ")"; }
    }
}
