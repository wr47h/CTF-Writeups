import httplib
import re
import sys
import time

AWAY = "away"
TOO_FAST = "too fast"
CLOSER = "closer"

def drive(lat, lon, token):
    conn = httplib.HTTPSConnection("drivetothetarget.web.ctfcompetition.com")
    params = "/?lat=%.4f&lon=%.4f&token=%s" % (lat, lon, token)
    conn.request("GET", params)
    r1 = conn.getresponse()
    data = r1.read()
    # print data
    conn.close()

    res = re.findall(r'<p>([^<]+)</p>', data)[0]
    lat = re.findall(r'name="lat" value="([^"]+)"', data)[0]
    lon = re.findall(r'name="lon" value="([^"]+)"', data)[0]
    token = re.findall(r'name="token" value="([^"]+)"', data)[0]

    r = None
    if AWAY in res:
        r = AWAY
    if TOO_FAST in res:
        r = TOO_FAST
    if CLOSER in res:
        r = CLOSER

    if r is None:
        print data
        sys.exit()

    return r, res, float(lat), float(lon), token

# lat = 51.6498
# lon = 0.0983
lat = 51.4915
lon = -0.1945
token = "gAAAAABdD7TmGy_v7DkVKysnHPkZO8e4mC7bgsQzZTPYhLeAc8VYeM2eyZp7QBlnaTh_epEoxodoKCIWv79yFOXQ4mfOK5MXgWr41kNS00DSYV30Im97PchNQVY6VsA6oacxbUL-Vgki"

v = [[1, 0],
    [-1, 0],
    [0, 1],
    [0, -1]]

speed = 0.0001
i = 0
tm = 10

while True:
    nlat = lat + v[i%4][0]*speed
    nlon = lon + v[i%4][1]*speed
    r, res, lat, lon, token = drive(nlat, nlon, token)
    print r, res, lat, lon
    if r == AWAY:
        i += 1
        speed = 0.0001
        time.sleep(1)
        continue

    if r == CLOSER:
        speed = 0.0001
        time.sleep(1)
        continue

    if r == TOO_FAST:
        time.sleep(1)
        continue
