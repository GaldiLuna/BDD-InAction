TimetableService timetableService = Mock(TimetableService) // Criar um serviço de horário “fingido”.

def "should calculate the correct arrival time"() {
    given:
    def westernLine =
            Line.named("Western").departingFrom("Emu Plains") // (B) Definir uma linha de trem.

    // O serviço de horário fingido retorna esta linha de trem quando você pergunta quais linhas
    // passam por Parramatta e Town Hall.
    timetableService.findLinesThrough("Parramatta",
            "Town Hall") >> [westernLine]

    // (C) O serviço de horário fingido também lhe dá os horários de chegada corretos em Parramatta.
    timetableService.findArrivalTimes(westernLine, "Parramatta") >>
            [at(7,58), at(8,00), at(8,02), at(8,11), at(8,14), at(8,21)]

    when:
    def proposedTrainTimes =
            itineraryService.findNextDepartures("Parramatta",
                    "Town Hall",
                    at(8,00));

    then:
    proposedTrainTimes == [at(8,02), at(8,11), at(8,14)]
}