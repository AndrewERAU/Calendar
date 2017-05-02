#!/usr/bin/env python3

import sys

#example of the command line arguments passed to this program
# 9 7 1 5 1 -t 'It works!' --email <email address>  --sms <phone number>

# sys.argv[0] is ./verifyReminder.py
# sys.argv[1] is min (0-59)
# sys.argv[2] is hour (0-23)
# sys.argv[3] is day of month (1-31)
# sys.argv[4] is month (1-12)
# sys.argv[5] is day of week (0-6). (0 = Sunday)
# sys.argv[6] is event title
# sys.argv[7] is either '--email' or '--sms' or nothing
# sys.argv[8] is either the email address or the phone number
# sys.argv[9] is either '--email' or '--sms' or nothing
# sys.argv[10] is either the email address or the phone number

sendReminderArgs = ''
for index, argument in enumerate (sys.argv):
    if index >= 6:
        sendReminderArgs += ' ' +  argument

# Space at end is necessary to avoid EOF error when installing crontab
with open('newCronCmd.tmp', 'w') as f:
  f.write(sys.argv[1] + ' ' + sys.argv[2] + ' ' + sys.argv[3] + ' ' + sys.argv[4] + ' ' + sys.argv[5] + ' ./sendReminder.py  ' + sendReminderArgs + '\n')
