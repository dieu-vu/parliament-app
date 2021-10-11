# Parliament App
This project is done as part of the course  Object-Oriented Programming and Datacommunication at Metropolia UAS.
Using Kotlin in Android Studio.
Short description: A simple android app to search and view Parliament member information. User can add reactions and comments which are stored in device memory.
Data source provided by Peter Hjort in [link](https://users.metropolia.fi/~peterh/mps.json)

## Implementations:
  - Use retrofit and moshi libraries for reading the data from a public url. Store the data in a Room database in the Android device.<br>
  -  Use WorkManager to regularily update the data every 6 hours in the background, with constraint of battery level, current usage and network meter.
  - Define class(es) to store reaction grades and comments and create Room database tables from them.
  - Implement a caching mechanism to avoid repeadtedly accessing network for the same data using Glide.
  - Implement repository class(es) for database / image store access. 
  - Implement app views as fragments and use navigation mechanism to move from a view to another. Implement a view model for each view and use binding mechanism. <br>
  When displaying collections of information use RecyclerView. 
  - Use live data to update views from database / image storage. 
  - Store notes, reactions in device’s  database

## Features:
- [x] From Home screen we can navigate to view party list or search a member
- [x] Party List -> Navigate to Party Member List -> Navigate to Member details
- [x] Search Member-> Navigate to Member details. Can view next member within the same party from member details too.
- [x] Member Details with formatted info and image. View Next Member button navigate to next member within the same party
- [x] External link to social media. Twitter logo is hidden for members who don’t have links.
- [x] Reactions & comments stored in data table. Reaction scores are updated lively after thumbs up and down buttons are clicked. Comment are sorted from newest to oldest in a recycler view under the comment edit text.
- [x] Can navigate to search fragment from the menu in other screens

## References:
- Teaching materials by course teachers: Peter Hjort and Petri Vesikivi
- Materials in the course [Developing Android Apps with Kotlin](https://www.udacity.com/course/developing-android-apps-with-kotlin--ud9012)
- StackOverflow, Medium posts
