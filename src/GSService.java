package shortestRoute;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface GSService {
   @GET("exec?type=item")
   Call<GSResult<GSItem>> getItem(@Query("key") String key);

   @GET("exec?type=list")
   Call<GSResult<List<String>>> list();

   @POST("exec") @Multipart
   Call<Void> putItem(@Part("key") String key,
                   @Part("text") String text,
                   @Part("json") GSItem json);

}
