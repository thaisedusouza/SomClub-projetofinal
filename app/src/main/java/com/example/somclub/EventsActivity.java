package com.example.somclub;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends Activity {

    // Estrutura de dados para os Eventos
    static class EventItem {
        String title;
        String location;
        String date;
        int confirmedCount;
        boolean isGoing;

        EventItem(String title, String location, String date, int confirmedCount) {
            this.title = title;
            this.location = location;
            this.date = date;
            this.confirmedCount = confirmedCount;
            this.isGoing = false;
        }
    }

    private final List<EventItem> eventList = new ArrayList<>();
    private LinearLayout eventsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Adiciona eventos iniciais para demonstração
        if (eventList.isEmpty()) {
            eventList.add(new EventItem("Festival R&B e Soul", "Parque da Cidade • 19:00", "Sáb, 15 de Nov", 84));
            eventList.add(new EventItem("Show ao Vivo: Tributo SZA", "Club Jam Underground • 21:30", "Sex, 20 de Nov", 142));
            eventList.add(new EventItem("Encontro de Produtores e Beats", "Estúdio Central • 16:00", "Dom, 22 de Nov", 39));
            eventList.add(new EventItem("Jam Session Aberta: Trap & Rap", "Praça da Cultura • 18:00", "Sáb, 28 de Nov", 67));
        }

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Ui.BG);

        LinearLayout root = Ui.page(this, "Eventos Próximos");

        // Campo de Pesquisa de Eventos
        EditText searchField = Ui.field(this, "Buscar evento ou local...");
        root.addView(searchField);

        // Seção para sugerir/criar evento
        root.addView(Ui.text(this, "Publicar novo evento:", 16, Ui.WHITE, true));
        EditText inputEventTitle = Ui.field(this, "Nome do evento");
        EditText inputEventLoc = Ui.field(this, "Local e horário");
        Button btnCreateEvent = Ui.button(this, "Publicar Evento");

        root.addView(inputEventTitle);
        root.addView(inputEventLoc);
        root.addView(btnCreateEvent);

        // Lista de Eventos
        root.addView(Ui.text(this, "Próximos acontecimentos:", 16, Ui.WHITE, true));
        eventsContainer = new LinearLayout(this);
        eventsContainer.setOrientation(LinearLayout.VERTICAL);
        root.addView(eventsContainer);

        // Botão para voltar à Home
        Button btnBack = Ui.button(this, "Voltar à tela inicial");
        btnBack.setOnClickListener(v -> Ui.go(this, HomeActivity.class));
        root.addView(btnBack);

        // Renderiza a lista de eventos
        renderEvents("");

        // Filtro em tempo real ao digitar na busca
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                renderEvents(s.toString().toLowerCase().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Lógica para adicionar novos eventos
        btnCreateEvent.setOnClickListener(v -> {
            String title = inputEventTitle.getText().toString().trim();
            String loc = inputEventLoc.getText().toString().trim();

            if (title.isEmpty() || loc.isEmpty()) {
                Toast.makeText(this, "Preencha o nome e o local do evento!", Toast.LENGTH_SHORT).show();
            } else {
                EventItem newEvent = new EventItem(title, loc, "Hoje", 1);
                newEvent.isGoing = true;
                eventList.add(0, newEvent);

                inputEventTitle.setText("");
                inputEventLoc.setText("");
                renderEvents("");
                Toast.makeText(this, "Evento '" + title + "' criado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        scroll.addView(root);
        setContentView(scroll);
    }

    // Método para atualizar dinamicamente os cards de evento
    private void renderEvents(String filterText) {
        eventsContainer.removeAllViews();

        for (EventItem event : eventList) {
            if (filterText.isEmpty() || event.title.toLowerCase().contains(filterText) || event.location.toLowerCase().contains(filterText)) {

                String subtitle = event.date + " • " + event.location + "\n" + event.confirmedCount + " pessoas confirmadas";
                LinearLayout cardEvent = Ui.card(this, event.title, subtitle);

                // Botão de Presença Confirmada / Vou
                Button btnGoing;
                if (event.isGoing) {
                    btnGoing = Ui.button(this, "✓ Presença Confirmada");
                } else {
                    btnGoing = Ui.button(this, "Confirmar Presença");
                }

                btnGoing.setOnClickListener(v -> {
                    if (event.isGoing) {
                        event.isGoing = false;
                        event.confirmedCount--;
                        Toast.makeText(this, "Presença cancelada em " + event.title, Toast.LENGTH_SHORT).show();
                    } else {
                        event.isGoing = true;
                        event.confirmedCount++;
                        Toast.makeText(this, "Presença confirmada no evento!", Toast.LENGTH_SHORT).show();
                    }
                    renderEvents(filterText);
                });

                cardEvent.addView(btnGoing);
                eventsContainer.addView(cardEvent);
            }
        }
    }
}