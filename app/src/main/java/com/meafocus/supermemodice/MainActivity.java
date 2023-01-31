package com.meafocus.supermemodice;


import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
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

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private static final String ARQUIVO_DADOS = "ArquivoUser";
    private MediaPlayer player;

    private void playSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.beep28);
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences =  getSharedPreferences(ARQUIVO_DADOS, 0 );
        if (preferences.contains("pontos")){
            String totalPontosUser = preferences.getString("pontos", "");
            TextView pontos = findViewById(R.id.pontosUser01);
            pontos.setText(totalPontosUser);
        }else
        {
            String totalPontosUser = "0";
            TextView pontos = findViewById(R.id.pontosUser01);
            pontos.setText(totalPontosUser);
        }


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

        //banner admob - Início
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
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
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        //banner admob - Fim

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView level01 = findViewById(R.id.level01);
        ImageView level02 = findViewById(R.id.level02);
        ImageView level03 = findViewById(R.id.level03);
        ImageView level04 = findViewById(R.id.level04);
        ImageView level05 = findViewById(R.id.level05);
        ImageView level06 = findViewById(R.id.level06);

        level01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                Intent intent = new Intent(getApplicationContext(), Fase1Activity.class);
                startActivity(intent);
                mInterstitialAd.show();
            }
        });

        if (preferences.contains("fase1")){
            String passouFase1 = preferences.getString("fase1", "");
            if (passouFase1.equals ("1")) {
                level02.setImageResource(R.drawable.dados02v);
                level02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound();
                        Intent intent = new Intent(getApplicationContext(), Fase2Activity.class);
                        startActivity(intent);
                        mInterstitialAd.show();
                    }
                });
            }
        }else {
            level02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Você deve concluir o nível 1 primeiro",
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
        }

        if (preferences.contains("fase2")){
            String passouFase2 = preferences.getString("fase2", "");
            if (passouFase2.equals ("1")) {
                level03.setImageResource(R.drawable.dados03v);
                level03.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound();
                        Intent intent = new Intent(getApplicationContext(), Fase3Activity.class);
                        startActivity(intent);
                        mInterstitialAd.show();
                    }
                });
            }
        }else {
            level03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Você deve concluir o nível 2 primeiro",
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
        }

        if (preferences.contains("fase3")){
            String passouFase3 = preferences.getString("fase3", "");
            if (passouFase3.equals ("1")) {
                level04.setImageResource(R.drawable.dados04v);
                level04.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound();
                        Intent intent = new Intent(getApplicationContext(), Fase4Activity.class);
                        startActivity(intent);
                        mInterstitialAd.show();
                    }
                });
            }
        }else {
            level04.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Você deve concluir o nível 3 primeiro",
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
        }

        if (preferences.contains("fase4")){
            String passouFase4 = preferences.getString("fase4", "");
            if (passouFase4.equals ("1")) {
                level05.setImageResource(R.drawable.dados05v);
                level05.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound();
                        Intent intent = new Intent(getApplicationContext(), Fase5Activity.class);
                        startActivity(intent);
                        mInterstitialAd.show();
                    }
                });
            }
        }else {
            level05.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Você deve concluir o nível 4 primeiro",
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
        }

        if (preferences.contains("fase5")){
            String passouFase5 = preferences.getString("fase5", "");
            if (passouFase5.equals ("1")) {
                level06.setImageResource(R.drawable.dados06v);
                level06.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound();
                        Intent intent = new Intent(getApplicationContext(), Fase6Activity.class);
                        startActivity(intent);
                        mInterstitialAd.show();
                    }
                });
            }
        }else {
            level06.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Você deve concluir o nível 5 primeiro",
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop () {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }



}