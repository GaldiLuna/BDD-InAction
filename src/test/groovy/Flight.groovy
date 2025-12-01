package test.groovy

class Flight {
    String from
    String to
    String number

    String flightNumber
    Map departure // Usamos Map para simular o objeto com a propriedade .name
    Map destination
    String time

    // Construtor Groovy que aceita um mapa de propriedades (simplifica a inicialização)
    Flight(Map properties) {
        this.flightNumber = properties.flightNumber
        this.departure = properties.departure
        this.destination = properties.destination
        this.time = properties.time
    }
}
