#!/usr/bin/python3

# Modified from
# https://www.tutorialspoint.com/python3/python_command_line_arguments.htm
import sys, getopt
import subprocess

def main(argv):
   emailAddress = ''
   phoneNumber = ''
   eventTitle = ''
   try:
      opts, args = getopt.getopt(argv,"he:s:t:",["email=","sms=","title="])
   except getopt.GetoptError:
      print ('./sendReminder.py -t <eventTitle> -e <emailAddress> -s <phoneNumber>')
      sys.exit(2)
   for opt, arg in opts:
      if opt == '-h':
         print ('./sendReminder.py -t <eventTitle> -e <emailAddress> -s <phoneNumber>')
         sys.exit()
      elif opt in ("-e", "--email"):
         emailAddress = arg
      elif opt in ("-s", "--sms"):
         phoneNumber = arg
      elif opt in ("-t", "--title"):
         eventTitle = arg
   if (emailAddress != ''):
       subprocess.call(['./sendEmailReminder.py', eventTitle, emailAddress])
   if (phoneNumber != ''):
       subprocess.call(['./sendSMSReminder.py', eventTitle, phoneNumber])

if __name__ == "__main__":
   main(sys.argv[1:])
