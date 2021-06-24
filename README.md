# ZJE
Welcome, this is my Java game engine! 
It originated from a wacky game I made a while ago, and has since morphed into an extremely lightweight programmer game engine.

## What is it?
Many things:
* **2D**: It makes 2D games and programs.
* **Code Focused**: Anything and everything is done in Java code, and nothing is hidden. If something doesn't work the way you like, just change it.
* **Assetless**: No facilities for using external assets are provided. Instead, graphics, audio and more should be generated in code, or from configuration files.
* **Extensible**: Much of the functionality uses a permissive and minimal API, meaning adding new features or elements is quite easy. As well, the simple plugin system makes adding non-core game mechanics quite easy.
* **Performant**: The above design choices mean that both building and creating the game is smooth and speedy. All that's required to use the engine is a JDK, and all that's required to play it's game is a JRE. Hardware requirements are virtually non-existant.

## What is it not
The above features are require tradeoffs, creating limitations in the engine:
- **No 3D**: Unfortunate, but it greatly reduces complexity and system requirements. Some light 3D effects should be possible though (simple projections, parallax, etc).
- **No shaders**: Shader programming is fun, but imposes significant hardware and software requirements on development and runtime.
- **No assets**: This is likely the strangest trade-off, but can be a very motivating constraint, and allows tiny install sizes.

## What comes next?
I develop this engine in a somewhat sporadic way, usually in the following fashion:
1. I make a game jam game, which goes somewhat beyond the engine.
2. I forget about it for a bit.
3. I finish and clean up the game.
4. I extract reuseable features from the game into the engine.

For example, I'm currently working on [Polygon Pal](https://github.com/Zalinius/SVG-Defender). The game features real-time dynamic music (using [The Beads Project](http://www.beadsproject.net)) which I'll likely extract into the engine when complete.


## License
The library is licensed under the Gnu Public License (GPL v3).
