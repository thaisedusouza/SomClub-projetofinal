package com.example.somclub;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SongActivity extends Activity {

    private int userRating = 0;
    private final Button[] starButtons = new Button[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Ui.BG);

        LinearLayout root = Ui.page(this, "🎵 Destaque do SomClub");

        // --- CARD DA MÚSICA OPEN ARMS DA SZA ---
        LinearLayout cardSong = Ui.card(this, "Open Arms - SZA (feat. Travis Scott)", "Álbum: SOS • R&B / Acoustic");
        cardSong.addView(Ui.audioPreviewButton(this, R.raw.open_arms));
        root.addView(cardSong);

        // --- SEÇÃO DE AVALIAÇÃO (ESTRELAS ESTILIZADAS) ---
        root.addView(Ui.text(this, "Avalie esta faixa:", 16, Ui.WHITE, true));

        LinearLayout ratingLayout = new LinearLayout(this);
        ratingLayout.setOrientation(LinearLayout.HORIZONTAL);
        ratingLayout.setPadding(0, 10, 0, 16);

        TextView txtRatingStatus = Ui.text(this, "Sua nota: Nenhuma", 14, Ui.MUTED, false);

        // Criar os 5 botões de estrelas arredondados
        for (int i = 0; i < 5; i++) {
            final int starIndex = i + 1;
            Button btnStar = Ui.starButton(this, "★ " + starIndex, false);
            starButtons[i] = btnStar;

            btnStar.setOnClickListener(v -> {
                userRating = starIndex;
                txtRatingStatus.setText("Sua nota: " + userRating + " / 5 ★");
                updateStarVisuals();
                Toast.makeText(this, "Você avaliou com " + starIndex + " estrelas!", Toast.LENGTH_SHORT).show();
            });

            ratingLayout.addView(btnStar);
        }

        root.addView(ratingLayout);
        root.addView(txtRatingStatus);

        // --- SEÇÃO DE COMENTÁRIOS ---
        root.addView(Ui.text(this, "Deixe seu comentário:", 16, Ui.WHITE, true));

        EditText inputComment = Ui.commentField(this, "Escreva o que achou de Open Arms...");
        root.addView(inputComment);

        Button btnPublish = Ui.button(this, "💬 Publicar Comentário");
        root.addView(btnPublish);

        // Container de comentários
        LinearLayout commentsList = new LinearLayout(this);
        commentsList.setOrientation(LinearLayout.VERTICAL);
        commentsList.setPadding(0, 20, 0, 20);
        root.addView(commentsList);

        // Comentários de exemplo
        commentsList.addView(Ui.card(this, " @marcos_vibes • 5 ★", "Open Arms é incrível! Essa vibe acústica da SZA é perfeita."));

        // Lógica de publicação
        btnPublish.setOnClickListener(v -> {
            String text = inputComment.getText().toString().trim();
            if (text.isEmpty()) {
                Toast.makeText(this, "Digite um comentário antes de publicar!", Toast.LENGTH_SHORT).show();
            } else {
                String ratingHeader = (userRating > 0) ? " @voce • " + userRating + " ★" : " @voce";

                LinearLayout newCommentCard = Ui.card(this, ratingHeader, text);
                commentsList.addView(newCommentCard, 0);

                inputComment.setText("");
                Toast.makeText(this, "Comentário publicado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        scroll.addView(root);
        setContentView(scroll);
    }

    // Atualiza a cor das estrelas até a nota selecionada (acende as selecionadas em dourado)
    private void updateStarVisuals() {
        for (int i = 0; i < 5; i++) {
            boolean isSelected = (i < userRating);

            android.graphics.drawable.GradientDrawable shape = new android.graphics.drawable.GradientDrawable();
            shape.setCornerRadius(20f);

            if (isSelected) {
                starButtons[i].setTextColor(android.graphics.Color.parseColor("#121212"));
                shape.setColor(android.graphics.Color.parseColor("#FFD700")); // Dourado aceso
                shape.setStroke(2, android.graphics.Color.parseColor("#FFC107"));
            } else {
                starButtons[i].setTextColor(android.graphics.Color.parseColor("#B0BEC5"));
                shape.setColor(android.graphics.Color.parseColor("#1E1B2E")); // Roxo apagado
                shape.setStroke(2, android.graphics.Color.parseColor("#4A148C"));
            }

            starButtons[i].setBackground(shape);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Ui.stopAudio();
    }
}