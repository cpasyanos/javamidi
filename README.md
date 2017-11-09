# javamidi
A MIDI Music Player following the model-view-controller design pattern. 

By Caroline Pasyanos and Jade Benner

Running the Music Editor:

To run the music editor, run main, passing it two string arguments. The first argument should be the name of the text file of the piece to be played by the program. The second argument should be the name of the must be one of "composite", "console", "midi", or "gui", corresponding to which view the user wants to run.

For example,

"mystery-1.txt" "composite"

will play the 1st mystery file in the composite view.

Navigating the GUI or composite views:

Right and left arrow keys scroll the current beat ONLY WHEN THE PIECE IS PAUSED. The current beat scrolls automatically when playing. Home returns to the beginning of the piece, and end skips to the end. Left clicking on the keyboard while the music is paused will add a note of duration 1 to the music piece at the current beat. Holding down the mouse button allows for longer notes to be added.

Model Design Notes:

IPiece is the main interface, and this is what represents a “piece” of music. A piece of music is a list of beats, organized in ascending order beginning from one. The operations currently supported by IPiece are adding and removing a note, and every implementation of IPiece uses the toString method to produce console output of the piece of music. This output shows the range of notes across the top. The beats are listed down the side, with the beginning of the note marked by an X, and the rest of the note marked by a |.
Beats hold an arraylist of all of the notes that begin at that beat and a number that that beat is at. Notes cannot be added to that beat unless they begin at the beat. Notes in a beat are sorted in ascending order with the lowest notes at the front of the list and the highest at the end.
Notes have a duration, a beginning beat, an octave, and a pitch. Notes implement the Comparable interface so that a list of notes may be sorted in ascending order in terms of lowest to highest note. The compareTo method does not care about the duration or start beat of the note.
