import sqlite3
import datetime

con = sqlite3.connect("./db/sample.db")

cur = con.cursor()

# Delete all data from the sample database.  Will rebuild it fresh
cur.execute("""DROP TABLE IF EXISTS Event""")

cur.execute("""CREATE TABLE Event (
    EventID       INTEGER PRIMARY KEY,
    Title         TEXT    NOT NULL
                          CHECK (Title <> ''),
    Description   TEXT,
    Date          DATE    NOT NULL
                          CHECK (Date <> ''),
    StartTime     TIME,
    EndTime       TIME,
    Location      TEXT,
    Invitees      TEXT,
    Tag           TEXT,
    Reminder1Date DATE,
    Reminder1Time TIME,
    Reminder2Date DATE,
    Reminder2Time TIME
);
""")

#get current date
today = datetime.datetime.now().strftime("%Y-%m-%d") # ex) 2017-04-28

titles = ['Go to the grocery store','Play golf','Workout']
descriptions = ['Buy eggs and milk.','Go to Antelope Hills for 4 hours.','Every day is leg day']
startTimes = ['9:00','8:00','17:30']
endTimes = ['10:30','12:00','19:00']
locations = ['100 Supermarket Rd.','72 Antelop Ln.','1800 Swole St.']
invitees = ['','tigerwoods@example.com','josefRakich@example.com']
tags = ['food','golf practice','lift, get big, protein']
reminder1times = ['8:00','7:44','15:30']
reminder2times = ['8:30','8:00','16:15']


for i, _ in enumerate(titles):
    cur.execute("""INSERT INTO Event (
    Title        ,
    Description  ,
    Date         ,
    StartTime    ,
    EndTime      ,
    Location     ,
    Invitees     ,
    Tag          ,
    Reminder1Date,
    Reminder1Time,
    Reminder2Date,
    Reminder2Time
    ) Values (
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?
    )""", (titles[i],descriptions[i],today,startTimes[i],endTimes[i],locations[i],invitees[i],tags[i],today,reminder1times[i],today,reminder2times[i]))


con.commit()

cur.close()
