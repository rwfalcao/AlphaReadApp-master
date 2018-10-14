package com.example.allan.appalpharead;

import com.example.allan.appalpharead.models.DicionarioOnline;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DicionarioService {

    String BASE_URL= "http://dicionario-aberto.net/";

    @GET("search-json/palavra")

    Call<DicionarioOnline> searchWord();
}
