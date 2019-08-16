from countryinfo import CountryInfo
import sys

country = sys.argv[1]
country_obj = CountryInfo(country)

caps = {'New Mexico': 'Santa Fe', 'Colorado': 'Denver', 'Vermont': 'Montpelier', 'Arizona': 'Phoenix', 'Iowa': 'Des Moines', 'Florida': 'Tallahassee', 'West Virginia': 'Charleston', 'Illinois': 'Springfield', 'Oregon': 'Salem', 'Kansas': 'Topeka', 'Arkansas': 'Little Rock', 'Kentucky': 'Frankfort', 'Massachusetts': 'Boston', 'Texas': 'Austin', 'Wyoming': 'Cheyenne', 'Maryland': 'Annapolis', 'New York': 'Albany', 'New Jersey': 'Trenton', 'Louisiana': 'Baton Rouge', 'South Carolina': 'Columbia', 'Georgia': 'Atlanta', 'Idaho': 'Boise', 'Alaska': 'Juneau', 'Oklahoma': 'Oklahoma City', 'Connecticut': 'Hartford', 'Nebraska': 'Lincoln', 'Tennessee': 'Nashville', 'Pennsylvania': 'Harrisburg', 'Michigan': 'Lansing', 'Maine': 'Augusta', 'Wisconsin': 'Madison', 'South Dakota': 'Pierre', 'California': 'Sacramento', 'New Hampshire': 'Concord', 'Indiana': 'Indianapolis', 'Washington': 'Olympia', 'Utah': 'Salt Lake City', 'Alabama': 'Montgomery', 'Minnesota': 'St. Paul', 'Hawaii': 'Honolulu', 'Ohio': 'Columbus', 'Missouri': 'Jefferson City', 'Delaware': 'Dover', 'Virginia': 'Richmond', 'Nevada': 'Carson City', 'Montana': 'Helena', 'North Dakota': 'Bismarck', 'Mississippi': 'Jackson', 'North Carolina': 'Raleigh', 'Rhode Island': 'Providence', 'Bahamas': 'Nassau', 'Andorra': 'Andorra la Vella', 'East Timor (Timor-Leste)': 'Dili', 'Serbia': 'Belgrade', 'Macedonia': 'Skopje', 'Gambia': 'Banjul'}

# with open('usstates.csv', 'r') as f:
#     for l in f:
#         l = l.strip()
#         state, cap = l.split(',')
#         caps[state] = cap


try:
    if country == 'Georgia':
        print(caps['Georgia'])
    elif country == 'San Marino':
        print('San Marino')
    elif country == 'Kiribati':
        print('Tarawa')
    elif country == 'Moldova':
        print('Chisinau')
    else:
        print(country_obj.capital())
except KeyError:
    print(caps[country])
