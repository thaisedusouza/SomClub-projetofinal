package com.example.somclub;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class LocationActivity extends Activity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scroll = new ScrollView(this);
        LinearLayout root = Ui.page(this, "Encontre eventos perto de você");
        TextView t14 = Ui.text(this, "O SomClub pode usar sua localização para mostrar eventos próximos. Você pode continuar sem permitir.", 17, Ui.MUTED, false);
        root.addView(t14);
        android.widget.Button button0 = Ui.button(this, "Permitir localização (demonstração)");
        root.addView(button0);
        button0.setOnClickListener(v -> Ui.go(this, EventsActivity.class));
        android.widget.Button button1 = Ui.button(this, "Agora não");
        root.addView(button1);
        button1.setOnClickListener(v -> Ui.go(this, EventsActivity.class));
        scroll.addView(root);
        setContentView(scroll);
    }
}
