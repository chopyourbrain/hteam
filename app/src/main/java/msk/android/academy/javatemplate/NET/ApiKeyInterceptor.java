package msk.android.academy.javatemplate.NET;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class ApiKeyInterceptor implements Interceptor {

    private static final String PARAM_API_KEY = "api-key";

    private final String apiKey;

    private ApiKeyInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }


    public static Interceptor create(String apiKey) {
        return new ApiKeyInterceptor(apiKey);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request requestWithoutApiKey = chain.request();

        final HttpUrl url = requestWithoutApiKey.url()
                .newBuilder()
                .addQueryParameter(PARAM_API_KEY, apiKey)
                .build();

        final Request requestWithAttachedApiKey = requestWithoutApiKey.newBuilder()
                .url(url)
                .build();

        return chain.proceed(requestWithAttachedApiKey);
    }
}