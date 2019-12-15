# NYTimesMostPopular App
Developed a simple app to hit the NY Times Most Popular Articles API and dispaying a list of articles, that displaying details when items on the list are tapped (a typical master/detail app).
Once you click on the list item you will get detail page for it.


# Overview

The app does the following:

Display the list of most popular Artical with their details
Add a detail view to display more information about the selected most popular artical item from the list
Show ProgressBar before network request
Use MVP Designe Pattern for application.
Use AES SHA-256 Encryption Algorithm to pass data from one page to other. 

# To achieve this, there are five different components in this app:

MainActivity - Responsible for executing the API requests and retrieving the JSON and pass data to NytListAdapater

NytListAdapater - Responsible for the capture data from the Mainactivity and render in recyclerview.Once click on the Item pass the 
detail view url to Detail Page Activity.

mostpopular- This package having request and response model for the network request.

mostviewdPresenter- This package having view,presenter and interactor.

ApiClient- ApiClient is Retrofit network request with publick certificate pinning. 

ApiInterface- is a commaon newtwork call interface 

# Libraries
This app leverages two third-party libraries:

retrofit2 - For network requests
recyclerview - To diplsy data in list format. 

# For Test App
Clone or Download from this repo.
Open downlaoded project in android studio and just run.
