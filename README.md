# adventure

A Clojure library designed to ... well, that part is up to you.

## Usage

`lein run` will run the main function in interaction.core, which will launch the repl your coded
`quit` will end the game. If you want to quit the Game. But if you finished the game. It will quit automatically.

## Game Introduction

You have four canonical directions. North, South, West, East. You can also type "N, S, W, E". The direction will take you into a new room.

The Middle room is the Airport. You are born in South "Cleveland" NBA Team. You have to move north to Airport to choose which Team you want to fight. The North of Airport is "Los Angeles Lakers", the West is "Chicago Bulls" and the East is "Golden State Warriors". You can also go back South to "Cleveland" because some items have to be picked at "Cleveland".

'pick' something can help you pick the item. The items are used to win the game such as "shoot the ball" and etc.

You have to fight with three Teams "Lakers, Bulls, and Warriors". Remember, the sequence can not be changed. You must fight first with "Lakers", then "Bulls", finally "Warriors".

Once you beat all of the three teams. Go back to Cleveland. People are waiting for you. They want to celebrate your team and give you the NBA Reward--the NBA Ring--to you.

## Game Solutions
Initially, you are in "Cleveland". You first go "N" to Airport. Then "N" to "Lakers". When you arrived in Lakers, you need to type 'pick Tooth-Socket', then type 'put'. You beat the Lakers.

Then you go to "Bulls--the United Center". But you need "Water". Go back to "Cleveland". 'pick Water'. Then go to "Bulls" again. Type 'drink'. The next room you will go "N" to "playground". But you need a "Ball". So you should 'pick Ball' when you are in "Bulls--the United Center" room. In the playground 'shoot the ball'. You beat the Bulls.

Finally, go to "Warriors". But you need to wear "shirt". The shirt is in the Airport. Go back to Airport. 'pick Shirt'. Then go to "Warriors" again. Type 'wear'. You can go to the court. Just Type 'shoot'. You beat the Warriors.

The last thing is Congratulations. Go back to Cleveland. Type 'reward'. Then Game is done.

Good Luck!

## actions you can Type
n
s
w
e
look
i
pick Tooth-Socket
pick Ball
pick Shirt
pick Water
put, drink, shoot the ball, wear, shoot, reward, quit

'n, s, w, e' to move different direction.
'look' to see items around this room and get hints.
'i' will show you the items you have.
'pick Ball', 'pick Shirt', 'pick Water' to get items.
'put', drink', 'shoot the ball', 'wear', 'shoot' 'reward' 'quit' to perform actions.
## License

Copyright © 2019 Mattox Beckman

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
