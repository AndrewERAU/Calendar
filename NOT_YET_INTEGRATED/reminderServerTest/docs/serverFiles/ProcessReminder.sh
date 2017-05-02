#if [ ./commandVerify.py ]; then

# writes command to a file 
./verifyReminder.py $SSH_ORIGINAL_COMMAND

# append command to crontab file
rm tmpCronFile # remove temp file if it alrealy exists
crontab -l >> tmpCronFile # read exisitng crontabs into file
cat newCronCmd.tmp  >> tmpCronFile; # append new crontab to existing crontabs
crontab tmpCronFile # intall crontab
rm tmpCronFile # remove temp file
rm newCronCmd.tmp

#else 
#    echo  "Call with line you want to add to crontab"
#    echo  'Example: * * * * * echo hi'
#    ./commandVerify.py $SSH_ORIGINAL_COMMAND 'arg2'
#fi
