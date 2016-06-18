# HATman
_Project Work 1 - Gamedevelopment with Python_


#Team
* Johanna Kirchmaier
* Anja Bergmann
* Tabea Halmschlager


#Dependencies: 
* Python 3
* Cocos2D
	> `pip install cocos2D`
* Twisted
	> `pip install twisted`
	> `pip install pypiwin32`
	> `pip install twisted-win`


# Setup
TBA



#Probably useful links:
* Python Plugin for Eclipse
	- [http://www.till.net/Themen/plone-zope-python/python-entwicklung-mit-eclips](http://www.till.net/Themen/plone-zope-python/python-entwicklung-mit-eclipse) 
* Cocos2d
	- [http://cocos2d.org/](http://cocos2d.org/)
	- [http://python.cocos2d.org/](http://python.cocos2d.org/)
* Multiplayer game programming for teens with python
	- [https://www.raywenderlich.com/38732/multiplayer-game-programming-for-teens-with-python](https://www.raywenderlich.com/38732/multiplayer-game-programming-for-teens-with-python)
	- [https://www.raywenderlich.com/38732/multiplayer-game-programming-for-teens-with-python-part-2]( https://www.raywenderlich.com/46843/multiplayer-game-programming-for-teens-with-python-part-2)
* Sockets and multiplayer in python
	- [http://stackoverflow.com/questions/25261552/multi-multiplayer-server-sockets-in-python](http://stackoverflow.com/questions/25261552/multi-multiplayer-server-sockets-in-python)
	- [http://stackoverflow.com/questions/30227722/creating-a-multiplayer-game-in-python](http://stackoverflow.com/questions/30227722/creating-a-multiplayer-game-in-python)
	- [https://docs.python.org/2/howto/sockets.html](https://docs.python.org/2/howto/sockets.html)
	- [https://forum.ubuntuusers.de/topic/python3-tcp-und-udp-fuer-multiplayerspiel/](https://forum.ubuntuusers.de/topic/python3-tcp-und-udp-fuer-multiplayerspiel/)
	- [http://www.binarytides.com/python-socket-programming-tutorial/](http://www.binarytides.com/python-socket-programming-tutorial/)
* Twisted
	- [https://twistedmatrix.com/trac/](https://twistedmatrix.com/trac/)
	- [https://github.com/twisted/twisted](https://github.com/twisted/twisted)
	- [https://en.wikipedia.org/wiki/Twisted_(software)](https://en.wikipedia.org/wiki/Twisted_(software))
	- [https://twistedmatrix.com/documents/current/api/twisted.web.html](https://twistedmatrix.com/documents/current/api/twisted.web.html)
	- [https://twistedmatrix.com/documents/current/api/twisted.internet.html](https://twistedmatrix.com/documents/current/api/twisted.internet.html)


#To-Do
* list might not be complete
* Pacman
* Ghosts (similar to Pacman)
* Labyrinth
* Pacman-can-hunt-ghosts-mode (after pacman ate one of these bigger dots)
* Collision detection between Pacman and Ghosts
* Collision detection between Pacman/Ghosts and Labyrinth walls 
* Create labyrinth randomly
* Multiplayer
* Start-Screen
* Score
* Highscore-Screen
* Database
* Pacman-lifes
* Hatman
	* --> Special version that overall works similar to the normal version but with fancier design: 
		* Pacman wears hat
		* Labyrinth Tim-Burton-like
		* Four different ghosts with different abilities
			* Weeping Angel --> Faster than the other ghosts, but can't move if in front of another character
			* Fox --> sneaks through the lab and can only be seen (by pacman) a) every x seconds or b) when 
					it is near pacman?
			* A ghost-ghost --> can walk through walls and doesn't care about the labrinth at all ???
			* Key --> can lock a way for a few seconds ???
* All the graphical stuff (draw sprites and a fancy designed labyrinth [only peaces so that they can be used 
		to make a random created labyrinth] and so on)
* Music (for Hatman-Version perhaps: Moonlight Sonata)
* Mobile ... ?
* Login

#Q>A
* Do ghosts work togehter and have a common score? --> Yes.
* What do the ghosts see? 
	- --> Only a little snippet of the labyrinth (rectangular area around them)? --> See paperprototype
	- --> Whole labyrinth but without Pacman? 
	- --> Other ghosts? 
	- --> The dots the pacman has to eat --> Rather not because then they could just linger on a place where 
		dots are and wait for the pacman to come there. 
	- --> Some sort of bonus item they can find in the labyrinth to make pacman visible for two 
			seconds or something? (Like the bigger dots the pacman can eat to hunt the ghosts)
	- --> Hatman-Ghosts different? --> Special abilities? 
* Multiplayermode: 
	- --> Can you see all open games looking for more players and join one (possibly not so good if there are 
		a lot players playing --> they have to be very fast to join a game and we need to make sure that there 
		are not too many players in one game accidentially) or do you just choose what character you'd like to 
		play and then get assigned to a game that is in need of that character? --> See Paperprototype
	- --> Friends? 
	- --> If you don't find enough players for a game within a minute (or bla) do you play against the computer? 
		- Problem: Complicated, because we would have to implement an algorithm to move the characters 
			somehow reasonable through the lab
		- Problem2: If our algorithm is not good, it is easier to reach a higher score
* How to get a programme mobile and onto your phone without using the play store??? --> trying, debugging, ...? 
* How to connect to a database using python? 
* How does our database look like? 
	- --> Player (pk_name, pw#, loggedIn(bool))
	- --> Highscore (pk_rank, fk_player, score, character)
	- --> activeGames (pk_id, fk_player_pacman, fk_ghost1, fk_ghost2, fk_ghost3, fk_ghost4) --> null = no player yet
	- --> ??? 
* How to calculate the score of pacman ...
	- eat dots
	- eat ghosts in ghost-hunting-mode
	- finish a level
* ... and of ghosts
	- time? 
	- number of dots eaten by pacman? 
	- ???
* What if a player loses connection or stops playing
	- before game start?
		--> Find another player somehow? 
			--> Depending on the method, the players are assigned together? (--> See paperprototype)
	- within the game?
		--> Game goes on and character just stops moving? 
			--> Wouldn't be so bad in case of a ghost missing, but without pacman it's a bit useless ...
		--> Game stops? 