package prova.rodrigo.socialapp.list_acao_social;

import java.util.List;

import prova.rodrigo.socialapp.entity.AcaoSocialEntity;

/**
 * Created by Rodrigo on 18/12/2017.
 */

interface ListaAcaoSocialView {

    /**
     * funcao para adicionar dados do adapter na recycler view, sem ela a lista da erro de null exception
     */
    void AdicionaListaRecycler();

    /**
     * funcao para atualizar dados da lista conforme obtidos do site
     * @param acaoSocialLista
     */
    void updateList(List<AcaoSocialEntity> acaoSocialLista);

    /**
     * funcao para exibir mensagens
     * @param msg
     */
    void showMessage(String msg);

    /**
     * funcao para exibir que o aplicativo esta carregando informacoes
     */
    void showLoading();

    /**
     * funcao para remover a tela que indica que o aplicativo esta carregando informacoes
     */
    void hideLoading();

    /**
     * funcao para armazenar informacoes json no shared preferences
     * @param jsonAcoesSociaisEntity
     */
    void saveAcaoSocialSharedPreferences(String jsonAcoesSociaisEntity);
}
