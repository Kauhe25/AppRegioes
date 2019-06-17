package com.example.regiao;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvRegiaoSelec;
    private TextView tvEstadoSelec;

    private String[] regioes;
    private String[][] estados;

    private ConstraintLayout constraintLayout;
    private LinearLayout linearLayout;

    int contadorRegioes = -1;
    int contadorEstados = 0;

    boolean validador = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.layout);
        tvEstadoSelec = (TextView) findViewById(R.id.tvEstadoSelec);
        tvRegiaoSelec = (TextView) findViewById(R.id.tvRegiaoSelec);

        //ivDireita = (ImageView) findViewById(R.id.ivDireita);
        //ivEsquerda = (ImageView) findViewById(R.id.ivEsquerda);


        regioes = new String[] {"Norte","Nordeste","Centro-Oeste","Sudeste","Sul"};

        estados = new String[][]{
                {"Acre","Amazonas","Amapá", "Pará", "Rondônia", "Roraima","Tocantins"}, //norte
                { "Alagoas", "Bahia", "Ceará", "Maranhão", "Piauí", "Pernambuco", "Paraiba",
                  "Rio Grande do Norte", "Sergipe"},//nordeste
                {"Distrito Federal","Goiás","Mato Grosso do Sul", "Mato Grosso"}, //centro-oeste
                {"Espirito Santo", "Minas Gerais","Rio de Janeiro","São Paulo"},//sudeste
                {"Rio Grande do Sul","Santa Catarina", "Paraná"}}; //Sul e finaliza


        linearLayout.setOnTouchListener( new OnSwipeTouchListener(this){

            @Override
            public void onSwipeRight() {


                super.onSwipeRight();

                if(validador) {
                    contadorEstados++;
                    if (contadorEstados < 0) {
                        contadorEstados = 4;
                    } else if (contadorEstados > 4) {
                        contadorEstados = 0;
                    }
                    tvEstadoSelec.setText(estados[contadorRegioes][contadorEstados]);
                }

                else{
                    Toast.makeText(getBaseContext(), "Arraste primeiro para Cima/Baixo para Escolher a Região",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if(validador) {

                    contadorEstados--;
                    if (contadorEstados < 0) {
                        contadorEstados = 4;
                    } else if (contadorEstados > 4) {
                        contadorEstados = 0;
                    }
                    tvEstadoSelec.setText(estados[contadorRegioes][contadorEstados]);
                }

                else{
                    Toast.makeText(getBaseContext(), "Arraste primeiro para Cima/Baixo para Escolher a Região",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();

                validador = true;
                contadorRegioes++;

                if(contadorRegioes < 0){ contadorRegioes = 4; }
                else if (contadorRegioes > 4){ contadorRegioes = 0; }

                tvRegiaoSelec.setText( regioes[contadorRegioes]);
                tvEstadoSelec.setText(estados[contadorRegioes][0]);
                contadorEstados = 0;
            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();

                validador = true;
                contadorRegioes--;

                if(contadorRegioes < 0){ contadorRegioes = 4; }
                else if (contadorRegioes > 4){ contadorRegioes = 0; }

                tvRegiaoSelec.setText( regioes[contadorRegioes]);
                tvEstadoSelec.setText(estados[contadorRegioes][0]);
                contadorEstados = 0;
            }

        });
    }
}
