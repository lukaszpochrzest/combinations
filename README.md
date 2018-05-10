# weighted-partitions

Simple application that generates equally weighted partitions

## Usage

Given an exemplary players.json input file consisting of a list of football players with their skills :

```ruby
[
  {
    "desc": "Player1",
    "weight": 5.0
  },
  {
    "desc": "Player2",
    "weight": 3.0
  },
  {
    "desc": "Player3",
    "weight": 4.0
  },
  {
    "desc": "Player4",
    "weight": 2.0
  }
]
```

Generating 2-player teams of equal skill sum:

```
java -jar weighted-partitions.jar players.json 2
```

gives an output


```
List([Player4, 2.0], [Player1, 5.0])
List([Player2, 3.0], [Player3, 4.0])

```

where each line represents a team.