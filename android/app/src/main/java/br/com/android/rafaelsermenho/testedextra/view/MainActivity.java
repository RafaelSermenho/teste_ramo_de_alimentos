package br.com.android.rafaelsermenho.testedextra.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.android.rafaelsermenho.testedextra.R;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCardapio = (Button) findViewById(R.id.btnCardapio);
        Button btnPromocoes = (Button) findViewById(R.id.btnPromocoes);
        Button btnCarrinho = (Button) findViewById(R.id.btnCarrinho);

        btnCardapio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCardapio = new Intent(MainActivity.this, SandwichListActivity.class);
                startActivity(openCardapio);
            }
        });

        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCarrinho = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(openCarrinho);
            }
        });

        btnPromocoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPromocao = new Intent(MainActivity.this, PromotionActivity.class);
                startActivity(openPromocao);
            }
        });
    }

}
