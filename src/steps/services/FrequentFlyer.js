describe('Frequent Flyers', function() {
    var frequentFlyer; // Este é o objeto sob teste.

    beforeEach(function() {
        frequentFlyer = require('../lib/frequent_flyer');
    }); // Faça isso antes de cada especificação.

    describe("Managing Frequent Flyer statuses", function() { // (C) Requisitos aninhados.
        it("should initially have Bronze status", function() { // (D) O 'it' marca uma especificação.
            expect(frequentFlyer.getStatus()).toBe('Bronze'); // (E) O Jasmine usa “expect” para descrever resultados esperados.
        });
        it("should initially have no status points", function() {
            expect(frequentFlyer.getStatusPoints()).toBe(0);
        });
    });

    describe("Cumulating Frequent Flyer points", function() { // (B) Um conjunto de especificações relacionadas.
        it('should earn points for each flight', function() {
            frequentFlyer.earnStatusPoints(100);
            frequentFlyer.earnStatusPoints(50);
            expect(frequentFlyer.getStatusPoints()).toBe(150);
        });
        it('should upgrade member to next status level when enough points are earned', function() {
            frequentFlyer.earnStatusPoints(300);
            expect(frequentFlyer.getStatus()).toBe('Silver');
        });
    });
});