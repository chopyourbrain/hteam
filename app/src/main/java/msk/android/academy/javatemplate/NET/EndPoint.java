package msk.android.academy.javatemplate.NET;


import io.reactivex.Single;
import msk.android.academy.javatemplate.DTO.RecipesResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndPoint {

    @GET("{recipe}.json")
    Single<RecipesResponse> search(@Path("recipe") String category);

}