#!/usr/bin/python3

import asyncio
import websockets
from datetime import datetime
from datetime import timezone
import sys

async def test():
    async with websockets.connect('ws://challenges.ctfd.io:30065/') as websocket:
        resp = await websocket.recv()
        print(resp)
        await websocket.send("y")

        while True:
            resp = await websocket.recv()
            print(resp)
            if "batpwn" in resp.lower():
                sys.exit(0)
            year = int(resp.split("-")[0])
            month = int(resp.split("-")[1])
            day = int(resp.split("-")[2].split(" ")[0])
            hours = int(resp.split(" ")[1].split(":")[0])
            minutes = int(resp.split(" ")[1].split(":")[1])
            seconds = int(resp.split(" ")[1].split(":")[2])
            time = str(int(datetime(year, month, day, hours, minutes, seconds).replace(tzinfo=timezone.utc).timestamp()))
            print(time)
            await websocket.send(time)
 
asyncio.get_event_loop().run_until_complete(test())