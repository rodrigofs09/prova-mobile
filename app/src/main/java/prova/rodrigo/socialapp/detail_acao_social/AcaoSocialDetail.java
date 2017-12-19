package prova.rodrigo.socialapp.detail_acao_social;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prova.rodrigo.socialapp.R;
import prova.rodrigo.socialapp.entity.AcaoSocialEntity;

import static prova.rodrigo.socialapp.list_acao_social.ListaAcaoSocialMain.EXTRA_ACAO_SOCIAL;

/**
 * Created by Rodrigo on 18/12/2017.
 */

public class AcaoSocialDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acao_social_detail);

        ButterKnife.bind(this);

        //insere opção Up Action na ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //recebe os tipos passados por intent
        Intent intent = getIntent();
        acaoSocialEntity = (AcaoSocialEntity) intent.getSerializableExtra(EXTRA_ACAO_SOCIAL);

        //carrega imagem
        Picasso.with(this)
                .load(acaoSocialEntity.getImage())
                .centerCrop()
                .fit()
                .into(imagemAcaoSocial);

        //seta textviews
        nomeDescricao.setText(acaoSocialEntity.getName());
        descricaoAcaoSocial.setText(acaoSocialEntity.getDescription());

        getSupportActionBar().setTitle(nomeDescricao.getText());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @BindView(R.id.imagemAcaoSocial)
    ImageView imagemAcaoSocial;

    @BindView(R.id.text_view_description)
    TextView nomeDescricao;

    @BindView(R.id.descricaoAcaoSocial)
    TextView descricaoAcaoSocial;

    private AcaoSocialEntity acaoSocialEntity;

    //define o click no botao com uma intent para abrir o navegador
    @OnClick(R.id.button_site)
    public void onClickButtonSite(){
        Uri uri = Uri.parse(acaoSocialEntity.getSite());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}