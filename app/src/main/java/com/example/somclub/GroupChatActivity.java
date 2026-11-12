package com.example.somclub;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class GroupChatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String groupName = getIntent().getStringExtra("group_name");
        if (groupName == null) groupName = "Chat do Grupo";

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Ui.BG);

        LinearLayout root = Ui.page(this, groupName);

        root.addView(Ui.text(this, "Bate-papo da Comunidade", 14, Ui.MUTED, false));

        LinearLayout chatContainer = new LinearLayout(this);
        chatContainer.setOrientation(LinearLayout.VERTICAL);
        chatContainer.setPadding(0, 10, 0, 20);
        root.addView(chatContainer);

        chatContainer.addView(Ui.card(this, "@lucas_beats", "Fala pessoal! Alguém escutando o novo som da SZA?"));
        chatContainer.addView(Ui.card(this, "@ana_music", "Eu! A faixa Open Arms tá boa demais 🔥"));

        root.addView(Ui.text(this, "Sua mensagem:", 14, Ui.WHITE, true));
        EditText inputMsg = Ui.commentField(this, "Digite sua mensagem para o grupo...");
        root.addView(inputMsg);

        Button btnSend = Ui.button(this, "Enviar Mensagem");
        root.addView(btnSend);

        Button btnBack = Ui.button(this, "Voltar aos Grupos");
        btnBack.setOnClickListener(v -> finish());
        root.addView(btnBack);

        btnSend.setOnClickListener(v -> {
            String text = inputMsg.getText().toString().trim();
            if (text.isEmpty()) {
                Toast.makeText(this, "Digite uma mensagem antes de enviar!", Toast.LENGTH_SHORT).show();
            } else {
                LinearLayout newMsgCard = Ui.card(this, "@voce", text);
                chatContainer.addView(newMsgCard);

                inputMsg.setText("");
                Toast.makeText(this, "Mensagem enviada!", Toast.LENGTH_SHORT).show();
            }
        });

        scroll.addView(root);
        setContentView(scroll);
    }
}