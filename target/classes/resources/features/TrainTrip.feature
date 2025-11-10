Story: Find out what time the next trains for my destination station leave
Narrative:
In order to plan my trips more effectively
As a commuter
I want to know the next trains going to my destination

Scenario: Find the optimal itinerary between stations on the same line
Given Western line trains from Emu Plains leave Parramatta
for Town Hall at 7:58, 8:00, 8:02, 8:11, 8:14, 8:21
When I want to travel from Parramatta to Town Hall at 8:00
Then I should be told about the trains at: 8:02, 8:11, 8:14

Story: Tell travellers when they will arrive at their destination
Narrative:
In order to plan my voyage more effectively
As a commuter
I want to know what time I will arrive at my destination

Scenario Outline: Calculate arrival times
Given I want to go from <departure> to <destination>
And the next train leaves at <departure-time> on <line> line
When I ask for my arrival time
Then the estimated arrival time should be <arrival-time>

Examples:
| departure  | destination | departure-time | line      | arrival-time |
| Parramatta | Town Hall   | 8:02           | Western   | 8:29         |
| Epping     | Central     | 8:03           | Northern  | 8:48         |
| Epping     | Central     | 8:07           | Newcastle | 8:37         |
| Epping     | Central     | 8:13           | Epping    | 8:51         |