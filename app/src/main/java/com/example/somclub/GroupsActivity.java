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

public class GroupsActivity extends Activity {

    // Estrutura simples de dados para o Grupo
    static class GroupItem {
        String name;
        String genre;
        int members;
        boolean isJoined;

        GroupItem(String name, String genre, int members) {
            this.name = name;
            this.genre = genre;
            this.members = members;
            this.isJoined = false;
        }
    }

    private final List<GroupItem> groupList = new ArrayList<>();
    private LinearLayout groupsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Adiciona alguns grupos iniciais
        if (groupList.isEmpty()) {
            groupList.add(new GroupItem("Fãs de R&B & SZA", "R&B / Soul", 342));
            groupList.add(new GroupItem("Trap & Hip-Hop Brasil", "Hip-Hop", 518));
            groupList.add(new GroupItem("Indie & Pop Alternative", "Pop / Indie", 196));
            groupList.add(new GroupItem("Rock Clássico & Metal", "Rock", 245));
        }

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Ui.BG);

        LinearLayout root = Ui.page(this, "👥 Grupos Musicais");

        // --- CAMPO DE BUSCA FUNCIONAL ---
        EditText searchField = Ui.field(this, "🔍 Buscar grupo musical...");
        root.addView(searchField);

        // --- SEÇÃO DE CRIAR NOVO GRUPO ---
        root.addView(Ui.text(this, "Criar seu próprio grupo:", 16, Ui.WHITE, true));
        EditText inputGroupName = Ui.field(this, "Nome do novo grupo");
        EditText inputGenre = Ui.field(this, "Gênero musical (ex: Pop, Trap)");
        Button btnCreateGroup = Ui.button(this, "➕ Criar Grupo");

        root.addView(inputGroupName);
        root.addView(inputGenre);
        root.addView(btnCreateGroup);

        // --- LISTA DE GRUPOS ---
        root.addView(Ui.text(this, "Grupos disponíveis:", 16, Ui.WHITE, true));
        groupsContainer = new LinearLayout(this);
        groupsContainer.setOrientation(LinearLayout.VERTICAL);
        root.addView(groupsContainer);

        // Renderiza os grupos na tela
        renderGroups("");

        // Lógica de busca ao digitar
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                renderGroups(s.toString().toLowerCase().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Lógica para criar novo grupo
        btnCreateGroup.setOnClickListener(v -> {
            String name = inputGroupName.getText().toString().trim();
            String genre = inputGenre.getText().toString().trim();

            if (name.isEmpty() || genre.isEmpty()) {
                Toast.makeText(this, "Preencha o nome e o gênero do grupo!", Toast.LENGTH_SHORT).show();
            } else {
                GroupItem newGroup = new GroupItem(name, genre + " • 1 membro", 1);
                newGroup.isJoined = true; // Criador entra automaticamente
                groupList.add(0, newGroup);

                inputGroupName.setText("");
                inputGenre.setText("");
                renderGroups("");
                Toast.makeText(this, "Grupo '" + name + "' criado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        scroll.addView(root);
        setContentView(scroll);
    }

    // Método para desenhar os cards dos grupos na tela
    private void renderGroups(String filterText) {
        groupsContainer.removeAllViews();

        for (GroupItem group : groupList) {
            // Aplica filtro de busca pelo nome ou gênero
            if (filterText.isEmpty() || group.name.toLowerCase().contains(filterText) || group.genre.toLowerCase().contains(filterText)) {

                String subtitle = "Gênero: " + group.genre + " • " + group.members + " membros";
                LinearLayout cardGroup = Ui.card(this, group.name, subtitle);

                // Botão dinâmico Entrar / Sair
                Button btnAction;
                if (group.isJoined) {
                    btnAction = Ui.button(this, "✓ Participando (Sair)");
                } else {
                    btnAction = Ui.button(this, "➕ Participar do Grupo");
                }

                btnAction.setOnClickListener(v -> {
                    if (group.isJoined) {
                        group.isJoined = false;
                        group.members--;
                        Toast.makeText(this, "Você saiu do grupo " + group.name, Toast.LENGTH_SHORT).show();
                    } else {
                        group.isJoined = true;
                        group.members++;
                        Toast.makeText(this, "Você entrou no grupo " + group.name + "!", Toast.LENGTH_SHORT).show();
                    }
                    renderGroups(filterText); // Atualiza a tela
                });

                cardGroup.addView(btnAction);
                groupsContainer.addView(cardGroup);
            }
        }
    }
}