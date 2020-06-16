#HSL MQTT

🇫🇮 Tämä Java-sovellus kytkeytyy Helsingin seudun liikenteen (HSL) [reaaliaikaiseen Digitransit-rajapintaan](https://digitransit.fi/en/developers/apis/4-realtime-api/vehicle-positions/), 
josta voi seurata HSL-alueen julkisen liikenteen ajoneuvojen tapahtumia, kuten sijaintipäivityksiä.

🇬🇧 This Java application connects to the [real time Digitransit API](https://digitransit.fi/en/developers/apis/4-realtime-api/vehicle-positions/) 
of the Helsinki Regional Transport Authority (HSL). From the API one can follow the events, such 
as location updates, of the public transport vehicles of the HSL area.

##Usage

This is a command line application that requires two or more parameters to start:

1. Speed limit in kilometers per hour. Entering a speed limit will print events with speeds
   above the limit into the standard error stream, instead of standard out. Enter some high
   value such as "999" if you are not interested in the speed limits.
2. One or more MQTT topics. These are the most important parameters and can be used to define
   the geographic area to watch. The format is quite tricky, see the API documentation again
   for further reference.
   
To summarize, one valid set of command line arguments could look like this:

    40 "/hfp/v2/journey/ongoing/+/+/+/+/+/+/+/+/+/+/60;24/19/85/#"
    
And it would yield an output that would be similar to this:

```
2020-06-16T21:54:42.592Z	VEHICLE_POSITION	66K	(Oy Pohjolan Liikenne Ab)	v=27.2 km/h	a=-0.15 m/s²	heading=234⁰	at N 60.180925, E 24.952562	offset from schedule: -1 s	startTime: 00:31	routeId: 1066K
2020-06-16T21:54:42.693Z	VEHICLE_POSITION	66K	(Oy Pohjolan Liikenne Ab)	v=20.7 km/h	a=0.16 m/s²	heading=53⁰	at N 60.182122, E 24.956284	offset from schedule: -5 s	startTime: 00:50	routeId: 1066K
2020-06-16T21:54:42.731Z	ARRIVES_TO_STOP	9	(HKL-Raitioliikenne)	v=21.6 km/h	a=-0.27 m/s²	heading=357⁰	at N 60.185959, E 24.951601	offset from schedule: 166 s	startTime: 00:40	routeId: 1009
2020-06-16T21:54:42.820Z	VEHICLE_POSITION	9	(HKL-Raitioliikenne)	v=22.2 km/h	a=-0.27 m/s²	heading=357⁰	at N 60.185959, E 24.951601	offset from schedule: 197 s	startTime: 00:40	routeId: 1009
2020-06-16T21:54:42.623Z	VEHICLE_POSITION	64	(Oy Pohjolan Liikenne Ab)	v=27.2 km/h	a=0.21 m/s²	heading=214⁰	at N 60.184252, E 24.959783	offset from schedule: -9 s	startTime: 00:35	routeId: 1064
2020-06-16T21:54:43.256Z	VEHICLE_POSITION	87N	(Nobina Finland Oy)	v=4.8 km/h	a=1.08 m/s²	heading=54⁰	at N 60.180222, E 24.959274	offset from schedule: -15 s	startTime: 00:50	routeId: 1087N
2020-06-16T21:54:43.592Z	VEHICLE_POSITION	66K	(Oy Pohjolan Liikenne Ab)	v=25.7 km/h	a=-0.42 m/s²	heading=234⁰	at N 60.180889, E 24.952459	offset from schedule: -1 s	startTime: 00:31	routeId: 1066K
2020-06-16T21:54:43.623Z	VEHICLE_POSITION	64	(Oy Pohjolan Liikenne Ab)	v=28.0 km/h	a=0.2 m/s²	heading=213⁰	at N 60.184193, E 24.959713	offset from schedule: -9 s	startTime: 00:35	routeId: 1064
2020-06-16T21:54:43.693Z	VEHICLE_POSITION	66K	(Oy Pohjolan Liikenne Ab)	v=20.7 km/h	a=0.16 m/s²	heading=53⁰	at N 60.182122, E 24.956284	offset from schedule: -5 s	startTime: 00:50	routeId: 1066K
2020-06-16T21:54:43.820Z	VEHICLE_POSITION	9	(HKL-Raitioliikenne)	v=19.6 km/h	a=-0.71 m/s²	heading=357⁰	at N 60.186006, E 24.951599	offset from schedule: 197 s	startTime: 00:40	routeId: 1009
2020-06-16T21:54:44.256Z	VEHICLE_POSITION	87N	(Nobina Finland Oy)	v=10.2 km/h	a=1.49 m/s²	heading=51⁰	at N 60.180248, E 24.95933	offset from schedule: -15 s	startTime: 00:50	routeId: 1087N
2020-06-16T21:54:44.623Z	VEHICLE_POSITION	64	(Oy Pohjolan Liikenne Ab)	v=29.1 km/h	a=0.31 m/s²	heading=213⁰	at N 60.184133, E 24.959636	offset from schedule: -9 s	startTime: 00:35	routeId: 1064
2020-06-16T21:54:44.693Z	VEHICLE_POSITION	66K	(Oy Pohjolan Liikenne Ab)	v=18.1 km/h	a=-0.7 m/s²	heading=55⁰	at N 60.182134, E 24.956365	offset from schedule: -5 s	startTime: 00:50	routeId: 1066K
2020-06-16T21:54:44.592Z	VEHICLE_POSITION	66K	(Oy Pohjolan Liikenne Ab)	v=25.7 km/h	a=0.0 m/s²	heading=234⁰	at N 60.180854, E 24.952364	offset from schedule: -1 s	startTime: 00:31	routeId: 1066K
2020-06-16T21:54:44.746Z	ARRIVED_TO_STOP	9	(HKL-Raitioliikenne)	v=10.8 km/h	a=-1.49 m/s²	heading=359⁰	at N 60.186037, E 24.951605	offset from schedule: 197 s	startTime: 00:40	routeId: 1009
2020-06-16T21:54:44.820Z	VEHICLE_POSITION	9	(HKL-Raitioliikenne)	v=14.3 km/h	a=-1.49 m/s²	heading=359⁰	at N 60.186037, E 24.951605	offset from schedule: 195 s	startTime: 00:40	routeId: 1009
2020-06-16T21:54:45.257Z	VEHICLE_POSITION	87N	(Nobina Finland Oy)	v=16.1 km/h	a=1.68 m/s²	heading=40⁰	at N 60.180309, E 24.959391	offset from schedule: -15 s	startTime: 00:50	routeId: 1087N
```