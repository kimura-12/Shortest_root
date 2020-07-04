package shortestRoute;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GSRequestGet {

    public static String baseURL = "https://script.google.com/macros/s/AKfycbxdmHck4-mUx6dqfoqyIKpRrBJundsMCQHa0hFmQaNw2M1LSQ/exec/";
    //public static UUID uuid0 = new UUID(1234, 901);// 各自、学籍番号などで違う値を利用してください。
    public List<Point> get(int filename) throws IOException {
    	List<Point> points = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(/*gson*/))
                .build();
        GSService service = retrofit.create(GSService.class);

        UUID uuid0 = new UUID(1234,filename);

        {
            System.out.println("Start Sync Call");
            Call<GSResult<List<String>>> repos = service.list();
            Response<GSResult<List<String>>> response = repos.execute();
            if (response.isSuccessful()) {
                System.out.println("List: Success (Sync), " + response.body());
            } else {
                System.out.println("List: Failed (Sync), " + response.message());
            }
        }

        {
            System.out.println("Start Sync Call");
            Call<GSResult<GSItem>> repos;
			try {
				repos = service.getItem(uuid0.toString());
				Response<GSResult<GSItem>> response = repos.execute();
	            points = response.body().json.points;
	            if (response.isSuccessful()) {
	                System.out.println("GetItem: Success, " + response.body());
	            } else {
	                System.out.println("GetItem: Failed, " + response.message());
	            }
			} catch (Exception e) {
				System.out.println("ファイルが存在しません");
			}

        }

        return points;
    }

}
