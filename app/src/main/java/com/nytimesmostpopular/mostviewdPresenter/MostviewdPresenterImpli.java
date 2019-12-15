package com.nytimesmostpopular.mostviewdPresenter;


import com.nytimesmostpopular.api.ApiInteractor;
import com.nytimesmostpopular.model.mostpopular.NycMostPopularRequest;

/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public class MostviewdPresenterImpli implements mostviewdPresenter,MostPopularInteractor.onMostPopularFinishListner {
    private iMostPopularView iMostPopularView;
    private MostPopularInteractorImpl mostPopularInteractor;

    public MostviewdPresenterImpli(iMostPopularView view) {
        this.iMostPopularView = view;
        this.mostPopularInteractor = new MostPopularInteractorImpl();
    }

    @Override
    public void mostPopularApiCall() {
        iMostPopularView.showLoadingBar();
        mostPopularInteractor.mostPopularApiRequest( new NycMostPopularRequest(), new ApiInteractor.OnApiResultCallback<String>() {
            @Override
            public void onCompleted(String response, int code) {
                iMostPopularView.hideLoadingBar();
                iMostPopularView.mostPopularApiResponse(response);
            }

            @Override
            public void onError(String errMsg, int code, Throwable t) {
                iMostPopularView.hideLoadingBar();
                iMostPopularView.mostPopularApiErrorResponse(errMsg,code);
            }

            @Override
            public void onNext(String response) {

            }
        });

    }

    @Override
    public void onMostPopularError(String errorMsg) {

    }

    @Override
    public void onSuccess(String value) {

    }

    @Override
    public void onFailure(String msg) {

    }
}
