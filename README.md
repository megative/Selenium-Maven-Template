Veriff Test Task for Quality Engineer
=======================

Hi everyone! My name is Pavel, and this is my test task for the position of Quality Engineer in Veriff.
I decided to use some existing template for this task - because it's much faster to develop this task using stuff like this.
I forked it from there - https://github.com/Ardesco/Selenium-Maven-Template - and implemented it for my own needs.

To be honest I'm not really good in testing tasks and I don't really love it.
Hope you will find my solution interesting and you'll find interesting my way of thinging.

Also I've got some code sample of my pet project, I can share it with you if you'll be interested in.

About this task:

UI Tests located /src/test/java/tests/ui
API Tests located /src/test/java/tests/api

I used Maven as a builder tool (it was here from template, actually).

To Start tests:

1. Open a terminal window/command prompt
2. Clone this project.
3. `cd Veriff-test-task` (Or whatever folder you cloned it into)
4. `mvn clean verify`

Before that you need to configure Java and Maven to start it. It should be cross-platformed, I developed and tested it using macOS.

If the tests fail screenshots will be saved in ${project.basedir}/target/screenshots - Only for UI Tests.
Call stacktrace and reports are available in ${project.basedir}/targer/surefire-reports
Complete report is available with running verification process with -X key. All the reports are human-readable (HTML-based).

### It's not working!!!

You have probably got outdated driver binaries, by default they are not overwritten if they already exist to speed things up.  You have two options:

- `mvn clean verify -Doverwrite.binaries=true`
- Delete the `selenium_standalone_binaries` folder in your resources directory
