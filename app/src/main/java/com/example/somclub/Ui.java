package com.example.somclub;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ui {

    private static MediaPlayer activePlayer = null;

    // --- CONSTANTES DE COR ---
    public static final int WHITE = Color.WHITE;                  // Resolve o erro Ui.WHITE
    public static final int PRIMARY = Color.parseColor("#6A1B9A");
    public static final int PURPLE = Color.parseColor("#6A1B9A");
    public static final int ACCENT = Color.parseColor("#AB47BC");
    public static final int BACKGROUND = Color.parseColor("#121212");
    public static final int BG = Color.parseColor("#121212");
    public static final int SURFACE = Color.parseColor("#1E1B2E");
    public static final int TEXT_COLOR = Color.WHITE;
    public static final int MUTED = Color.parseColor("#B0BEC5");

    // --- 1. NAVEGAÇÃO ENTRE ECRÃS ---
    public static void go(Context context, Class<?> destinationClass) {
        Intent intent = new Intent(context, destinationClass);
        context.startActivity(intent);
    }

    // --- 2. ESTRUTURA DA PÁGINA ---
    public static LinearLayout page(Context context, String titleText) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(BACKGROUND);
        layout.setPadding(40, 60, 40, 60);

        TextView title = new TextView(context);
        title.setText(titleText);
        title.setTextSize(26);
        title.setTextColor(WHITE);
        title.setTypeface(null, Typeface.BOLD);
        title.setPadding(0, 0, 0, 40);

        layout.addView(title);
        return layout;
    }

    // --- 3. CRIAR TEXTOS ---
    public static TextView text(Context context, String content, float size, int color, boolean bold) {
        TextView textView = new TextView(context);
        textView.setText(content);
        textView.setTextSize(size);
        textView.setTextColor(color);
        if (bold) {
            textView.setTypeface(null, Typeface.BOLD);
        }
        textView.setPadding(0, 10, 0, 20);
        return textView;
    }

    public static TextView text(Context context, String content) {
        return text(context, content, 15, WHITE, false);
    }

    // --- 4. CAMPO DE TEXTO / PESQUISA ---
    public static EditText field(Context context, String hintText) {
        EditText editText = new EditText(context);
        editText.setHint(hintText);
        editText.setHintTextColor(Color.parseColor("#80FFFFFF"));
        editText.setTextColor(WHITE);
        editText.setTextSize(15);
        editText.setPadding(36, 30, 36, 30);

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(20f);
        shape.setColor(Color.parseColor("#261C2C"));
        shape.setStroke(2, Color.parseColor("#4A148C"));
        editText.setBackground(shape);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 24);
        editText.setLayoutParams(params);

        return editText;
    }

    // --- 5. CARTÃO ESTILIZADO ---
    public static LinearLayout card(Context context, String titleText, String subtitleText) {
        LinearLayout cardLayout = new LinearLayout(context);
        cardLayout.setOrientation(LinearLayout.VERTICAL);
        cardLayout.setPadding(40, 30, 40, 30);

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(24f);
        shape.setColor(SURFACE);
        shape.setStroke(2, PRIMARY);
        cardLayout.setBackground(shape);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 24);
        cardLayout.setLayoutParams(params);

        TextView title = new TextView(context);
        title.setText(titleText);
        title.setTextSize(18);
        title.setTextColor(WHITE);
        title.setTypeface(null, Typeface.BOLD);

        TextView subtitle = new TextView(context);
        subtitle.setText(subtitleText);
        subtitle.setTextSize(14);
        subtitle.setTextColor(MUTED);
        subtitle.setPadding(0, 8, 0, 0);

        cardLayout.addView(title);
        cardLayout.addView(subtitle);

        return cardLayout;
    }

    // --- 6. BOTÃO PADRÃO ---
    public static Button button(Context context, String text) {
        Button btn = new Button(context);
        btn.setText(text);
        btn.setTextColor(WHITE);
        btn.setTextSize(15);
        btn.setTypeface(null, Typeface.BOLD);
        btn.setGravity(Gravity.CENTER);

        GradientDrawable btnShape = new GradientDrawable();
        btnShape.setCornerRadius(30f);
        btnShape.setColor(PRIMARY);
        btn.setBackground(btnShape);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                130
        );
        params.setMargins(0, 16, 0, 16);
        btn.setLayoutParams(params);

        return btn;
    }

    // --- BOTÃO DE ESTRELA PARA AVALIAÇÃO ---
    public static Button starButton(Context context, String text, boolean isSelected) {
        Button btn = new Button(context);
        btn.setText(text);
        btn.setTextSize(14);
        btn.setTypeface(null, Typeface.BOLD);
        btn.setGravity(Gravity.CENTER);

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(20f); // Cantos arredondados

        if (isSelected) {
            btn.setTextColor(Color.parseColor("#121212")); // Texto escuro no fundo dourado
            shape.setColor(Color.parseColor("#FFD700"));  // Amarelo Dourado aceso
            shape.setStroke(2, Color.parseColor("#FFC107"));
        } else {
            btn.setTextColor(Color.parseColor("#B0BEC5")); // Texto suave
            shape.setColor(Color.parseColor("#1E1B2E"));  // Roxo escuro apagado
            shape.setStroke(2, Color.parseColor("#4A148C")); // Borda roxa
        }

        btn.setBackground(shape);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 110, 1f);
        params.setMargins(6, 0, 6, 0);
        btn.setLayoutParams(params);

        return btn;
    }

    // --- CAIXA DE TEXTO PARA COMENTÁRIOS COM VÁRIAS LINHAS ---
    public static EditText commentField(Context context, String hintText) {
        EditText editText = new EditText(context);
        editText.setHint(hintText);
        editText.setHintTextColor(Color.parseColor("#80FFFFFF"));
        editText.setTextColor(WHITE);
        editText.setTextSize(14);
        editText.setPadding(30, 24, 30, 24);
        editText.setMinLines(2);

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(16f);
        shape.setColor(Color.parseColor("#1E1B2E"));
        shape.setStroke(2, Color.parseColor("#4A148C"));
        editText.setBackground(shape);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 12, 0, 12);
        editText.setLayoutParams(params);

        return editText;
    }

    // --- 7. BOTÃO DE PRÉVIA DE ÁUDIO ---
    public static Button audioPreviewButton(Context context, int audioResId) {
        Button btn = button(context, "▶ Ouvir Prévia");

        btn.setOnClickListener(v -> {
            if (activePlayer != null) {
                if (activePlayer.isPlaying()) {
                    activePlayer.stop();
                }
                activePlayer.release();
                activePlayer = null;
            }

            activePlayer = MediaPlayer.create(context, audioResId);
            if (activePlayer != null) {
                btn.setText("⏸ Tocando Prévia...");
                GradientDrawable playingShape = new GradientDrawable();
                playingShape.setCornerRadius(30f);
                playingShape.setColor(ACCENT);
                btn.setBackground(playingShape);

                activePlayer.start();

                activePlayer.setOnCompletionListener(mp -> {
                    btn.setText("▶ Ouvir Prévia");
                    GradientDrawable normalShape = new GradientDrawable();
                    normalShape.setCornerRadius(30f);
                    normalShape.setColor(PRIMARY);
                    btn.setBackground(normalShape);

                    mp.release();
                    activePlayer = null;
                });
            }
        });

        return btn;
    }

    // --- 8. PARAR ÁUDIO AO FECHAR TELA ---
    public static void stopAudio() {
        if (activePlayer != null) {
            if (activePlayer.isPlaying()) {
                activePlayer.stop();
            }
            activePlayer.release();
            activePlayer = null;
        }
    }
}