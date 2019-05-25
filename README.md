# MyTestApp

Note: Implemented the api for Book author details. Because of the most popular article api not working.
http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=n4SnOGxdhdx0OMSjiGqDriLdkyG4GkI7

Used below api to implement the sample app with clean architecture.
https://api.nytimes.com/svc/books/v3/reviews.json?author=Stephen+King&api-key=n4SnOGxdhdx0OMSjiGqDriLdkyG4GkI7

Sample app to display a list of Book Author details and detailed page of each when clicked.

When the app is launched, you will be see a list of Books Author Details.
Clicking on a list item will bring you into the details of that Author details.

Display google dummy image url because of the existing response urls not working.

This app demonstrates the Clean Architecture + MVVM architectural pattern

### Libraries
**Dagger** is used to handle dependency injection.

**RxJava** is used to handle streams of data. Since this is a simple project, it is only lightly using RxJava. We use it as a Retrofit call adapter, and manipulate the data downstream. We also a BehaviorSubject wrapper called a StickyAction. This allows the View to observe changes in the ViewModel. A StickyAction handles nulls, and consumes the values when observed. A normal BehvaiorSubject will keep the latest value for any new observer to see.


**Databinding**
**Retrofit**
**GSON**
**RoomDB**
**Glide**