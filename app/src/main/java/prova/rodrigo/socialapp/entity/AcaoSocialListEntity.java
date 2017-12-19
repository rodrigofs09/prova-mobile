package prova.rodrigo.socialapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rodrigo on 18/12/2017.
 */

public class AcaoSocialListEntity {
    @SerializedName("acoes_sociais")
    @Expose
    private List<AcaoSocialEntity> acoes = null;

    public List<AcaoSocialEntity> getAcoes() {
        return acoes;
    }

}
