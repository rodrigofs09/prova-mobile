package prova.rodrigo.socialapp.list_acao_social;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import prova.rodrigo.socialapp.entity.AcaoSocialEntity;
import prova.rodrigo.socialapp.detail_acao_social.AcaoSocialDetail;
import prova.rodrigo.socialapp.R;

public class ListaAcaoSocialMain extends AppCompatActivity implements ListaAcaoSocialView{

    /**
     * Binding do butterknife para recycler view e tela de loading
     */
    @BindView(R.id.rv_acoes_sociais)
    RecyclerView rvAcoesSociais;

    @BindView(R.id.linear_layout_loading)
    LinearLayout loadingLayout;

    // declara presenter, adapter e entity
    ListaAcaoSocialPresenter listaAcaoSocialPresenter;

    public ListaAcaoSocialAdapter adapterAcoes;
    private List<AcaoSocialEntity> acaoSocialList;

    /**
     * variavel que guarda objetos para passa-los entre actvities
     */
    public final static String EXTRA_ACAO_SOCIAL = "@string/extra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind butterknife
        ButterKnife.bind(this);

        // cria array list que ira armazenar dados
        acaoSocialList = new ArrayList<>();

        // seta adapter e layout na recycler view
        rvAcoesSociais.setAdapter(new ListaAcaoSocialAdapter(acaoSocialList, this));
        rvAcoesSociais.setLayoutManager(new LinearLayoutManager(this));

        // atualiza lista de informacoes
        listaAcaoSocialPresenter = new ListaAcaoSocialPresenter(this);
        listaAcaoSocialPresenter.updateLista();
    }

    /**
     * adiciona botao para salvar informacoes na action bar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * tratamento para clique no botao de salvar informacoes
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_download:
                // salvar as informações das acoes sociais nas SharedPreferences
                listaAcaoSocialPresenter.saveDados();

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * implementa lista de dados na recycler view
     */
    @Override
    public void AdicionaListaRecycler(){
        adapterAcoes = new ListaAcaoSocialAdapter(acaoSocialList, this);
        adapterAcoes.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                // trata o clique
                Intent intent = new Intent(ListaAcaoSocialMain.this, AcaoSocialDetail.class);
                intent.putExtra(EXTRA_ACAO_SOCIAL, acaoSocialList.get(position));
                startActivity(intent);
            }
        });

        rvAcoesSociais.setAdapter(adapterAcoes);

        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        rvAcoesSociais.setLayoutManager(layoutManager);
        rvAcoesSociais.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void updateList(final List<AcaoSocialEntity> acaoSocialLista) {

        acaoSocialList = acaoSocialLista;

        //seta o adapter
        adapterAcoes = new ListaAcaoSocialAdapter(acaoSocialList, this);
        adapterAcoes.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent
                        (ListaAcaoSocialMain.this,
                                AcaoSocialDetail.class);
                intent.putExtra(EXTRA_ACAO_SOCIAL, acaoSocialList.get(position));

                startActivity(intent);

            }

        });

        rvAcoesSociais.setAdapter(adapterAcoes);

        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        rvAcoesSociais.setLayoutManager(layoutManager);
        rvAcoesSociais.addItemDecoration(dividerItemDecoration);
    }

    // metodo para exibir mensagem
    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // metodo para mostrar tela de loading
    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    // metodo para esconder tela de loading
    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

    // metodo para salvar dados no shared preferences
    @Override
    public void saveAcaoSocialSharedPreferences(String jsonAcoesSociaisEntity) {
        SharedPreferences.Editor editor =
                getSharedPreferences("acoes_sociais_json", MODE_PRIVATE).edit();

        editor.putString("acao_entity_json", jsonAcoesSociaisEntity);

        editor.apply();

        showMessage("Informações salvas com sucesso");

    }

}
