import boto3

client = None

myNumber = '' # put the number to send the text msg TO here

# not recommended to hardcode access keys
access_key_id='' # put your access key here
secret_access_key='' # put your secrect access key here
region='' # put your aws region here. ex) 'us-west-2'

def setup():
    global client
    # not recommended to hardcode
    client = boto3.client('sns',region_name=region,
                          aws_access_key_id=access_key_id,
                          aws_secret_access_key=secret_access_key)
    response = client.set_sms_attributes(
                   attributes={
                        'DefaultSMSType':'Transactional',
                        'MonthlySpendLimit': '1' #$USD
                    }
    )

def sendSMS(msgBody):
    if client == None:
        setup()

    response = client.publish(
                  PhoneNumber=myNumber,
                  Message=msgBody,
    )
    return response

