package br.com.contmatic.telefone;

public enum Ddd {

                 SP11(11, "São Paulo, São Paulo/Jundiaí/Itu//Bragança Paulista"),

                 SP12(12, "São Paulo, São José dos Campos/Taubaté/Vale do Paraíba"),

                 SP13(13, "São Paulo, Santos/São Vicente/Baixada Santista/Vale do Ribeira"),

                 SP14(14, "São Paulo, Bauru/Marília/Jaú/Botucatu"),

                 SP15(15, "São Paulo, Sorocaba/Itapetininga/Itapeva"),

                 SP16(16, "São Paulo, Ribeirão Preto/Franca/São Carlos/Araraquara"),

                 SP17(17, "São Paulo, São José do Rio Preto/Catanduva/Barretos/Votuporanga"),

                 SP18(18, "São Paulo, Presidente Prudente/Araçatuba/Birigui/Assis"),

                 SP19(19, "São Paulo, Campinas/Piracicaba/Limeira/Americana"),

                 RJ21(21, "Rio de Janeiro, Rio de Janeiro, Região Metropolitana e Teresópolis"),

                 RJ22(22, "Rio de Janeiro, Campos dos Goytacazes/Nova Friburgo/Macaé/Cabo Frio"),

                 RJ24(24, "Rio de Janeiro, Petrópolis/Volta Redonda/Angra dos Reis"),

                 ES27(27, "Espírito Santo, Vitória, Região Metropolitana, Norte e Noroeste do Estado do Espírito Santo."),

                 ES28(28, "Espírito Santo, Mesorregião Sul do Espírito Santo"),

                 MG31(31, "Minas Gerais, Belo Horizonte, Região Metropolitana e Vale do Aço"),

                 MG32(32, "Minas Gerais, Juiz de Fora/São João Del Rei"),

                 MG33(33, "Minas Gerais, Governador Valadares/Teófilo Otoni/Caratinga/Manhuaçu"),

                 MG34(34, "Minas Gerais, Uberlândia e Triângulo Mineiro"),

                 MG35(35, "Minas Gerais, Poços de Caldas/Pouso Alegre/Varginha"),

                 MG37(37, "Minas Gerais, Divinópolis/Itaúna"),

                 MG38(38, "Minas Gerais, Montes Claros/Diamantina/Noroeste de Minas"),

                 PR41(41, "Paraná, Curitiba e Região Metropolitana"),

                 PR42(42, "Paraná, Ponta Grossa/Guarapuava"),

                 PR43(43, "Paraná, Londrina/Apucarana"),

                 PR44(44, "Paraná, Maringá/Campo Mourão/Umuarama"),

                 PR45(45, "Paraná, Cascavel/Foz do Iguaçu"),

                 PR46(46, "Paraná, Francisco Beltrão/Pato Branco"),

                 SC47(47, "Santa Catarina, Joinville/Blumenau/Itajaí/Balneário Camboriú"),

                 SC48(48, "Santa Catarina, Florianópolis e Região Metropolitana/Criciúma/Tubarão"),

                 SC49(49, "Santa Catarina, Chapecó/Lages/Caçador"),

                 RS51(51, "Rio Grande do Sul, Porto Alegre e Região Metropolitana/Santa Cruz do Sul/Litoral Norte"),

                 RS53(53, "Rio Grande do Sul, Pelotas/Rio Grande"),

                 RS54(54, "Rio Grande do Sul, Caxias do Sul/Passo Fundo"),

                 RS55(55, "Rio Grande do Sul, Santa Maria/Uruguaiana/Santana do Livramento/Santo Ângelo"),

                 DFGO61(61, "Distrito Federal/Goiás, Abrangência em todo o Distrito Federal e municípios goianos"),

                 GO62(62, "Goiás, Goiânia e Região Metropolitana/Anápolis/Niquelândia/Porangatu"),

                 TO63(63, "Tocantins, Abrangência em todo o estado"),

                 GO64(64, "Goiás, Rio Verde/Itumbiara/Caldas Novas/Catalão"),

                 MT65(65, "Mato Grosso, Cuiabá e Região Metropolitana"),

                 MT66(66, "Mato Grosso, Rondonópolis/Sinop"),

                 MT67(67, "Mato Grosso do Sul, Abrangência em todo o estado"),

                 AC68(68, "Acre, Abrangência em todo o estado"),

                 RO69(69, "Rondônia, Abrangência em todo o estado"),

                 BA71(71, "Bahia, Salvador e Região Metropolitana"),

                 BA73(73, "Bahia, Itabuna/Ilhéus"),

                 BA74(74, "Bahia, Juazeiro"),

                 BA75(75, "Bahia, Feira de Santana/Alagoinhas"),

                 BA77(77, "Bahia, Vitória da Conquista/Barreiras"),

                 SE79(79, "Sergipe, Abrangência em todo o estado"),

                 PE81(81, "Pernambuco, Recife e Região Metropolitana/Caruaru"),

                 AL82(82, "Alagoas, Abrangência em todo o estado"),

                 PB83(83, "Paraíba, Abrangência em todo o estado"),

                 RN84(84, "Rio Grande do Norte, Abrangência em todo o estado"),

                 CE85(85, "Ceará, Fortaleza e Região Metropolitana"),

                 PI86(86, "Piauí, Teresina e Região Metropolitana/Parnaíba"),

                 PE87(87, "Pernambuco, Petrolina/Garanhuns/Serra Talhada/Salgueiro"),

                 CE88(88, "Ceará, Juazeiro do Norte/Sobral"),

                 PI89(89, "Piauí, Picos/Floriano"),

                 PA91(91, "Pará, Belém/Região Metropolitana"),

                 AM92(92, "Amazonas, Manaus/Região Metropolitana/Parintins"),

                 PA93(93, "Pará, Santarém/Altamira"),

                 PA94(94, "Pará, Marabá"),

                 RO95(95, "Roraima, Abrangência em todo o estado"),

                 AP96(96, "Amapá, Abrangência em todo o estado"),

                 AM97(97, "Amazonas, Abrangência no interior do estado"),

                 MA98(98, "Maranhão, São Luís e Região Metropolitana"),

                 MA99(99, "Maranhão, Imperatriz/Caxias/Codó");

    private String regiao;
    private int ddd;

    private Ddd(int ddd, String regiao) {
        this.ddd = ddd;
        this.regiao = regiao;
    }

    public String getRegiao() {
        return regiao;
    }

    public int getDdd() {
        return ddd;
    }

    @Override
    public String toString() {
        return name();
    }

}
