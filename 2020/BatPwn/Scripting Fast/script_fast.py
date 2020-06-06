#!/usr/bin/python3

import asyncio
import websockets
from datetime import datetime
from datetime import timezone

async def test():
    async with websockets.connect('ws://challenges.ctfd.io:30065/') as websocket:
        response = await websocket.recv()
        print(response)
        await websocket.send("y")
        while True:
            response = await websocket.recv()
            print(response)
            if "batpwn" in response.lower() or "flag" in response.lower():
                exit(0)
            year = int(response.split("-")[0])
            month = int(response.split("-")[1])
            day = int(response.split("-")[2].split(" ")[0])
            hours = int(response.split(" ")[1].split(":")[0])
            minutes = int(response.split(" ")[1].split(":")[1])
            seconds = int(response.split(" ")[1].split(":")[2])
            trying = str(int(datetime(year, month, day, hours, minutes, seconds).replace(tzinfo=timezone.utc).timestamp()))
            print(f"[+] Seding: {trying}")
            await websocket.send(trying)
 
asyncio.get_event_loop().run_until_complete(test())