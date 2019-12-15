package com.nytimesmostpopular.mostviewdPresenter;


/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public interface iMostPopularView {
    void callMostPopularApi();
    void mostPopularApiResponse(String o);
    void mostPopularApiErrorResponse(String response, int code);
    void showLoadingBar();
    void hideLoadingBar();
}
