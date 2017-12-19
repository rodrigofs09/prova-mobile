package prova.rodrigo.socialapp.list_acao_social;

import com.google.gson.Gson;

import prova.rodrigo.socialapp.entity.AcaoSocialListEntity;
import prova.rodrigo.socialapp.network.api.ApiAcaoSocial;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rodrigo on 18/12/2017.
 */

/**
 * camada para tratar parte logica da view
 */
public class ListaAcaoSocialPresenter {

    /**
     * interface para evitar acesso a metodos que nao sao necessarios
     */
    private ListaAcaoSocialView listaAcaoSocialView;

    /**
     * lista de dados
     */
    AcaoSocialListEntity acaoSocialListEntity;

    /**
     * construtor da camada de view do mvp
     * @param listaAcaoSocialView
     */
    public ListaAcaoSocialPresenter(ListaAcaoSocialView listaAcaoSocialView){
        this.listaAcaoSocialView = listaAcaoSocialView;
    }

    /**
     * funcao para atualizar lista com informacoes do site
     */
    void updateLista(){

        listaAcaoSocialView.showLoading();

        final ApiAcaoSocial apiAcaoSocial = ApiAcaoSocial.getInstance();

        /**
         * metodo get para adicionar arquivo json na api
         */
        apiAcaoSocial.getAcoesSociais().enqueue(new Callback<AcaoSocialListEntity>() {
            @Override
            public void onResponse(Call<AcaoSocialListEntity> call, Response<AcaoSocialListEntity> response) {
                AcaoSocialListEntity acaoSocialListEntity = response.body();
                // tratamento em caso da lista nao vazia
                if(acaoSocialListEntity != null){
                    listaAcaoSocialView.updateList(acaoSocialListEntity.getAcoes());
                } else{
                    listaAcaoSocialView.showMessage("Falha no acesso ao servidor");
                }
                listaAcaoSocialView.hideLoading();
            }

            /**
             * erro ao carregar lista
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<AcaoSocialListEntity> call, Throwable t) {
                listaAcaoSocialView.hideLoading();
                listaAcaoSocialView.showMessage("Falha no acesso ao servidor");
            }
        });
    }

    /**
     * funcao para salvar dados no shared preferences
     */
    public void saveDados() {
        String jsonAcoesSociaisEntity = new Gson().toJson(acaoSocialListEntity);
        listaAcaoSocialView.saveAcaoSocialSharedPreferences(jsonAcoesSociaisEntity);
    }
}
