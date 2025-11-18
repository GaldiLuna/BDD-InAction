describe FrequentFlyer do // Você está falando sobre o objeto de domínio Frequent Flyer.
    it 'should initially have Bronze status' do // O que ele deve fazer ou ter?
        frequentFlyer = FrequentFlyer.new // Cria um novo objeto Frequent Flyer.
        expect(frequentFlyer.status).to eq('BRONZE') // Você espera que o status seja Bronze.
    end
end

describe FrequentFlyer do
    context 'when the frequent flyer account is first created' do // (B) Agrupa os requisitos por contexto.
        it 'should initially have Bronze status' do
            frequentFlyer = FrequentFlyer.new
            expect(frequentFlyer.status).to eq('BRONZE')
        end
    end

    context 'when a new member starts to fly with Flying High' do // (B) Agrupa os requisitos por contexto.
        it 'should earn points for each flight' do
            frequentFlyer = FrequentFlyer.new
            frequentFlyer.earn_status_points(100)
            expect(frequentFlyer.status_points).to eq(100)
        end // (B) Contextos podem conter vários requisitos relacionados.

        it 'should upgrade member status when enough points are earned' do
            frequentFlyer = FrequentFlyer.new
            frequentFlyer.earn_status_points(100).earn_status_points(200)
            expect(frequentFlyer.status).to eq('SILVER')
        end
    end
end