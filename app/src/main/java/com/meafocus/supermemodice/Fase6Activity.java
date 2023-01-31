package com.meafocus.supermemodice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class Fase6Activity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private SeekBar seekBarEscala;
    private TextView valorEscala;
    private static final String ARQUIVO_DADOS = "ArquivoUser";
    private SeekBar seekPos;
    int pts = 3;

    //sons - inicio
    private void playSound1() {
        MediaPlayer mediaPlayer1 = MediaPlayer.create(this, R.raw.beep28);
        mediaPlayer1.start();
    }

    private void playSound2() {
        MediaPlayer mediaPlayer2 = MediaPlayer.create(this, R.raw.beep21);
        mediaPlayer2.start();
    }

    private void playSound3() {
        MediaPlayer mediaPlayer3 = MediaPlayer.create(this, R.raw.glasses);
        mediaPlayer3.start();
    }
    private void playSound4() {
        MediaPlayer mediaPlayer4 = MediaPlayer.create(this, R.raw.glasses);
        mediaPlayer4.start();
    }
    private void playSound5() {
        MediaPlayer mediaPlayer5 = MediaPlayer.create(this, R.raw.glasses);
        mediaPlayer5.start();
    }

    private void playSound6() {
        MediaPlayer mediaPlayer6 = MediaPlayer.create(this, R.raw.roll);
        mediaPlayer6.start();
    }

    private void playSound7() {
        MediaPlayer mediaPlayer7 = MediaPlayer.create(this, R.raw.error);
        mediaPlayer7.start();
    }
    private void playSound8() {
        MediaPlayer mediaPlayer8 = MediaPlayer.create(this, R.raw.error2);
        mediaPlayer8.start();
    }
    //sons - final




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fase6);

        //inicio ads tela cheia
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6944068380993727/5621413024");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                //mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Log.d("ADMOB_DIVESH" , "ADERRROR : " + adError);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });

        //fim ads- tela cheia

//carrega  e exibe os pontos - inicio
        SharedPreferences preferences =  getSharedPreferences(ARQUIVO_DADOS, 0 );
        if (preferences.contains("pontos")){
            String totalPontosUser = preferences.getString("pontos", "");
            TextView pontos = findViewById(R.id.pontosUser);
            pontos.setText(totalPontosUser);
        }else
        {
            String totalPontosUser = "0";
            TextView pontos = findViewById(R.id.pontosUser);
            pontos.setText(totalPontosUser);
        }
//carrega  e exibe os pontos - fim

        //banner admob - Início
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //banner admob - Fim

        // desliga barra e aplicativo em tela cheia - inicio
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // desliga barra e aplicativo em tela cheia - fim


// seekbar - inicio
        seekBarEscala = findViewById(R.id.seekBarEscala);
        valorEscala = findViewById(R.id.valorEscala);
        valorEscala.setText("6");

        seekBarEscala.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorEscala.setText("" +  (progress + 6));
                playSound1();
                //   TextView valorEscala = findViewById(R.id.valorEscala);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
// seekbar - final


    }


    // voltar ao menu incial - inicio
    public void botaoVoltar(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

    }
// voltar ao menu incial - final

    // botão decremento - inicio
    public void botaoMenos(View view){

        int inicioEscala = 6;
        CharSequence capEscala = valorEscala.getText();
        int valorEscalaTela = Integer.parseInt(capEscala.toString());
        if (valorEscalaTela > inicioEscala) {
            seekPos = findViewById(R.id.seekBarEscala);
            seekPos.setProgress(valorEscalaTela - 7) ;
        }else
        {
            playSound7();
        }
    }
// botão decremento - final


    // botão incremento - inicio
    public void botaoMais(View view){

        int fimEscala = 35;
        CharSequence capEscala = valorEscala.getText();
        int valorEscalaTela = Integer.parseInt(capEscala.toString());
        if (valorEscalaTela <= fimEscala) {
            seekPos = findViewById(R.id.seekBarEscala);
            seekPos.setProgress(valorEscalaTela - 5) ;
        }else
        {
            playSound7();
        }
    }
// botão incremento - final


    // botão de atribuir dados aleatorio
    public void botaoClicado (View view){
        TextView concentreSe;
        concentreSe = findViewById(R.id.concentreSe);
        concentreSe.setVisibility(View.VISIBLE );
        Button botaoComecar = findViewById(R.id.botaoComecar);
        botaoComecar.setVisibility(View.INVISIBLE );

        new CountDownTimer(3000, 1000) {

            int contaR = 0;

            public void onTick(long millisUntilFinished) {
                String[] contagemR = {"dadoc3", "dadoc2", "dadoc1"};
                String opcaocontador = contagemR[contaR];
                ImageView dado01 = findViewById(R.id.dado01);
                ImageView dado02 = findViewById(R.id.dado02);
                ImageView dado03 = findViewById(R.id.dado03);
                ImageView dado04 = findViewById(R.id.dado04);
                ImageView dado05 = findViewById(R.id.dado05);
                ImageView dado06 = findViewById(R.id.dado06);
                switch ( opcaocontador ){
                    case "dadoc1" :
                        dado01.setImageResource(R.drawable.dadosc1);
                        dado02.setImageResource(R.drawable.dadosc1);
                        dado03.setImageResource(R.drawable.dadosc1);
                        dado04.setImageResource(R.drawable.dadosc1);
                        dado05.setImageResource(R.drawable.dadosc1);
                        dado06.setImageResource(R.drawable.dadosc1);
                        contaR = contaR + 1;
                        playSound2();
                        break;
                    case "dadoc2" :
                        dado01.setImageResource(R.drawable.dadosc2);
                        dado02.setImageResource(R.drawable.dadosc2);
                        dado03.setImageResource(R.drawable.dadosc2);
                        dado04.setImageResource(R.drawable.dadosc2);
                        dado05.setImageResource(R.drawable.dadosc2);
                        dado06.setImageResource(R.drawable.dadosc2);
                        contaR = contaR + 1;
                        playSound2();
                        break;
                    case "dadoc3" :
                        dado01.setImageResource(R.drawable.dadosc3);
                        dado02.setImageResource(R.drawable.dadosc3);
                        dado03.setImageResource(R.drawable.dadosc3);
                        dado04.setImageResource(R.drawable.dadosc3);
                        dado05.setImageResource(R.drawable.dadosc3);
                        dado06.setImageResource(R.drawable.dadosc3);
                        contaR = contaR + 1;
                        playSound2();
                        break;
                }


            }
            public void onFinish() {
                concentreSe.setVisibility(View.INVISIBLE );
                String[] opcoes = {"dado1", "dado2", "dado3", "dado4", "dado5", "dado6" };
                int totalPontos = 0;
                playSound6();

                //dado 01
                ImageView dado01 = findViewById(R.id.dado01);
                int dado1 = new Random().nextInt(6);
                totalPontos = totalPontos + dado1 + 1;
                String opcaoDado1 = opcoes[dado1];
                switch ( opcaoDado1 ){
                    case "dado1" :
                        dado01.setImageResource(R.drawable.dados01);
                        break;
                    case "dado2" :
                        dado01.setImageResource(R.drawable.dados02);
                        break;
                    case "dado3" :
                        dado01.setImageResource(R.drawable.dados03);
                        break;
                    case "dado4" :
                        dado01.setImageResource(R.drawable.dados04);
                        break;
                    case "dado5" :
                        dado01.setImageResource(R.drawable.dados05);
                        break;
                    case "dado6" :
                        dado01.setImageResource(R.drawable.dados06);
                        break;
                }

                //dado 02
                ImageView dado02 = findViewById(R.id.dado02);
                int dado2 = new Random().nextInt(6);
                totalPontos = totalPontos + dado2 + 1;
                String opcaoDado2 = opcoes[dado2];
                switch ( opcaoDado2 ){
                    case "dado1" :
                        dado02.setImageResource(R.drawable.dados01);
                        break;
                    case "dado2" :
                        dado02.setImageResource(R.drawable.dados02);
                        break;
                    case "dado3" :
                        dado02.setImageResource(R.drawable.dados03);
                        break;
                    case "dado4" :
                        dado02.setImageResource(R.drawable.dados04);
                        break;
                    case "dado5" :
                        dado02.setImageResource(R.drawable.dados05);
                        break;
                    case "dado6" :
                        dado02.setImageResource(R.drawable.dados06);
                        break;
                }

                //dado 03
                ImageView dado03 = findViewById(R.id.dado03);
                int dado3 = new Random().nextInt(6);
                totalPontos = totalPontos + dado3 + 1;
                String opcaoDado3 = opcoes[dado3];
                switch ( opcaoDado3 ){
                    case "dado1" :
                        dado03.setImageResource(R.drawable.dados01);
                        break;
                    case "dado2" :
                        dado03.setImageResource(R.drawable.dados02);
                        break;
                    case "dado3" :
                        dado03.setImageResource(R.drawable.dados03);
                        break;
                    case "dado4" :
                        dado03.setImageResource(R.drawable.dados04);
                        break;
                    case "dado5" :
                        dado03.setImageResource(R.drawable.dados05);
                        break;
                    case "dado6" :
                        dado03.setImageResource(R.drawable.dados06);
                        break;
                }

                //dado 04
                ImageView dado04 = findViewById(R.id.dado04);
                int dado4 = new Random().nextInt(6);
                totalPontos = totalPontos + dado4 + 1;
                String opcaoDado4 = opcoes[dado4];
                switch ( opcaoDado4 ){
                    case "dado1" :
                        dado04.setImageResource(R.drawable.dados01);
                        break;
                    case "dado2" :
                        dado04.setImageResource(R.drawable.dados02);
                        break;
                    case "dado3" :
                        dado04.setImageResource(R.drawable.dados03);
                        break;
                    case "dado4" :
                        dado04.setImageResource(R.drawable.dados04);
                        break;
                    case "dado5" :
                        dado04.setImageResource(R.drawable.dados05);
                        break;
                    case "dado6" :
                        dado04.setImageResource(R.drawable.dados06);
                        break;
                }

                //dado 05
                ImageView dado05 = findViewById(R.id.dado05);
                int dado5 = new Random().nextInt(6);
                totalPontos = totalPontos + dado5 + 1;
                String opcaoDado5 = opcoes[dado5];
                switch ( opcaoDado5 ){
                    case "dado1" :
                        dado05.setImageResource(R.drawable.dados01);
                        break;
                    case "dado2" :
                        dado05.setImageResource(R.drawable.dados02);
                        break;
                    case "dado3" :
                        dado05.setImageResource(R.drawable.dados03);
                        break;
                    case "dado4" :
                        dado05.setImageResource(R.drawable.dados04);
                        break;
                    case "dado5" :
                        dado05.setImageResource(R.drawable.dados05);
                        break;
                    case "dado6" :
                        dado05.setImageResource(R.drawable.dados06);
                        break;
                }

                //dado 06
                ImageView dado06 = findViewById(R.id.dado06);
                int dado6 = new Random().nextInt(6);
                totalPontos = totalPontos + dado6 + 1;
                String opcaoDado6 = opcoes[dado6];
                switch ( opcaoDado6 ){
                    case "dado1" :
                        dado06.setImageResource(R.drawable.dados01);
                        break;
                    case "dado2" :
                        dado06.setImageResource(R.drawable.dados02);
                        break;
                    case "dado3" :
                        dado06.setImageResource(R.drawable.dados03);
                        break;
                    case "dado4" :
                        dado06.setImageResource(R.drawable.dados04);
                        break;
                    case "dado5" :
                        dado06.setImageResource(R.drawable.dados05);
                        break;
                    case "dado6" :
                        dado06.setImageResource(R.drawable.dados06);
                        break;
                }

                TextView memo = findViewById(R.id.concentreSe);
                memo.setText("Memorize!");
                memo.setVisibility(View.VISIBLE);

                TextView cola = findViewById(R.id.cola);
                TextView status = findViewById(R.id.status);
                cola.setText("" + totalPontos);
                TextView textView8 = findViewById(R.id.valorEscala);
                new CountDownTimer(4000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        status.setText("" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        TextView valorEscala = findViewById(R.id.valorEscala);
                        valorEscala.setVisibility(View.VISIBLE );
                        SeekBar seekBarEscala = findViewById(R.id.seekBarEscala);
                        seekBarEscala.setVisibility(View.VISIBLE );
                        Button botaoVerificar = findViewById(R.id.botaoVerificar);
                        botaoVerificar.setVisibility(View.VISIBLE );
                        Button botaoReiniciar = findViewById(R.id.botaoReiniciar);
                        botaoReiniciar.setVisibility(View.VISIBLE );
                        dado01.setImageResource(R.drawable.dados0);
                        dado02.setImageResource(R.drawable.dados0);
                        dado03.setImageResource(R.drawable.dados0);
                        dado04.setImageResource(R.drawable.dados0);
                        dado05.setImageResource(R.drawable.dados0);
                        dado06.setImageResource(R.drawable.dados0);
                        status.setVisibility(View.VISIBLE );
                        memo.setVisibility(View.INVISIBLE);
                        status.setText(R.string.Times_up);
                        ImageView botaoMais7 = findViewById(R.id.botaoMais7);
                        ImageView botaoMenos7 = findViewById(R.id.botaoMenos7);
                        botaoMais7.setVisibility(View.VISIBLE);
                        botaoMenos7.setVisibility(View.VISIBLE);
                    }
                }.start();

            }
        }.start();
    }

    private void prepareAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6944068380993727/5906000395");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }


    //botão reiniciar fase
    public void desiste (View view){
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }else {
            prepareAd();
        }
        ImageView tick = findViewById(R.id.tick);
        ImageView tick2 = findViewById(R.id.tick2);
        ImageView tick3 = findViewById(R.id.tick3);
        ImageView tick4 = findViewById(R.id.tick4);
        ImageView tick5 = findViewById(R.id.tick5);
        ImageView tick6 = findViewById(R.id.tick6);
        ImageView start01 = findViewById(R.id.estrela1);
        ImageView start02 = findViewById(R.id.estrela2);
        ImageView start03 = findViewById(R.id.estrela3);
        Button continuar = findViewById(R.id.botaoVoltar);
        ImageView dado01 = findViewById(R.id.dado01);
        ImageView dado02 = findViewById(R.id.dado02);
        ImageView dado03 = findViewById(R.id.dado03);
        ImageView dado04 = findViewById(R.id.dado04);
        ImageView dado05 = findViewById(R.id.dado05);
        ImageView dado06 = findViewById(R.id.dado06);
        Button repetir = findViewById(R.id.botaoRetryin);
        Button desiste = findViewById(R.id.botaoReiniciar);
        Button verificar = findViewById(R.id.botaoVerificar);
        Button iniciar = findViewById(R.id.botaoComecar);
        SeekBar seekBarEscala = findViewById(R.id.seekBarEscala);
        TextView valorEscala = findViewById(R.id.valorEscala);
        TextView status = findViewById(R.id.status);
        repetir.setVisibility(View.INVISIBLE);
        desiste.setVisibility(View.INVISIBLE);
        verificar.setVisibility(View.INVISIBLE);
        seekBarEscala.setVisibility(View.INVISIBLE);
        valorEscala.setVisibility(View.INVISIBLE);
        status.setVisibility(View.INVISIBLE);
        iniciar.setVisibility(View.VISIBLE);
        dado01.setImageResource(R.drawable.dados00);
        dado02.setImageResource(R.drawable.dados00);
        dado03.setImageResource(R.drawable.dados00);
        dado04.setImageResource(R.drawable.dados00);
        dado05.setImageResource(R.drawable.dados00);
        dado06.setImageResource(R.drawable.dados00);
        tick.setVisibility(View.INVISIBLE);
        tick2.setVisibility(View.INVISIBLE);
        tick3.setVisibility(View.INVISIBLE);
        tick4.setVisibility(View.INVISIBLE);
        tick5.setVisibility(View.INVISIBLE);
        tick6.setVisibility(View.INVISIBLE);
        start01.setVisibility(View.INVISIBLE);
        start02.setVisibility(View.INVISIBLE);
        start03.setVisibility(View.INVISIBLE);
        continuar.setVisibility(View.INVISIBLE);
        Animation animation1 = AnimationUtils.loadAnimation(Fase6Activity.this,R.anim.fade2_out);
        start01.startAnimation(animation1);
        start02.startAnimation(animation1);
        start03.startAnimation(animation1);
        seekBarEscala.setProgress(0);
            ImageView botaoMais7 = findViewById(R.id.botaoMais7);
            ImageView botaoMenos7 = findViewById(R.id.botaoMenos7);
            botaoMais7.setVisibility(View.INVISIBLE);
            botaoMenos7.setVisibility(View.INVISIBLE);
        TextView movimentoConta = findViewById(R.id.movimentoConta6);
        movimentoConta.setVisibility(View.INVISIBLE);
        ImageView icoSerio = findViewById(R.id.serioEmoticon6);
        icoSerio.setVisibility(View.INVISIBLE);
        ImageView icoTriste = findViewById(R.id.tristeEmoticon6);
        icoTriste.setVisibility(View.INVISIBLE);

        pts = 3;
    }


    //botão verifica dados do usuario
    public void verifica (View view){
        int x = seekBarEscala.getProgress(); // valor informado na barra
        TextView cola = findViewById(R.id.cola);
        CharSequence y = cola.getText(); // valor real do dado
        TextView status = findViewById(R.id.status);
        //status.setText( "Informado: " + (x +1) + " / " + y );
        int y2 = Integer.parseInt(y.toString());
        if ((x + 6) == y2){
            //status.setText("Parabéns, você acertou!!!!!\nQue tal jogar mais uma vez?");
            status.setText("");
            TextView valorEscala = findViewById(R.id.valorEscala);
            valorEscala.setVisibility(View.INVISIBLE );
            SeekBar seekBarEscala = findViewById(R.id.seekBarEscala);
            seekBarEscala.setVisibility(View.INVISIBLE );
            Button botaoVerificar = findViewById(R.id.botaoVerificar);
            botaoVerificar.setVisibility(View.INVISIBLE );
            Button botaoReiniciar = findViewById(R.id.botaoReiniciar);
            botaoReiniciar.setVisibility(View.INVISIBLE );
            ImageView dado01 = findViewById(R.id.dado01);
            dado01.setImageResource(R.drawable.dados00);
            ImageView dado02 = findViewById(R.id.dado02);
            dado02.setImageResource(R.drawable.dados00);
            ImageView dado03 = findViewById(R.id.dado03);
            dado03.setImageResource(R.drawable.dados00);
            ImageView dado04 = findViewById(R.id.dado04);
            dado04.setImageResource(R.drawable.dados00);
            ImageView dado05 = findViewById(R.id.dado05);
            dado05.setImageResource(R.drawable.dados00);
            ImageView dado06 = findViewById(R.id.dado06);
            dado06.setImageResource(R.drawable.dados00);
            ImageView tick = findViewById(R.id.tick);
            tick.setVisibility(View.VISIBLE );
            ImageView tick2 = findViewById(R.id.tick2);
            tick2.setVisibility(View.VISIBLE );
            ImageView tick3 = findViewById(R.id.tick3);
            tick3.setVisibility(View.VISIBLE );
            ImageView tick4 = findViewById(R.id.tick4);
            tick4.setVisibility(View.VISIBLE );
            ImageView tick5 = findViewById(R.id.tick5);
            tick5.setVisibility(View.VISIBLE );
            ImageView tick6 = findViewById(R.id.tick6);
            tick6.setVisibility(View.VISIBLE );
            Button botaoVoltar = findViewById(R.id.botaoVoltar);
            botaoVoltar.setVisibility(View.VISIBLE );
            Button botaoretryin = findViewById(R.id.botaoRetryin);
            botaoretryin.setVisibility(View.VISIBLE );

            ImageView estrela1 = findViewById(R.id.estrela1);
            ImageView estrela2 = findViewById(R.id.estrela2);
            ImageView estrela3 = findViewById(R.id.estrela3);
            ImageView botaoMais7 = findViewById(R.id.botaoMais7);
            ImageView botaoMenos7 = findViewById(R.id.botaoMenos7);
            botaoMais7.setVisibility(View.INVISIBLE);
            botaoMenos7.setVisibility(View.INVISIBLE);
            if (pts >= 1) {
                TextView movimentoConta = findViewById(R.id.movimentoConta6);
                movimentoConta.setVisibility(View.VISIBLE );
                if (pts == 3) {
                    movimentoConta.setText(((pts * -1) + 4) + " Tentativa");
                }else
                {
                    movimentoConta.setText(((pts * -1) + 4) + " Tentativas");
                }
                new CountDownTimer(500, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        estrela1.setImageResource(R.drawable.estrela);
                        estrela1.setVisibility(View.VISIBLE);
                        //    ImageView estrela1g;
                        Animation animation1 = AnimationUtils.loadAnimation(Fase6Activity.this, R.anim.zoom_anim);
                        estrela1.startAnimation(animation1);
                        playSound3();
                    }
                }.start();
            }

            if (pts >= 2) {
                new CountDownTimer(1300, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        estrela2.setImageResource(R.drawable.estrela);
                        estrela2.setVisibility(View.VISIBLE);
                        //   ImageView estrela2g;
                        Animation animation2 = AnimationUtils.loadAnimation(Fase6Activity.this, R.anim.zoom_anim);
                        estrela2.startAnimation(animation2);
                        playSound4();
                    }
                }.start();
            }

            if (pts >= 3) {
                new CountDownTimer(2100, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        estrela3.setImageResource(R.drawable.estrela);
                        estrela3.setVisibility(View.VISIBLE);
                        //    ImageView estrela3g;
                        Animation animation3 = AnimationUtils.loadAnimation(Fase6Activity.this, R.anim.zoom_anim);
                        estrela3.startAnimation(animation3);
                        playSound5();
                    }
                }.start();
            }
            if (pts == 0) {
                TextView movimentoConta = findViewById(R.id.movimentoConta6);
                movimentoConta.setVisibility(View.VISIBLE );
                movimentoConta.setText(((pts * -1) + 4) + " tentativas");
                ImageView icoTriste = findViewById(R.id.serioEmoticon6);
                icoTriste.setVisibility(View.VISIBLE);
            }

            if (pts < 0) {
                TextView movimentoConta = findViewById(R.id.movimentoConta6);
                movimentoConta.setVisibility(View.VISIBLE );
                movimentoConta.setText(((pts * -1) + 4) + " tentativas");
                ImageView icoSerio = findViewById(R.id.tristeEmoticon6);
                icoSerio.setVisibility(View.VISIBLE);
            }


            SharedPreferences preferences =  getSharedPreferences(ARQUIVO_DADOS, 0 );

            if (preferences.contains("pontos")){
                String totalPontosUser = preferences.getString("pontos", "");
                SharedPreferences.Editor editor =  preferences.edit();
                int value = Integer.parseInt(totalPontosUser);
                value = (value + (pts * 5));
                editor.putString("fase6", "1");
                editor.putString("pontos", (value + ""));
                editor.apply();
                TextView pontos = findViewById(R.id.pontosUser);
                pontos.setText(String.valueOf(value));

            }else
            {
                SharedPreferences.Editor editor =  preferences.edit();
                editor.putString("fase6", "1");
                editor.putString("pontos", ((pts * 5) + ""));
                editor.apply();
                String totalPontosUser = preferences.getString("pontos", "");
                TextView pontos = findViewById(R.id.pontosUser);
                pontos.setText(totalPontosUser);
            }
        }else
        {
            pts = (pts - 1);
            status.setText(R.string.wrong);
            playSound8();

        }
    }
}
