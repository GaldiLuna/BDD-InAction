describe("Displaying flight status", function () {
    var controller, scope, stateParams, flights;

    beforeEach(module('flyinghigh')); // Esta é a aplicação sob teste.

    beforeEach(inject(function($rootScope, $controller, // (B) O AngularJS injeta os serviços de que você precisa.
                                $stateParams, flightService) {
        controller = $controller;
        scope = $rootScope.$new();
        stateParams = $stateParams;
        flights = flightService // (C) O objeto FlightService retorna o status atual de um voo.
    }));

    it('should provide a positive visual queue for on-time flights',
       function () {
        stateParams.flightId = 'FH-101'; // (D) Passa o parâmetro de consulta 'flightId' para o voo sob teste.
        controller('FlightMonitorController', // (E) Invoca o controller.
                   {$scope: scope,
                    $stateParams: stateParams,
                    flightService: flights});

        expect(scope.flight.title).toBe('Flight 101');
        expect(scope.flight.status).toBe('ontime'); // (F) Verifica os resultados esperados.
        expect(scope.flight.statusicon).toBe('icon-thumbs-up');
        expect(scope.flight.eta).toBe('13:45');
    });

    flightService = {
        getStatus: function(flightNumber) {
            switch(flightNumber)
            {
                case 'FH-101': return 'ontime';
                case 'FH-102': return 'delayed';
            }
        }
    };
});