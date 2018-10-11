package com.example.allan.appalpharead;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RankingFrament extends Fragment {
    private EditText word;
    private TextView response;
    private Button btn;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        word = view.findViewById(R.id.TextT);
        response = view.findViewById(R.id.TextRT);
        btn = view.findViewById(R.id.btnTest);
        /*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response.setText(word.getText().toString());
            }});
        */
         btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String resposta = getDict(word.getText().toString());
                    response.setText(resposta);
                } catch (IOException e) {
                    response.setText("Deu ruim!");
                    e.printStackTrace();
                }
            }

            private String getDict(String word) throws IOException {
                String link = "http://dicionario-aberto.net/search-json/" + word;
                URL url = new URL(link);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                StringBuilder content;

                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
                    String line;
                    content = new StringBuilder();
                    while ((line = in.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                    System.out.println(content.toString());
                } finally {
                    con.disconnect();
                }
                return content.toString();
            }
        });
        return view;
    }
}
