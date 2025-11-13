this.Given(/^the flying distance between Sydney and Melbourne is (\d+) km$/,
function(arg1, callback) {
    // express the regexp above with the code you wish you had
    callback.pending();
});

this.Given(/^I am a standard Frequent Flyer member$/, function(callback) {
    // express the regexp above with the code you wish you had
    callback.pending();
});
...

var stepDefinitionWrapper = function () {
    var should = require('chai').should() // Chai é uma biblioteca de asserção BDD para JavaScript.
    var distance_travelled, traveller_status; // Variáveis que serão compartilhadas entre os passos.

    var calculate_travel_points = function(status, distance) {
        return distance / 2;
    }

    this.Given(/^the distance between (.*) and (.*) is (\d+) km$/,
    function(start, end, distance, callback) {
        distance_travelled = distance;

        this.Given(/^I am a (\w+) Frequent Flyer member$/, // Chama callback() para dizer ao Cucumber-JS que pode prosseguir para o próximo passo.
        function(status, callback) {
            traveller_status = status;
            callback();
        });

        this.When(/^I fly from (.*) to (.*)$/,
        function(start, end, callback) {
            callback();
        });

        this.Then(/^I should earn (\d+) travel points$/,
        function(expected_travel_points, callback) {
            var calculatedTravelPoints = calculate_points(traveller_status,
                                                         distance_travelled);

            // Use a biblioteca de asserção Chai para expressar suas expectativas.
            calculatedTravelPoints.should.equal(parseInt(expected_travel_points));
            callback();
        });
    }

    module.exports = stepDefinitionWrapper;
}