package com.example.somclub;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class LoginActivity extends Activity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scroll = new ScrollView(this);
        LinearLayout root = Ui.page(this, "Bem-vindo!");
        TextView t14 = Ui.text(this, "Acesse sua conta para continuar.", 15, Ui.MUTED, false);
        root.addView(t14);
        root.addView(Ui.field(this, "E-mail"));
        root.addView(Ui.field(this, "Senha"));
        android.widget.Button button0 = Ui.button(this, "Entrar");
        root.addView(button0);
        button0.setOnClickListener(v -> Ui.go(this, HomeActivity.class));
        android.widget.Button button1 = Ui.button(this, "Criar conta");
        root.addView(button1);
        button1.setOnClickListener(v -> Ui.go(this, RegisterActivity.class));
        scroll.addView(root);
        setContentView(scroll);
    }
}
