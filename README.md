# PowderGuru

#Short: 
App that lets users search for cities and returns (ski) weather forecast for the next day.

#Long:
On the launcer activity (login) Powder guru lets user log in with his email and password. When the user does not have an account yet,
he can navigate to the Signup activity and register here (authentication with Firebase). When registering is succesful, user is sent to
main activity. When logging in is succesful, user is also sent to Main activity. 

In the Main activity the user can enter a city name, or a cityname and a countryname. When asynctasks are completed, user is navigated to 
the Predictions Activity where info about temperature, chance of sunshine and chance of snowfall is shown. From this activity (Predictions),
user can navigate to the SavedSpots activity, or save the current city. Saving it creates a new city object, that will be shown (together 
with all the others) in SavedSpots activity. In Saved Activity, the user can click on each city to get the latest forecasts. User can also
check each item (when already visited the place), or delete an item by holding

# Java files:
-Cityobj
-DBhelper
-DBmanager
-Httphelper
-Listadapter
-LoginActivity
-MainActivity
-PredictionsActivity
-SignupActivity
-SnowAsynctasks
-SpotsActivity
