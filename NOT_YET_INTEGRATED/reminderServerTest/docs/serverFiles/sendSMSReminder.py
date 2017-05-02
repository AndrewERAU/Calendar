#!/usr/bin/env python

import AWS_SNS_API as sns
import sys

eventTitle =  sys.argv[1]
sendToNumber = sys.argv[2] 

sns.sendSMS(eventTitle)
