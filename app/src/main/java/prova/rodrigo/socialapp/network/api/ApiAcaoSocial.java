package prova.rodrigo.socialapp.network.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import prova.rodrigo.socialapp.entity.AcaoSocialListEntity;
import prova.rodrigo.socialapp.network.service.AcaoSocialService;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rodrigo on 18/12/2017.
 */

/**
 * Relaciona retrofit e json, traduzindo o arquivo json em um objeto java
 */
public class ApiAcaoSocial {

    /**
     * declaracao de instancia e serviço
     */
    private static ApiAcaoSocial instancia;
    private AcaoSocialService acaoSocialService;

    /**
     * construtor de instancia
     * @return instancia contendo a informacoes da api acao social
     */
    public static ApiAcaoSocial getInstance() {
        if (instancia == null)
            instancia = new ApiAcaoSocial();

        return instancia;
    }

    /**
     *Construtor que cria o retrofit
     */
    private ApiAcaoSocial(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://dl.dropboxusercontent.com/")
                .addConverterFactory(defaultConvertFactory())
                .build();

        this.acaoSocialService = retrofit.create(AcaoSocialService.class);
    }

    /**
     * funcao para criar gson
     * @return gson a ser adicionado ao retrofit
     */
    private Converter.Factory defaultConvertFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("dd-MM-yyyy'T'HH:mm:ss")
                .create();
        return GsonConverterFactory.create(gson);
    }

    /**
     * funcao para obter acoes sociais
     * @return acoes sociais
     */
    public Call<AcaoSocialListEntity> getAcoesSociais(){
        return acaoSocialService.getAcoesSociais();
    }

}
