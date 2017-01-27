ttyl MPV
--------
* create single page web/js page
    * ~~setup and serve index.html and site.js~~
    * Main page
        * ~~make ajax request to call api~~, display error
        * make call to representative API, we may have to end up hosting this ourselves. 

* security setup
    * when someone makes a call, we text them to verify that they asked for it?
        * this maybe can wait until we're scheduling? But we gotta have it before shipping. 

* call endpoint
    * make it connect two phone numbers

* SSL on Heroku -- non-negotiable

* Dev Environment
    * hot loading server. Got to be able to build and change things fast

MPV +1
------
* Scheduling
    * call endpoint
        * add a scheduling database record
        * run a background process that processes schedules, makes calls

    * Frontend
        * create call scheduling page
            * should be able to delete a scheduled call



SOMEDAY MAYBE
-------------
* accounts
    * lets you skip the verify step from then on
* Recurring scheduled calls
* Reminders for scripts for what to say when you call
* subscribing to notifications for things to call about
* make the zip code picker zip specific instead of number specific



GOAL
====
A webpage that schedules a call with one of your representatives for later





UI
==

Main Page
---------
* A form
    * Zip Code (or address) (location services?)
        * Once entered, we give a list of reps, you select 1
    * Phone number field
        * We should save this in local storage, in case you visit from the same browser again.
            * If it's a public machine, though...
    * Date picker
        * only let you pick the next week. You're not going to remember what the call is in a month.
    * Go button
        * if it works, takes you to call page.
            * OR -- texts you a link to the call page. saying "nothing is scheduled until you verify by clicking that link"
        * if if fails, you stay here with an error message.


Call Schedule Page
------------------
* UUID in the URL
* Shows you who and when
* lets you change when
* lets you cancel


Verification
------------
* When you schedule, we text you a link to the schedule page, you have to click that to actually get the call. 


SECURITY CONCERNS
=================
* Let's not keep any data on when you called people before. What's the point? 
* Let's not keep any data we don't have to. Your info is on our servers while things are scheduled, then gone




REFERENCE
---------
http://mobileinputtypes.com -- helpful list of mobile input types. 
