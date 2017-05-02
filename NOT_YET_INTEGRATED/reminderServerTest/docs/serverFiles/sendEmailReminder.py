#!/usr/bin/env python

import sys

fromEmail = '' # put your email address to send email from here
username = fromEmail
password = '' # put the password for your email account here

emailSMTPServer = '' #ex for yahoo) 'smtp.mail.yahoo.com'
SMTPPORT = '' #ex for yahoo) '587'

emailSubject='test' # put the email subject here

eventTitle =  sys.argv[1]
sendToEmail = sys.argv[2] 


# FROM https://docs.python.org/2/library/email-examples.html
# Import smtplib for the actual sending function
import smtplib

# Import the email modules we'll need
from email.mime.text import MIMEText


# Create a text/plain message
msg = MIMEText(eventTitle)


msg['Subject'] = emailSubject
msg['From'] = fromEmail
msg['To'] = sendToEmail


s = smtplib.SMTP()
s.connect(emailSMTPServer,SMTPPORT)
s.ehlo()
s.starttls()
s.login(username,password)
s.sendmail(fromEmail, [sendToEmail], msg.as_string())
s.quit()

