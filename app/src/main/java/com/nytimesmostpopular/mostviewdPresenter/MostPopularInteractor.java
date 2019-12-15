package com.nytimesmostpopular.mostviewdPresenter;




import com.nytimesmostpopular.api.ApiInteractor;
import com.nytimesmostpopular.model.mostpopular.NycMostPopularRequest;

/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public interface MostPopularInteractor {
    interface onMostPopularFinishListner {

        void onMostPopularError(String errorMsg);
        void onSuccess(String value);
        void onFailure(String msg);

    }

    void mostPopularApiRequest(NycMostPopularRequest nycMostPopularRequest, ApiInteractor.OnApiResultCallback onLoginResultCallback);

}
