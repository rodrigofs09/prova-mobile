package prova.rodrigo.socialapp.network.service;

import prova.rodrigo.socialapp.entity.AcaoSocialListEntity;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rodrigo on 18/12/2017.
 */

public interface AcaoSocialService {
    /**
     * metodo retrofit  para acessar url do json
     * @return lista de acoes sociais do json
     */
    @GET("s/50vmlj7dhfaibpj/sociais.json")
    Call<AcaoSocialListEntity> getAcoesSociais();

}
