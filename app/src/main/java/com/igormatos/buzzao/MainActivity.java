package com.igormatos.buzzao;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;





public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button buttonStart;
    // private EditText inputLinha;
    private EditText inputVeiculo;

    private AutoCompleteTextView linha;
    String[] linhasRMR={

            "1940 - Abreu e Lima / Olinda ",
            "1933 - Abreu e Lima / TI Pelópidas ",
            "33 - Aeroporto ",
            "36 - Aeroporto (Bacurau) ",
            "42 - Aeroporto (Opcional) ",
            "43 - Aeroporto / Tacaruna (Derby) ",
            "721 - Água Fria ",
            "846 - Águas Compridas (Bacurau) ",
            "842 - Águas Compridas / TI  Xambá ",
            "831 - Aguazinha / TI Xambá ",
            "1929 - Alameda Paulista / Rio Doce (Maranguape I) ",
            "843 - Alto da Bondade / TI Xambá ",
            "893 - Alto da Conquista / TI Xambá ",
            "894 - Alto da Sucupira (Córrego do Abacaxi) / TI Xambá ",
            "604 - Alto do Burity / Macaxeira ",
            "892 - Alto do Cajueiro / TI  Xambá ",
            "746 - Alto do Capitão ",
            "511 - Alto do Mandu ",
            "711 - Alto do Pascoal ",
            "632 - Alto do Refúgio ",
            "895 - Alto do Sol Nascente / TI Xambá ",
            "244 - Alto do Vento / TI Cavaleiro ",
            "145 - Alto Dois Carneiros (Bacurau) ",
            "142 - Alto Dois Carneiros / TI Tancredo Neves ",
            "714 - Alto José Bonifácio (Av. Norte) ",
            "745 - Alto José Bonifácio (Bacurau) ",
            "743 - Alto José Bonifácio (João de Barros) ",
            "611 - Alto José do Pinho ",
            "847 - Alto Nova Olinda / TI Xambá ",
            "521 - Alto Santa Isabel ",
            "712 - Alto Santa Terezinha ",
            "715 - Alto Santa Terezinha (Bacurau) ",
            "726 - Alto Santa Terezinha (Conde da Boa Vista) ",
            "780 - Alto Santa Terezinha / Derby ",
            "621 - Alto Treze de Maio ",
            "1971 - Amparo ",
            "1975 - Amparo (Bacurau) ",
            "518 - Apipucos / RioMar (Opcional) ",
            "2463 - Araçoiaba / TI Camaragibe ",
            "1903 - Araçoiaba / TI Igarassu ",
            "1934 - Arthur Lundgren I / TI PE-15 ",
            "948 - Arthur Lundgren II / Macaxeira ",
            "1996 - Arthur Lundgren II / Rio Doce (Paratibe) ",
            "1941 - Arthur Lundgren II / TI PE-15 ",
            "413 - Avenida do Forte ",
            "2489 - Bairro dos Estados / TI Caxangá ",
            "425 - Barbalho (Detran) ",
            "108 - Barro / CEASA ",
            "207 - Barro / Macaxeira (BR-101) ",
            "202 - Barro / Macaxeira (Várzea) ",
            "206 - Barro / Prazeres ",
            "731 - Beberibe (Espinheiro) ",
            "700 - Beberibe / Afogados ",
            "730 - Beberibe / Av. Norte ",
            "710 - Beberibe / Derby ",
            "713 - Bomba do Hemetério ",
            "290 - Bonança / Jaboatão ",
            "315 - Bongi ",
            "311 - Bongi (Afogados) ",
            "14 - Brasília (Conde da Boa Vista) ",
            "18 - Brasília Teimosa ",
            "433 - Brasilit ",
            "624 - Brejo ",
            "626 - Brejo (Bacurau) ",
            "161 - Brigadeiro Ivo Borges / TI Aeroporto ",
            "1972 - Bultrins ",
            "184 - Cabo (Bacurau) ",
            "181 - Cabo (Cohab) / TI Cajueiro Seco ",
            "197 - Cabo / Ipojuca ",
            "189 - Cabo / Porto de Suape ",
            "1949 - Caetés / Centro de Paulista (Paratibe) ",
            "901 - Caetés / Macaxeira ",
            "1957 - Caetés I (Bacurau) ",
            "1912 - Caetés I / TI Pelópidas ",
            "1917 - Caetés II / TI Pelópidas ",
            "1998 - Caetés III / TI Pelópidas ",
            "852 - Caixa D'Água / TI Xambá ",
            "723 - Cajueiro ",
            "199 - Camela / TI Cabo ",
            "722 - Campina do Barreto ",
            "71 - Candeias ",
            "73 - Candeias (Bacurau) ",
            "72 - Candeias (Opcional) ",
            "70 - Candeias / Shopping RioMar ",
            "20 - Candeias / TI Tancredo Neves ",
            "533 - Casa Amarela (Bacurau) ",
            "532 - Casa Amarela (Cabugá) ",
            "516 - Casa Amarela (Nova Torre) ",
            "531 - Casa Amarela (Rosa e Silva) ",
            "330 - Casa Amarela / CDU (TRT) ",
            "1973 - Casa Caiada ",
            "232 - Cavaleiro ",
            "233 - Cavaleiro (Bacurau) ",
            "240 - Cavaleiro / CEASA ",
            "432 - CDU (Várzea) ",
            "435 - CDU (Várzea) (Bacurau) ",
            "40 - CDU / Boa Viagem / Caxangá ",
            "440 - CDU / Caxangá / Boa Viagem ",
            "424 - CDU / Torrões (Via San Martin) ",
            "2467 - Chã de Cruz / TI Camaragibe ",
            "724 - Chão de Estrelas ",
            "159 - Charneca / TI Cabo ",
            "896 - Cidade Tabajara / Ouro Preto ",
            "1923 - Cidade Tabajara / TI PE-15 ",
            "431 - Cidade Universitária (TRT) ",
            "107 - Circular (Cabugá / Prefeitura) ",
            "163 - Circular (Cajueiro Seco) ",
            "100 - Circular (Conde da Boa Vista / Prefeitura) ",
            "101 - Circular (Conde da Boa Vista / Rua do Sol) ",
            "104 - Circular (IMIP) ",
            "117 - Circular (Prefeitura / Cabugá) ",
            "116 - Circular (Príncipe) ",
            "119 - Circular / TI Cabo ",
            "272 - Colônia / Jaboatão ",
            "37 - Comportas / TI Cajueiro Seco ",
            "1994 - Conjunto Beira Mar ",
            "1982 - Conjunto Beira Mar / Derby ",
            "69 - Conjunto Catamarã ",
            "172 - Conjunto Marcos Freire (Bacurau) ",
            "1993 - Conjunto Praia do Janga ",
            "209 - Coqueiral / Barro ",
            "513 - Córrego da Areia ",
            "125 - Córrego da Gameleira / TI Tancredo Neves ",
            "718 - Córrego do Euclides / Derby ",
            "517 - Córrego do Inácio ",
            "643 - Córrego do Jenipapo (Bacurau) ",
            "1958 - Costa Azul ",
            "341 - Curado I ",
            "320 - Curado I / Werneck (Via Totó) ",
            "352 - Curado II (Bacurau) ",
            "303 - Curado II / Caxangá (BR-232) ",
            "351 - Curado II / TI TIP ",
            "363 - Curado IV (Av. 01) / TI TIP ",
            "362 - Curado IV (Bacurau) ",
            "361 - Curado IV (Rua 14) / TI TIP ",
            "348 - Curado V / TI TIP ",
            "34 - Curcurana / TI Cajueiro Seco ",
            "1988 - Desterro / TI Pelópidas ",
            "245 - Dois Carneiros / TI Cavaleiro ",
            "523 - Dois Irmãos (Bacurau) ",
            "522 - Dois Irmãos (Rui Barbosa) ",
            "741 - Dois Unidos ",
            "744 - Dois Unidos (Bacurau) ",
            "800 - Dois Unidos / Afogados ",
            "760 - Dois Unidos / Derby ",
            "423 - Engenho do Meio ",
            "1955 - Engenho Maranguape / TI Pelópidas ",
            "1950 - Engenho Maranguape / Varadouro ",
            "411 - Estrada dos Remédios ",
            "160 - Gaibu / Barra de Jangada - Via Paiva (Opcional) ",
            "157 - Gaibu / TI Cabo ",
            "158 - Garapu / TI Cabo ",
            "642 - Guabiraba (Córrego do Jenipapo) ",
            "640 - Guabiraba / Derby ",
            "110 - Ibura / IPSEP ",
            "102 - Ibura / Santa Luzia ",
            "1956 - Igarassu (Bacurau) ",
            "1980 - Igarassu / Cidade Tabajara ",
            "1904 - Igarassu / Nova Cruz ",
            "1968 - Ilha de Itamaracá / TI Igarassu ",
            "171 - Integração Muribeca / TI Cajueiro Seco ",
            "198 - Ipojuca / TI Cabo ",
            "120 - IPSEP / Shopping Recife ",
            "1969 - Itapissuma / TI Igarassu ",
            "254 - Jaboatão (Bacurau) ",
            "200 - Jaboatão (Parador) ",
            "220 - Jaboatão / TI Cavaleiro ",
            "1945 - Jaguarana (Alameda) / TI Pelópidas ",
            "1974 - Jardim Atlântico ",
            "13 - Jardim Beira Rio (Pina) ",
            "827 - Jardim Brasil (Bacurau) ",
            "825 - Jardim Brasil / Joana Bezerra ",
            "884 - Jardim Brasil / Rio Doce ",
            "822 - Jardim Brasil I (Cruz Cabugá) ",
            "821 - Jardim Brasil I (Estrada de Belém) ",
            "824 - Jardim Brasil II (Cruz Cabugá) ",
            "823 - Jardim Brasil II (Estrada de Belém) ",
            "151 - Jardim Jordão / TI Aeroporto ",
            "141 - Jardim Monte Verde / TI Tancredo Neves ",
            "1932 - Jardim Paulista Alto / TI PE-15 ",
            "1931 - Jardim Paulista Baixo / TI PE-15 ",
            "62 - Jardim Piedade ",
            "63 - Jardim Piedade (Bacurau) ",
            "2442 - Jardim Primavera (Vale das Pedreiras) / TI Caxangá ",
            "212 - Jardim São Paulo ",
            "321 - Jardim São Paulo (Abdias de Carvalho) ",
            "322 - Jardim São Paulo (Bacurau) ",
            "324 - Jardim São Paulo (Piracicaba) ",
            "2464 - Jardim Teresópolis / TI Caxangá ",
            "222 - Jardim Uchôa ",
            "80 - Joana Bezerra / Boa Viagem ",
            "154 - Jordão (Bacurau) ",
            "153 - Jordão Alto / TI Aeroporto ",
            "155 - Jordão Baixo / Boa Viagem ",
            "152 - Jordão Baixo / TI Aeroporto ",
            "717 - José Amarino dos Reis ",
            "134 - Lagoa Encantada / TI Tancredo Neves ",
            "644 - Largo do Maracanã ",
            "742 - Linha do Tiro ",
            "274 - Lote 56 / Jaboatão ",
            "1984 - Loteamento Bonfim / TI Pelópidas ",
            "1978 - Loteamento Conceição / Rio Doce (PE-22) ",
            "1944 - Loteamento Conceição / TI Pelópidas ",
            "2473 - Loteamento João Paulo II / TI Camaragibe ",
            "256 - Loteamento Nova Esperança / TI Cavaleiro ",
            "1989 - Loteamento Planalto / TI Pelópidas ",
            "2459 - Loteamento Santos Cosme e Damião ",
            "2462 - Loteamento Santos Cosme e Damião (Bacurau) ",
            "2483 - Loteamento São João e São Paulo / TI Camaragibe ",
            "520 - Macaxeira / Parnamirim ",
            "314 - Mangueira ",
            "1952 - Maranguape I / TI Pelópidas ",
            "1928 - Maranguape II (Bacurau) ",
            "1953 - Maranguape II / TI Pelópidas ",
            "164 - Marcos Freire / TI Cajueiro Seco ",
            "1960 - Maria Farinha / Casa Caiada ",
            "44 - Massangana (Boa Vista) ",
            "2419 - Matriz da Luz / TI Camaragibe ",
            "1936 - Mirueira (Bacurau) ",
            "902 - Mirueira / Macaxeira ",
            "1943 - Mirueira / TI PE-15 ",
            "422 - Monsenhor Fabrício ",
            "427 - Monsenhor Fabrício (Bacurau) ",
            "258 - Moreno (Bacurau) ",
            "201 - Moreno / Jaboatão ",
            "612 - Morro da Conceição ",
            "613 - Morro da Conceição (Bacurau) ",
            "2420 - Muribara / TI Camaragibe ",
            "162 - Muribeca / TI Cajueiro Seco ",
            "170 - Muribeca dos Guararapes (Bacurau) ",
            "165 - Muribeca dos Guararapes / TI Cajueiro Seco ",
            "312 - Mustardinha ",
            "1937 - Nobre / TI Pelópidas ",
            "208 - Nossa Senhora da Conceição / Jaboatão ",
            "196 - Nossa Senhora do Ó / TI Cabo ",
            "515 - Nova Descoberta (Bacurau) ",
            "631 - Nova Descoberta (Cabugá) ",
            "514 - Nova Descoberta (Córrego do Joaquim) ",
            "510 - Nova Descoberta / Derby ",
            "417 - Nova Morada / Caxangá ",
            "841 - Nova Olinda / TI  Xambá ",
            "1927 - Ouro Preto (Bacurau) ",
            "1911 - Ouro Preto (COHAB) ",
            "1921 - Ouro Preto (Jatobá I) ",
            "1926 - Ouro Preto (Jatobá II) ",
            "916 - Ouro Preto / Joana Bezerra ",
            "886 - Ouro Preto / Rio Doce ",
            "242 - Pacheco ",
            "129 - Paiva / TI Cabo ",
            "1935 - Paratibe / TI PE-15 ",
            "106 - Parque Aeronáutica / Santa Luzia ",
            "2402 - Parque Capibaribe / Caxangá ",
            "2492 - Parque Capibaribe / TI Camaragibe ",
            "2410 - Parque Capibaribe / TI TIP ",
            "601 - Parque Residencial Bola na Rede / Macaxeira ",
            "1992 - Pau Amarelo ",
            "1995 - Pau Amarelo (Bacurau) ",
            "1922 - Pau Amarelo / TI Pelópidas ",
            "1990 - Pau Amarelo / Varadouro ",
            "1907 - Paulista / Rio Doce ",
            "914 - PE-15 / Afogados ",
            "50 - PE-15 / Boa Viagem ",
            "2486 - Penedo / TI Camaragibe ",
            "61 - Piedade ",
            "11 - Piedade / Derby ",
            "910 - Piedade / Rio Doce ",
            "10 - Piedade / Shopping Center ",
            "111 - Pinheiros ",
            "1 - Ponte dos Carvalhos / Prazeres (Barra de Jangada) ",
            "183 - Ponte dos Carvalhos / TI Cajueiro Seco ",
            "178 - Porto de Suape / TI Cabo ",
            "118 - Prazeres / Boa Viagem ",
            "255 - Quitandinha / TI Cavaleiro ",
            "191 - Recife / Porto de Galinhas (Nossa Senhora do Ó) ",
            "195 - Recife / Porto de Galinhas (Opcional) ",
            "38 - Residencial Boa Viagem (Bacurau) ",
            "1985 - Rio Doce (Bacurau) ",
            "1966 - Rio Doce (Circular) ",
            "1981 - Rio Doce (Conde da Boa Vista) ",
            "1983 - Rio Doce (Princesa Isabel) ",
            "1987 - Rio Doce (Príncipe) ",
            "2920 - Rio Doce / CDU ",
            "1986 - Rio Doce / Derby ",
            "930 - Rio Doce / Dois Irmãos ",
            "416 - Roda de Fogo ",
            "313 - San Martin (Abdias de Carvalho) ",
            "412 - San Martin (Largo da Paz) ",
            "844 - Santa Casa / TI Xambá ",
            "204 - Santa Luzia / Loteamento Jiquiá ",
            "2476 - Santa Mônica / TI Camaragibe ",
            "2477 - Santa Terezinha / TI Camaragibe ",
            "2478 - Santana / TI Camaragibe ",
            "250 - Santo Aleixo / Jaboatão (Luz) ",
            "251 - Santo Aleixo / Jaboatão (Rios) ",
            "2457 - São Lourenço (Bacurau) ",
            "2491 - São Lourenço / TI Camaragibe ",
            "32 - Setúbal (Conde da Boa Vista) ",
            "39 - Setúbal (Príncipe) ",
            "31 - Shopping Center (Terminal Residencial Boa Viagem) ",
            "53 - Shopping RioMar (Opcional) ",
            "415 - Sítio das Palmeiras ",
            "524 - Sítio dos Pintos (Dois Irmãos) ",
            "527 - Sítio dos Pintos / IMIP (Joana Bezerra) ",
            "812 - Sítio Novo (Av. Norte) ",
            "885 - Sítio Novo / Rio Doce ",
            "2445 - Tabatinga / TI Caxangá ",
            "115 - TI Aeroporto / TI Afogados ",
            "26 - TI Aeroporto / TI Joana Bezerra ",
            "216 - TI Barro / TI Cajueiro Seco ",
            "185 - TI Cabo ",
            "139 - TI Cabo / TI Cajueiro Seco ",
            "166 - TI Cajueiro Seco (Rua do Sol) ",
            "140 - TI Cajueiro Seco / Shopping Recife ",
            "2450 - TI Camaragibe (Centro) - BRT ",
            "2460 - TI Camaragibe (Príncipe) ",
            "2480 - TI Camaragibe / Derby - BRT ",
            "2490 - TI Camaragibe / TI Macaxeira ",
            "437 - TI Caxangá (Centro) ",
            "1918 - TI Igarassu (Circular) ",
            "1967 - TI Igarassu (Dantas Barreto) ",
            "1946 - TI Igarassu (PCR) - BRT ",
            "1964 - TI Igarassu / TI Macaxeira ",
            "1905 - TI Igarassu / TI Pelópidas ",
            "219 - TI Jaboatão (Sancho) / TI TIP ",
            "21 - TI Joana Bezerra / Shopping RioMar ",
            "645 - TI Macaxeira (Av. Norte) ",
            "641 - TI Macaxeira / Encruzilhada ",
            "1915 - TI PE-15 (Dantas Barreto) - BRT ",
            "1900 - TI PE-15 (PCR) ",
            "1913 - TI PE-15 / TI Joana Bezerra ",
            "1977 - TI Pelópidas (Conde da Boa Vista) ",
            "1979 - TI Pelópidas (Dantas Barreto) - BRT ",
            "1976 - TI Pelópidas (PCR) - BRT ",
            "1909 - TI Pelópidas / TI Joana Bezerra ",
            "1906 - TI Pelópidas / TI Macaxeira ",
            "1970 - TI Pelópidas / TI PE-15 ",
            "24 - TI Tancredo Neves (Circular Boa Viagem) ",
            "168 - TI Tancredo Neves (Conde da Boa Vista) ",
            "167 - TI Tancredo Neves (IMIP) ",
            "193 - TI Tancredo Neves (Príncipe) ",
            "23 - TI Tancredo Neves / TI Aeroporto ",
            "60 - TI Tancredo Neves / TI Macaxeira ",
            "346 - TI TIP (Conde da Boa Vista) ",
            "347 - TI TIP (Derby) ",
            "302 - TI TIP / Caxangá ",
            "49 - TI TIP / Moreno (BR-232) ",
            "370 - TI TIP / TI Aeroporto ",
            "820 - TI Xambá (Cabugá) ",
            "860 - TI Xambá (Príncipe) ",
            "810 - TI Xambá / Encruzilhada ",
            "882 - TI Xambá / Rio Doce (Carlos de Lima Cavalcanti) ",
            "881 - TI Xambá / Rio Doce (Getúlio Vargas) ",
            "883 - TI Xambá / Rio Doce (II Perimetral) ",
            "861 - TI Xambá / TI Joana Bezerra ",
            "870 - TI Xambá / TI Largo da Paz ",
            "2475 - Timbi / TI Camaragibe ",
            "418 - Tiúma / Camaragibe (VPP) ",
            "2493 - Tiúma / TI Camaragibe ",
            "414 - Torre ",
            "421 - Torrões ",
            "426 - Torrões (Bacurau) ",
            "332 - Totó (Abdias de Carvalho) ",
            "333 - Totó (Bacurau) ",
            "331 - Totó (Jardim Planalto) ",
            "360 - Totó / Boa Viagem ",
            "133 - Três Carneiros / TI Tancredo Neves ",
            "123 - Três Carneiros Baixo / TI Tancredo Neves ",
            "131 - UR-02 (Bacurau) ",
            "132 - UR-02 (Ibura) / TI Tancredo Neves ",
            "128 - UR-03 / Barro (Milagres) ",
            "126 - UR-03 / TI Tancredo Neves ",
            "144 - UR-04 / TI Tancredo Neves ",
            "205 - UR-05 / Barro (BR-101) ",
            "136 - UR-05 / TI Tancredo Neves ",
            "143 - UR-06 / TI Tancredo Neves ",
            "2446 - UR-07 ",
            "135 - UR-10 / TI Tancredo Neves ",
            "146 - UR-11 (Bacurau) ",
            "103 - UR-11 / Barro ",
            "190 - UR-11 / IPSEP ",
            "137 - UR-11 / TI Tancredo Neves ",
            "2487 - Várzea Fria / TI Camaragibe ",
            "622 - Vasco da Gama (Cabugá) ",
            "623 - Vasco da Gama (João de Barros) ",
            "680 - Vasco da Gama / Afogados ",
            "630 - Vasco da Gama / Derby ",
            "2466 - Vera Cruz / TI Camaragibe ",
            "221 - Vila Cardeal e Silva ",
            "2488 - Vila da Fábrica / TI Caxangá ",
            "121 - Vila da Sudene ",
            "122 - Vila do IPSEP ",
            "124 - Vila do SESI / TI Tancredo Neves "

    };



    public static final String EXTRA_LINHA = "EXTRA_LINHA";
    public static final String EXTRA_VEICULO = "EXTRA_VEICULO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button)findViewById(R.id.btnMonitorar);
        buttonStart.setOnClickListener(this);

        linha = (AutoCompleteTextView)findViewById(R.id.editLinha);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,linhasRMR);

        linha.setAdapter(adapter);
        linha.setThreshold(1);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acao_config) {

                return true;
        }else if(id == R.id.acao_sobre){
                Intent it = new Intent(this, SobreActivity.class);
                startActivity(it);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==buttonStart.getId()){ //se o bot�o clicado foi o do start
            //inputLinha = (EditText)findViewById(R.id.editLinha);
            linha = (AutoCompleteTextView)findViewById(R.id.editLinha);
            inputVeiculo = (EditText)findViewById(R.id.editVeiculo);

            Intent intent = new Intent(MonitoramentoActivity.ACTION_MONITORAR);
            intent.addCategory((MonitoramentoActivity.CATEGORY_MONITORAR));

            //String linha = inputLinha.getText().toString();
            String linhaTxt = linha.getText().toString();
            String veiculoTxt = inputVeiculo.getText().toString();

            intent.putExtra(MonitoramentoActivity.EXTRA_LINHA, linhaTxt);
            intent.putExtra(MonitoramentoActivity.EXTRA_VEICULO, veiculoTxt);

            if(linhaTxt.equalsIgnoreCase("")||veiculoTxt.equalsIgnoreCase("")){
                Toast.makeText(this, "Informe a linha e o veiculo", Toast.LENGTH_SHORT).show();
            }else {
                startActivity(intent);
            }

        }
    }






}
