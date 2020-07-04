package shortestRoute;

import java.awt.Point;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GSRequestPost {

    public static String baseURL = "https://script.google.com/macros/s/AKfycbxdmHck4-mUx6dqfoqyIKpRrBJundsMCQHa0hFmQaNw2M1LSQ/exec/";
    //public static UUID uuid0 = new UUID(1234, 901);// 各自、学籍番号などで違う値を利用してください。
    public void post(List<Point> points,int filename) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(/*gson*/))
                .build();
        GSService service = retrofit.create(GSService.class);

        Point[] test0 = new Point[points.size()];
        int count = 0;

        for (Point p : points) {
			test0[count] = p;
			count ++;
		}

        GSItem test= new GSItem(test0);

        UUID uuid0 = new UUID(1234,filename);

        System.out.println("Start Post:"+ uuid0.toString());
        Call<Void> repos = service.putItem(uuid0.toString(), Integer.toString(filename), test);
        repos.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("PutItem Success: " + response.message());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("PutItem Failed." );
            }
        });
     }

}
