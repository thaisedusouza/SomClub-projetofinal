package com.example.somclub;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class RegisterActivity extends Activity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scroll = new ScrollView(this);
        LinearLayout root = Ui.page(this, "Crie sua conta");
        TextView t14 = Ui.text(this, "Faça parte da nossa comunidade musical.", 15, Ui.MUTED, false);
        root.addView(t14);
        root.addView(Ui.field(this, "Nome completo"));
        root.addView(Ui.field(this, "E-mail"));
        root.addView(Ui.field(this, "Senha"));
        android.widget.Button button0 = Ui.button(this, "Cadastrar");
        root.addView(button0);
        button0.setOnClickListener(v -> Ui.go(this, HomeActivity.class));
        android.widget.Button button1 = Ui.button(this, "Já tenho conta — Entrar");
        root.addView(button1);
        button1.setOnClickListener(v -> Ui.go(this, LoginActivity.class));
        scroll.addView(root);
        setContentView(scroll);
    }
}
