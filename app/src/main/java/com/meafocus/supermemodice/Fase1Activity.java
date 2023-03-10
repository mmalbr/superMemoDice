package com.meafocus.supermemodice;

import android.annotation.SuppressLint;
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
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.animation.ImageMatrixProperty;

import java.util.Random;

public class Fase1Activity extends AppCompatActivity {
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
        setContentView(R.layout.activity_fase1);



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

        //banner admob - In??cio
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
        valorEscala.setText("1");

        seekBarEscala.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorEscala.setText("" +  (progress + 1));
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
        finish();
    }
// voltar ao menu incial - final



    // bot??o decremento - inicio
    public void botaoMenos(View view){

        int inicioEscala = 1;
        CharSequence capEscala = valorEscala.getText();
        int valorEscalaTela = Integer.parseInt(capEscala.toString());
        if (valorEscalaTela > inicioEscala) {
            seekPos = findViewById(R.id.seekBarEscala);
           seekPos.setProgress(valorEscalaTela - 2) ;
        }else
        {
            playSound7();
        }
    }
// bot??o decremento - final


    // bot??o incremento - inicio
    public void botaoMais(View view){

        int fimEscala = 5;
        CharSequence capEscala = valorEscala.getText();
        int valorEscalaTela = Integer.parseInt(capEscala.toString());
        if (valorEscalaTela <= fimEscala) {
         //   valorEscala.setText("" + (valorEscalaTela + 1));
            seekPos = findViewById(R.id.seekBarEscala);
            seekPos.setProgress(valorEscalaTela) ;
        }else
        {
            playSound7();
        }
    }
// bot??o incremento - final

    // bot??o de atribuir dados aleatorio
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
              switch ( opcaocontador ){
                  case "dadoc1" :
                      dado01.setImageResource(R.drawable.dadosc1);
                      contaR = contaR + 1;
                      playSound2();
                      break;
                  case "dadoc2" :
                      dado01.setImageResource(R.drawable.dadosc2);
                      contaR = contaR + 1;
                      playSound2();
                      break;
                  case "dadoc3" :
                      dado01.setImageResource(R.drawable.dadosc3);
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

              TextView memo = findViewById(R.id.concentreSe);
              memo.setText(R.string.memorize);
              memo.setVisibility(View.VISIBLE);


              TextView cola = findViewById(R.id.cola);
              TextView status = findViewById(R.id.status);
              cola.setText("" + totalPontos);
              TextView textView8 = findViewById(R.id.valorEscala);

              new CountDownTimer(170, 1000) {
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
                      status.setVisibility(View.VISIBLE );
                      memo.setVisibility(View.INVISIBLE);
                      status.setText(R.string.Times_up);
                      ImageView botaoMais = findViewById(R.id.botaoMais);
                      ImageView botaoMenos = findViewById(R.id.botaoMenos);
                      botaoMais.setVisibility(View.VISIBLE);
                      botaoMenos.setVisibility(View.VISIBLE);
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


  //bot??o reiniciar fase
    public void desiste (View view){
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }else {
            prepareAd();
        }
        ImageView tick = findViewById(R.id.tick);
        ImageView start01 = findViewById(R.id.estrela1);
        ImageView start02 = findViewById(R.id.estrela2);
        ImageView start03 = findViewById(R.id.estrela3);
        Button continuar = findViewById(R.id.botaoVoltar);
        ImageView dado01 = findViewById(R.id.dado01);
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
        tick.setVisibility(View.INVISIBLE);
        start01.setVisibility(View.INVISIBLE);
        start02.setVisibility(View.INVISIBLE);
        start03.setVisibility(View.INVISIBLE);
        continuar.setVisibility(View.INVISIBLE);
        Animation animation1 = AnimationUtils.loadAnimation(Fase1Activity.this,R.anim.fade2_out);
        start01.startAnimation(animation1);
        start02.startAnimation(animation1);
        start03.startAnimation(animation1);
        seekBarEscala.setProgress(0);
        ImageView botaoMais = findViewById(R.id.botaoMais);
        ImageView botaoMenos = findViewById(R.id.botaoMenos);
        botaoMais.setVisibility(View.INVISIBLE);
        botaoMenos.setVisibility(View.INVISIBLE);
        TextView movimentoConta = findViewById(R.id.movimentoConta);
        movimentoConta.setVisibility(View.INVISIBLE);
        ImageView icoSerio = findViewById(R.id.serioEmoticon);
        icoSerio.setVisibility(View.INVISIBLE);
        ImageView icoTriste = findViewById(R.id.tristeEmoticon);
        icoTriste.setVisibility(View.INVISIBLE);
        pts = 3;
    }


    //bot??o verifica dados do usuario
    public void verifica (View view){
        int x = seekBarEscala.getProgress(); // valor informado na barra
        TextView cola = findViewById(R.id.cola);
        CharSequence y = cola.getText(); // valor real do dado
        TextView status = findViewById(R.id.status);
        //status.setText( "Informado: " + (x +1) + " / " + y );
        int y2 = Integer.parseInt(y.toString());
        if ((x + 1) == y2){
            //status.setText("Parab??ns, voc?? acertou!!!!!\nQue tal jogar mais uma vez?");
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
            ImageView tick = findViewById(R.id.tick);
            tick.setVisibility(View.VISIBLE );
            Button botaoVoltar = findViewById(R.id.botaoVoltar);
            botaoVoltar.setVisibility(View.VISIBLE );
            Button botaoretryin = findViewById(R.id.botaoRetryin);
            botaoretryin.setVisibility(View.VISIBLE );
            ImageView estrela1 = findViewById(R.id.estrela1);
            ImageView estrela2 = findViewById(R.id.estrela2);
            ImageView estrela3 = findViewById(R.id.estrela3);
            ImageView botaoMais = findViewById(R.id.botaoMais);
            ImageView botaoMenos = findViewById(R.id.botaoMenos);
            botaoMais.setVisibility(View.INVISIBLE);
            botaoMenos.setVisibility(View.INVISIBLE);

            if (pts >= 1) {
                TextView movimentoConta = findViewById(R.id.movimentoConta);
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
                        Animation animation1 = AnimationUtils.loadAnimation(Fase1Activity.this, R.anim.zoom_anim);
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
                        Animation animation2 = AnimationUtils.loadAnimation(Fase1Activity.this, R.anim.zoom_anim);
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
                        Animation animation3 = AnimationUtils.loadAnimation(Fase1Activity.this, R.anim.zoom_anim);
                        estrela3.startAnimation(animation3);
                        playSound5();
                    }
                }.start();
            }

            if (pts == 0) {
                TextView movimentoConta = findViewById(R.id.movimentoConta);
                movimentoConta.setVisibility(View.VISIBLE );
                movimentoConta.setText(((pts * -1) + 4) + " tentativas");
                ImageView icoTriste = findViewById(R.id.serioEmoticon);
                icoTriste.setVisibility(View.VISIBLE);
            }

            if (pts < 0) {
                TextView movimentoConta = findViewById(R.id.movimentoConta);
                movimentoConta.setVisibility(View.VISIBLE );
                movimentoConta.setText(((pts * -1) + 4) + " tentativas");
                ImageView icoSerio = findViewById(R.id.tristeEmoticon);
                icoSerio.setVisibility(View.VISIBLE);
            }


                SharedPreferences preferences =  getSharedPreferences(ARQUIVO_DADOS, 0 );

            if (preferences.contains("pontos")){
                String totalPontosUser = preferences.getString("pontos", "");
                SharedPreferences.Editor editor =  preferences.edit();
                int value = Integer.parseInt(totalPontosUser);
                value = (value + pts);
                editor.putString("fase1", "1");
                editor.putString("pontos", (value + ""));
                editor.apply();
                TextView pontos = findViewById(R.id.pontosUser);
                pontos.setText(String.valueOf(value));

            }else
            {
                SharedPreferences.Editor editor =  preferences.edit();
                editor.putString("fase1", "1");
                editor.putString("pontos", (pts + ""));
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
