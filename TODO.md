ttyl MPV
--------
* create single page web/js page
    x ~~setup and serve index.html and site.js~~
    * Main page
        * ~~make ajax request to call api~~, display error
        * make call to representative API, we may have to end up hosting this ourselves. 

* make_call endpoint
    * make it connect two phone numbers

* SSL on Heroku -- non-negotiable
* Monitoring?

* Dev Environment
    * hot loading server. Got to be able to build and change things fast
        * Apparently literally impossible on JAVA, WTF

* Use React on the front end
    * clone the current stuff (should be cleaner)
    * have building the java server also build the client and put the files in the right place
    * see wether to ship this to other people we need to eject

MPV +1
------
* Scheduling
    * scheduled_call endpoint POST/GET/PUT/DELETE
        * add a scheduling database record
        * If they've never confirmed their number before, don't let them schedule more than one call until they do.

    * confirm-number endpoint
        * when someone schedules a call, we text them to verify that they asked for it

    * Frontend
        * create call scheduling page
            * should be able to delete a scheduled call
            * need a date-picker
                * can just be restricted to a week in advace

    * Scheduler
        * run all the time, scheudle calls whose time has come
        * just calls the make_call API?


PUBLIC USE BLOCK SHIPS
----------------------
* Talk To A Lawyer
* TOS?
* Privacy Page
* SSL Everywhere


SOMEDAY MAYBE
-------------
* accounts
    * lets you skip the verify step from then on
    * Once you click a verification link, you are logged in. No other login service.
        * Can we log you into your desktop session as well? 
            * could be abused, we'd have to have a "log me out everywhere" button
    * saves your ZIP, too
    * lets you see future scheduled calls
* Recurring scheduled calls
* schedule a row of calls. Every time one hangs up we start the next.
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
* Don't let anyone use this to spam texts to an enemy
* Don't let us accidetially use Twillio to make thousands of calls to one number at once




REFERENCE
---------
http://mobileinputtypes.com -- helpful list of mobile input types. 
