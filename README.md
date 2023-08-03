# MovieFlix_Android_MobileApp

Android Movie Tracker and IMDB Lookup App

This Android application is designed to help users keep track of the movies they have watched, organize them, and access information about them from the IMDB database. The app adheres to the following specifications:

Home Screen:
Upon launching the app, the user is presented with a home screen containing six buttons:
a) Register Movie: Allows the user to enter details of a movie they have watched, such as title, year, director, actors/actresses, rating, and review. The information is saved in an SQLite database.
b) Display Movies: Shows a list of registered movies sorted alphabetically by title. Users can see one movie per line along with a tickbox next to each movie. The tickbox indicates whether the movie is added to the favorites list.
c) Favourites: Displays a list of all favorite movies. Users can deselect any favorite movie by unchecking the tickbox and saving the changes.
d) Edit Movies: Shows a list of all watched movies, whether they are favorites or not. Users can click on a movie title to view its details and edit any information, including the favorite status and rating.
e) Search: Allows users to search for movies by typing a string in a textbox. The app displays all movies containing the typed string in the title, director, or actors' fields.
f) Ratings: Enables users to view the IMDb ratings of selected movies. Users can choose a movie from the list and click the "Find movie in IMDB" button to fetch and display matching titles and their ratings from the IMDb database.

Register Movie:
a) Users can enter the title, year, director, actors/actresses (comma-separated), rating (1-10 stars), and review of the movie.
b) The year textbox only accepts numeric values greater than 1895.
c) The rating textbox only accepts numeric values between 1 and 10.
d) The Save button saves the movie details in the local SQLite database.

Display Movies:
a) Shows a list of movies sorted alphabetically by title.
b) Each movie has a tickbox that indicates whether it is added to the favorites list.
c) Users can use scrollbars if the list of movies is more than one page long.
d) The Add to Favourites button allows users to add selected movies to the favorites list.

Favourites:
a) Displays a list of all favorite movies.
b) Users can deselect any favorite movie by unchecking the tickbox and saving the changes.

Edit Movies:
a) Shows a list of all watched movies, including favorites.
b) Users can click on a movie title to view its details.
c) Users can edit any movie information, including the favorite status and rating.
d) The Update button saves the edited details in the database.
e) Users can edit the rating by clicking on stars to indicate their rating.

Search:
a) Users can enter a search string in the textbox to find movies with matching titles, directors, or actors' names.
b) The search is case-insensitive and supports partial matches.

Ratings:
a) Users can select a movie title from the list.
b) Clicking the "Find movie in IMDB" button displays IMDb ratings for movies with matching titles.
