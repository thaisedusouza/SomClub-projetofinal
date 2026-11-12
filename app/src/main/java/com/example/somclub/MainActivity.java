package com.example.somclub;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scroll = new ScrollView(this);
        LinearLayout root = Ui.page(this, "SomClub");
        TextView t14 = Ui.text(this, "♫", 54, Ui.PURPLE, false);
        t14.setGravity(Gravity.CENTER);
        root.addView(t14);
        TextView t17 = Ui.text(this, "Música • Grupos • Eventos", 16, Ui.MUTED, false);
        root.addView(t17);
        TextView t19 = Ui.text(this, "Avalie músicas, encontre pessoas com os mesmos gostos e descubra eventos.", 16, Ui.WHITE, true);
        root.addView(t19);
        android.widget.Button button0 = Ui.button(this, "Criar conta");
        root.addView(button0);
        button0.setOnClickListener(v -> Ui.go(this, RegisterActivity.class));
        android.widget.Button button1 = Ui.button(this, "Entrar");
        root.addView(button1);
        button1.setOnClickListener(v -> Ui.go(this, LoginActivity.class));
        scroll.addView(root);
        setContentView(scroll);
    }
}
