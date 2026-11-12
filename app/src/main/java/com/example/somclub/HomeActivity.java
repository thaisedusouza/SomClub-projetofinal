package com.example.somclub;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Ui.BG);

        // Título principal
        LinearLayout root = Ui.page(this, "Seu SomClub");

        // Subtítulo / mensagem inicial
        root.addView(Ui.text(this, "OLA! QUE SOM RESSONA COM VOCE HOJE?", 13, Ui.MUTED, true));

        // Campo de busca
        EditText searchField = Ui.field(this, "Buscar artista, música ou grupo");
        root.addView(searchField);

        // Seção Em Destaque
        root.addView(Ui.text(this, "Em destaque", 16, Ui.WHITE, true));
        root.addView(Ui.card(this, "Good Things - SZA", "R&B • Avaliação da comunidade: ★ 4.8"));

        // Seção Descubra
        root.addView(Ui.text(this, "Descubra", 16, Ui.WHITE, true));
        root.addView(Ui.card(this, "Grupos musicais", "Encontre pessoas que curtem o mesmo som."));
        root.addView(Ui.card(this, "Eventos próximos", "Shows e encontros para colocar a música na rua."));

        // Botões de Ação
        Button btnMúsicas = Ui.button(this, "Explorar músicas");
        btnMúsicas.setOnClickListener(v -> Ui.go(this, SongActivity.class));
        root.addView(btnMúsicas);

        Button btnGrupos = Ui.button(this, "Encontrar grupos");
        btnGrupos.setOnClickListener(v -> Ui.go(this, GroupsActivity.class));
        root.addView(btnGrupos);

        Button btnEventos = Ui.button(this, "Ver eventos");
        btnEventos.setOnClickListener(v -> Ui.go(this, EventsActivity.class));
        root.addView(btnEventos);

        // O botão de Localização foi totalmente removido daqui!

        scroll.addView(root);
        setContentView(scroll);
    }
}