package com.nytimesmostpopular.mostviewdPresenter;


import com.nytimesmostpopular.api.ApiInteractor;
import com.nytimesmostpopular.api.ApiInteractorImpl;
import com.nytimesmostpopular.model.mostpopular.NycMostPopularRequest;
import com.nytimesmostpopular.network.ApiClient;

/**
 * Created by Dilip Birajadar on 2019-12-15.
 */
public class MostPopularInteractorImpl implements MostPopularInteractor {

    @Override
    public void mostPopularApiRequest(NycMostPopularRequest nycMostPopularRequest, ApiInteractor.OnApiResultCallback onLoginResultCallback) {
        ApiClient.appApiService().mostPopular().enqueue(new ApiInteractorImpl.FNApiCallback<String>(onLoginResultCallback));

    }
}
