package prova.rodrigo.socialapp.list_acao_social;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prova.rodrigo.socialapp.entity.AcaoSocialEntity;
import prova.rodrigo.socialapp.R;

/**
 * Created by Rodrigo on 18/12/2017.
 */

public class ListaAcaoSocialAdapter extends RecyclerView.Adapter<ListaAcaoSocialAdapter.AcaoSocialViewHolder> {

    OnRecyclerViewSelected onRecyclerViewSelected;

    // classe que mapeia componentes da view
    public class AcaoSocialViewHolder extends RecyclerView.ViewHolder {

        // bind do butterknife de texto e imagem
        @BindView(R.id.nomeAcaoSocial)
        TextView txtNomeAcao;

        @BindView(R.id.imagemAcaoSocial)
        ImageView imagemAcao;

        public AcaoSocialViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        // seta o clique rápido
        @OnClick(R.id.container)
        void onItemClick(View view){
            if(onRecyclerViewSelected != null)
                onRecyclerViewSelected.onClick(view, getAdapterPosition());

        }

    }

    private List<AcaoSocialEntity> acaoSocialList;
    private Context context;

    // Construtor que recebe a lista
    ListaAcaoSocialAdapter(List<AcaoSocialEntity> acaoSocialList, Context context) {
        this.acaoSocialList = acaoSocialList;
        this.context = context;
    }

    // infla o componente view
    @Override
    public AcaoSocialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.acao_social_item_list, parent, false);
        return new AcaoSocialViewHolder (v);
    }

    // seta os dados nas views
    @Override
    public void onBindViewHolder(final AcaoSocialViewHolder holder, final int position) {
        AcaoSocialEntity acaoSocialEntity  = acaoSocialList.get(position);
        holder.txtNomeAcao.setText(acaoSocialEntity.getName());
        Picasso.with(context)
                .load(acaoSocialEntity.getImage())
                .centerCrop()
                .fit()
                .into(holder.imagemAcao);
    }

    //retorna o tamanho da lista
    @Override
    public int getItemCount() {
        return acaoSocialList.size();
    }

    // seta clique
    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }
}
